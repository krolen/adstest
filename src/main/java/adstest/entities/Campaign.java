package adstest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kkulagin
 * @since 30.01.2016
 */
public class Campaign {
  private final long id;
  private final int countryValue;
  private final String domain;
  private final int[] dimensions;

  public Campaign(long id, Country country, String domain, Set<Dimension> dimensions) {
    this(id, country, domain, dimensions.stream().mapToInt(Dimension::asInt).toArray());
  }

  public Campaign(long id, Country country, String domain, int[] dimensions) {
    this.id = id;
    this.domain = domain;
    this.countryValue = country.value();
    this.dimensions = dimensions;

  }

  public long getId() {
    return id;
  }

  public Country getCountry() {
    return Country.fromValue(countryValue);
  }

  public String getDomain() {
    return domain;
  }

  @JsonIgnore
  public int[] getDimensionsAsInts() {
    return dimensions;
  }

  @JsonIgnore
  public int getCountryValue() {
    return countryValue;
  }

  public Set<Dimension> getDimensions() {
    return Arrays.stream(getDimensionsAsInts()).mapToObj(Dimension::new).collect(Collectors.toSet());
  }

  @Override
  public String toString() {
    return "Campaign{" +
      "id=" + id +
      ", country=" + getCountry() +
      ", domain='" + domain + '\'' +
      ", dimensions=" + Arrays.toString(getDimensions().toArray()) +
      '}';
  }
}
