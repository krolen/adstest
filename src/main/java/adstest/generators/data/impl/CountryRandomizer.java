package adstest.generators.data.impl;

import adstest.entities.Country;
import adstest.generators.data.Randomizer;

/**
 * @author kkulagin
 * @since 30.01.2016
 */
public class CountryRandomizer implements Randomizer<Country> {

  private final Country[] data;

  public CountryRandomizer() {
    data = Country.values();
  }

  @Override
  public Country[] getData() {
    return data;
  }
}
