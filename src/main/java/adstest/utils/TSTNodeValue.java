package adstest.utils;

import adstest.entities.Campaign;
import adstest.entities.Country;

import java.util.*;

/**
 * contains data array. If there is a campaigns with corresponding country
 * then corresponding array entry will contain TreeMap<Integer, long[]>
 * mapping possible dimensions (represented by int) to a sorted array of campaign ids that have this dimensions.
 * It means campaign id might be duplicated in several Map entries.
 * <p>
 * Depending on number of campaigns we might want to choose a different way to store dimensions and campaigns info
 */
class TSTNodeValue {

  private final Map<Integer, long[]>[] data;

  public TSTNodeValue() {
    int totalCountries = Country.count();
    data = new Map[totalCountries];
  }

  public void addCampaign(Campaign campaign) {
    if (data[campaign.getCountry().value()] == null) {
      data[campaign.getCountry().value()] = new HashMap<>();
    }
    final Map<Integer, long[]> map = data[campaign.getCountryValue()];

    Arrays.stream(campaign.getDimensionsAsInts()).parallel().forEach(dimension -> {
      long[] existingCampaignIds = map.getOrDefault(dimension, new long[]{});
      long[] newCampaignIds = Arrays.copyOf(existingCampaignIds, existingCampaignIds.length + 1);
      newCampaignIds[newCampaignIds.length - 1] = campaign.getId();
      map.put(dimension, newCampaignIds);
    });
  }

  public Set<Long> matchedCampaignIds(int countryCode, int[] dimensions) {
    Map<Integer, long[]> dimension2CampaignIds = data[countryCode];
    if (dimension2CampaignIds == null) return Collections.emptySet();

    Set<Long> set = new HashSet<>();
    for (int dimension : dimensions) {
      long[] campaignIds = dimension2CampaignIds.get(dimension);
      if(campaignIds != null) {
        for (long campaignId : campaignIds) {
          set.add(campaignId);
        }
      }
    }
    return set;
  }

}
