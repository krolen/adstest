package adstest.utils;

import adstest.entities.BidRequest;
import adstest.entities.Campaign;
import adstest.entities.Country;
import adstest.entities.Dimension;

import java.util.HashSet;
import java.util.Set;

/**
 * Cannot use junit due assignment restrictions. So having fun :)
 *
 * @author kkulagin
 * @since 30.01.2016
 */
public class TSTTest {


  public void testGet() throws Exception {
    TST trie = new TST();
    Campaign campaign = new Campaign(1, Country.CA, "apple.com", campaignDimensions());
    trie.put(campaign);

    Set<Dimension> shortCampaignDimentions = campaignDimensions();
    shortCampaignDimentions.remove(new Dimension(600, 700));
    shortCampaignDimentions.remove(new Dimension(800, 900));
    campaign = new Campaign(2, Country.CA, "super.apple.com", shortCampaignDimentions);
    trie.put(campaign);

    BidRequest bidRequest = new BidRequest(1, "http://www.apple.com", Country.CA, requestDimensions());
    Set<Long> longs = trie.get(bidRequest);
    check(longs.contains(1L));

    bidRequest = new BidRequest(1, "https://www.apple.com/yoyoyo", Country.CA, requestDimensions());
    longs = trie.get(bidRequest);
    check(longs.contains(1L));

    bidRequest = new BidRequest(1, "https://www.apple.com/yoyoyo", Country.IT, requestDimensions());
    longs = trie.get(bidRequest);
    check(longs.isEmpty());

    Set<Dimension> dimensions = requestDimensions();
    dimensions.remove(new Dimension(600, 700));
    dimensions.remove(new Dimension(800, 900));
    bidRequest = new BidRequest(1, "https://www.apple.com/yoyoyo", Country.CA, dimensions);
    longs = trie.get(bidRequest);
    check(longs.isEmpty());

    dimensions.add(new Dimension(100, 200));
    bidRequest = new BidRequest(1, "https://super.super.apple.com/yoyoyo", Country.CA, dimensions);
    longs = trie.get(bidRequest);
    check(longs.contains(2L));

  }

  public void testPut() throws Exception {

  }

  private Set<Dimension> campaignDimensions() {
    Set<Dimension> dimensions = new HashSet<>();
    dimensions.add(new Dimension(100, 200));
    dimensions.add(new Dimension(200, 300));
    dimensions.add(new Dimension(400, 500));
    dimensions.add(new Dimension(600, 700));
    dimensions.add(new Dimension(800, 900));
    return dimensions;
  }

  private Set<Dimension> requestDimensions() {
    Set<Dimension> dimensions = new HashSet<>();
    dimensions.add(new Dimension(101, 201));
    dimensions.add(new Dimension(201, 301));
    dimensions.add(new Dimension(401, 501));
    dimensions.add(new Dimension(600, 700));
    dimensions.add(new Dimension(800, 900));
    return dimensions;
  }

  private void check(boolean condition, String msg) {
    if (!condition) {
      throw new RuntimeException("Condition not met: " + msg);
    }
  }

  private void check(boolean condition) {
    check(condition, null);
  }

  public static void main(String[] args) throws Exception {
    TSTTest tstTest = new TSTTest();
    tstTest.testGet();
  }


}