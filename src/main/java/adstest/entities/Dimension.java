package adstest.entities;

/**
 * This is unspecified in a project description, I assume that bid request matches campaign
 * iff campaign dimensions collection contains exactly the same dimension.
 *
 * I.e. bid request 200*300 does not macth campaign with dimension 200*400 even though it fits inside.
 *
 * Another assumption is - both dimensions (x and y) are not more than 2^16 which should be true.
 * From my understanding they will not even be more than 2^13
 *
 * Generally speaking if we need better performance we don't need this class at all - we need to represent dimension as int.
 *
 * @author kkulagin
 * @since 30.01.2016
 */
public class Dimension {
  private final int value;

  public Dimension(int value) {
    this.value = value;
  }

  public Dimension(int x, int y) {
    value = x << 16 | y;
  }

  public int asInt() {
    return value;
  }

  public int getX() {
    return value >>> 16;
  }

  public int getY() {
    return 0xFF_FF & value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Dimension)) return false;
    Dimension dimension = (Dimension) o;
    return value == dimension.value;
  }

  @Override
  public int hashCode() {
    return value;
  }

  @Override
  public String toString() {
    return "Dimension{" +
      "x=" + getX() +
      ", y=" + getY() +
      '}';
  }


}
