package adstest;

import adstest.entities.Country;
import adstest.entities.Dimension;
import adstest.generators.BidRequestsGenerator;
import adstest.generators.CampaignsGenerator;
import adstest.generators.data.Randomizer;
import adstest.generators.data.impl.FileDimensionsRandomizer;
import adstest.generators.data.impl.FileDomainRandomizer;

import java.util.Arrays;

/**
 * @author kkulagin
 * @since 31.01.2016
 */
public class Runner {

  public static void runBig(int campaignsNumber, int requestsNumber) {
    new Matcher(campaignsNumber, requestsNumber).match();
  }

  public static void runTest(int campaignsNumber, int requestsNumber) {
    SmallCountryRandomizer smallCountryRandomizer = new SmallCountryRandomizer();

    Matcher matcher = new Matcher(campaignsNumber, requestsNumber);
    Randomizer<String> campaignDomainsRandomizer = new FileDomainRandomizer("/campaign_domains.txt");
    Randomizer<Dimension> campaignDimensionRandomizer = new FileDimensionsRandomizer("/campaign_dimensions.txt");
    matcher.setCampaignsGenerator(new CampaignsGenerator(campaignDomainsRandomizer, campaignDimensionRandomizer, smallCountryRandomizer));

    Randomizer<String> requestDomainsRandomizer = new FileDomainRandomizer("/request_domains.txt");
    Randomizer<Dimension> requestDimensionRandomizer = new FileDimensionsRandomizer("/request_dimensions.txt");
    matcher.setBidRequestsGenerator(new BidRequestsGenerator(requestDomainsRandomizer, requestDimensionRandomizer, smallCountryRandomizer));

    matcher.match();
  }

  private static class SmallCountryRandomizer implements Randomizer<Country> {
    private final Country[] data;

    public SmallCountryRandomizer() {
      data = Arrays.copyOf(Country.values(), 2);
    }

    @Override
    public Country[] getData() {
      return data;
    }
  }

  public static void main(String[] args) {
    int campaignsNumber = Integer.parseInt(args[0]);
    int requestsNumber = Integer.parseInt(args[1]);
    if(args.length == 2) {
      runTest(campaignsNumber, requestsNumber);
    } else {
      runBig(campaignsNumber, requestsNumber);
    }
  }

}
