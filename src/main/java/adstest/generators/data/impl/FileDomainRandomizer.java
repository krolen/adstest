package adstest.generators.data.impl;

import adstest.generators.data.Randomizer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author kkulagin
 * @since 30.01.2016
 */
public class FileDomainRandomizer implements Randomizer<String> {

  private final String fileName;
  private final String[] domains;

  public FileDomainRandomizer(String fileName) {
    this.fileName = fileName;
    domains = getDomains();
  }

  @Override
  public String[] getData() {
    return domains;
  }

  private String[] getDomains() {
    try (Stream<String> stream = Files.lines(Paths.get(this.getClass().getResource(fileName).toURI()))) {
      return stream.toArray(String[]::new);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
