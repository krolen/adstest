package adstest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kkulagin
 * @since 30.01.2016
 */
public class BidRequest {
  private final long id;
  private final String pageUrl;
  private final int countryValue;
  private final int[] dimensions;

  public BidRequest(long id, String pageUrl, Country country, int[] dimensions) {
    this.id = id;
    this.pageUrl = pageUrl;
    this.countryValue = country.value();
    this.dimensions = dimensions;
  }

  public BidRequest(long id, String pageUrl, Country country, Set<Dimension> dimensions) {
    this(id, pageUrl, country, dimensions.stream().mapToInt(Dimension::asInt).toArray());
  }

  public long getId() {
    return id;
  }

  public String getPageUrl() {
    return pageUrl;
  }

  public Country getCountry() {
    return Country.fromValue(countryValue);
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BidRequest)) return false;

    BidRequest that = (BidRequest) o;

    return id == that.id;

  }

  @Override
  public int hashCode() {
    return (int) (id ^ (id >>> 32));
  }

  @Override
  public String toString() {
    return "BidRequest{" +
      "id=" + id +
      ", pageUrl='" + pageUrl + '\'' +
      ", country=" + getCountry() +
      ", dimensions=" + Arrays.toString(getDimensions().toArray()) +
      '}';
  }
}
