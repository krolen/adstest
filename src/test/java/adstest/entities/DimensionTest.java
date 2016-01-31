package adstest.entities;

/**
 * @author kkulagin
 * @since 30.01.2016
 */
public class DimensionTest {

  public static void main(String[] args) {
    int x = (1 << 16) - 1;
    int y = (1 << 16) - 1;
    System.out.println(x + ", " + y);
    System.out.println(Integer.toBinaryString(x) + ", " + Integer.toBinaryString(y));

    Dimension dimension = new Dimension(x, y);
    System.out.println(dimension);
  }

}