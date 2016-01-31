package adstest;

import adstest.entities.BidRequest;
import adstest.entities.Campaign;
import adstest.entities.Dimension;
import adstest.generators.BidRequestsGenerator;
import adstest.generators.CampaignsGenerator;
import adstest.generators.data.Randomizer;
import adstest.generators.data.impl.FileDimensionsRandomizer;
import adstest.generators.data.impl.FileDomainRandomizer;
import adstest.utils.TST;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author kkulagin
 * @since 30.01.2016
 */
public class Matcher {

  private BidRequestsGenerator bidRequestsGenerator;
  private CampaignsGenerator campaignsGenerator;

  private final int campaignsNumber;
  private final int bidsNumber;
  private TST trie = new TST();

  public Matcher(int campaignsNumber, int bidsNumber) {
    this.campaignsNumber = campaignsNumber;
    this.bidsNumber = bidsNumber;
    init();
  }

  private void init() {
    Randomizer<String> campaignDomainsRandomizer = new FileDomainRandomizer("/campaign_domains_big.txt");
    Randomizer<Dimension> campaignDimensionRandomizer = new FileDimensionsRandomizer("/campaign_dimensions.txt");
    campaignsGenerator = new CampaignsGenerator(campaignDomainsRandomizer, campaignDimensionRandomizer);

    Randomizer<String> requestDomainsRandomizer = new FileDomainRandomizer("/request_domains_big.txt");
    Randomizer<Dimension> requestDimensionRandomizer = new FileDimensionsRandomizer("/request_dimensions.txt");
    bidRequestsGenerator = new BidRequestsGenerator(requestDomainsRandomizer, requestDimensionRandomizer);
  }

  public void match() {
    Serializer serializer = new Serializer();
    serializer.start();
    serializer.startCollection("campaigns");
    for (int i = 0; i < campaignsNumber; i++) {
      Campaign campaign = campaignsGenerator.generate();
      trie.put(campaign);
      serializer.printCampaign(campaign);
    }
    campaignsGenerator = null;
    serializer.endCollection();

    // fill requests data to see actual processing time without new data creation
    BidRequest[] requests = new BidRequest[bidsNumber];
    IntStream.range(0, bidsNumber).parallel().forEach(i -> {
      requests[i] = bidRequestsGenerator.generate();
    });
    bidRequestsGenerator = null;
    serializer.startCollection("results");

    AsyncPrinter asyncPrinter = new AsyncPrinter(serializer);
    asyncPrinter.start();

    long start = System.currentTimeMillis();
    Arrays.stream(requests).parallel().forEach(bidRequest -> {
      Set<Long> campaignIds = trie.get(bidRequest);
      asyncPrinter.print(bidRequest, campaignIds);
    });
    long end = System.currentTimeMillis();
    asyncPrinter.stop();

    serializer.endCollection();
    serializer.printField("bidsNumber", String.valueOf(bidsNumber));
    serializer.printField("evaluationTime", String.valueOf(end - start));
    serializer.close();
  }


  public void setBidRequestsGenerator(BidRequestsGenerator bidRequestsGenerator) {
    this.bidRequestsGenerator = bidRequestsGenerator;
  }

  public void setCampaignsGenerator(CampaignsGenerator campaignsGenerator) {
    this.campaignsGenerator = campaignsGenerator;
  }

}
