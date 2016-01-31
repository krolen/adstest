package adstest.generators.data;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author kkulagin
 * @since 30.01.2016
 */
public interface Randomizer<T> {

  T[] getData();

  default T random() {
    return getData()[ThreadLocalRandom.current().nextInt(getData().length)];
  }
}
