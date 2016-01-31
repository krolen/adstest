package adstest.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author kkulagin
 * @since 30.01.2016
 */
public class Utils {
  private static final String[] PROTOCOLS = new String[]{"http://", "https://"};
  private static final String[] SUBDOMAINS = new String[]{"", "one.", "two.", "three."};
  private static final String[] PATHS = new String[]{"", "/path1", "/path2", "/path3", "/path4", "/path5"};

  public static char[] appendDotAndReverse(char[] orig) {
    char[] reverse = new char[orig.length + 1];
    for (int i = 0; i < orig.length; i++) {
      reverse[i] = orig[orig.length - i - 1];
    }
    reverse[orig.length] = '.';
    return reverse;
  }

  public static char[] extractAndReverseDomainWithDot(String url) {
    char[] chars = url.toCharArray();
    int httpProtocolLength = PROTOCOLS[0].length();
    // handle https
    int start = chars[httpProtocolLength] == '/' ? httpProtocolLength + 1 : httpProtocolLength;
    int end = chars.length;
    for (int i = start; i < chars.length; i++) {
      if(chars[i] == '/') {
        end = i;
        break;
      }
    }
    return appendDotAndReverse(Arrays.copyOfRange(chars, start, end));
  }

  public static String generateUrl(String domain) {
    ThreadLocalRandom random = ThreadLocalRandom.current();
    return PROTOCOLS[random.nextInt(PROTOCOLS.length)] +
      SUBDOMAINS[random.nextInt(SUBDOMAINS.length)] +
      domain +
      PATHS[random.nextInt(PATHS.length)] +
      PATHS[random.nextInt(PATHS.length)];
  }

  public static long[] toArray(Set<Long> set) {
    long[] result = new long[set.size()];
    Iterator<Long> itr = set.iterator();
    for (int i = 0; i < result.length; i++) {
      result[i] = itr.next();
    }
    return result;
  }

}
