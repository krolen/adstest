package adstest.generators;

import adstest.entities.BidRequest;
import adstest.entities.Country;
import adstest.entities.Dimension;
import adstest.generators.data.Randomizer;
import adstest.generators.data.impl.CountryRandomizer;
import adstest.utils.Utils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author kkulagin
 * @since 30.01.2016
 */
public class BidRequestsGenerator {
  private AtomicLong id = new AtomicLong(0);
  private final Randomizer<Country> countryRandomizer;
  private final Randomizer<String> domainRandomizer;
  private final Randomizer<Dimension> dimensionRandomizer;

  public BidRequestsGenerator(Randomizer<String> domainRandomizer, Randomizer<Dimension> dimensionRandomizer) {
    this(domainRandomizer, dimensionRandomizer, new CountryRandomizer());
  }

  public BidRequestsGenerator(Randomizer<String> domainRandomizer, Randomizer<Dimension> dimensionRandomizer, Randomizer<Country> countryRandomizer) {
    this.domainRandomizer = domainRandomizer;
    this.dimensionRandomizer = dimensionRandomizer;
    this.countryRandomizer = countryRandomizer;
  }


  public BidRequest generate() {
    int dimensionsNumber = ThreadLocalRandom.current().nextInt(dimensionRandomizer.getData().length) + 1;
    Set<Dimension> dimensions = new HashSet<>();
    for (int i = 0; i < dimensionsNumber; i++) {
      dimensions.add(dimensionRandomizer.random());
    }
    return new BidRequest(id.incrementAndGet(), Utils.generateUrl(domainRandomizer.random()), countryRandomizer.random(), dimensions);
  }

}
