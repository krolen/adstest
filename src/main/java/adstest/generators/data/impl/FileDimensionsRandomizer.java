package adstest.generators.data.impl;

import adstest.entities.Dimension;
import adstest.generators.data.Randomizer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author kkulagin
 * @since 30.01.2016
 */
public class FileDimensionsRandomizer implements Randomizer<Dimension> {

  private final String fileName;
  private final Dimension[] dimensions;

  public FileDimensionsRandomizer(String fileName) {
    this.fileName = fileName;
    dimensions = getDimensions();
  }

  @Override
  public Dimension[] getData() {
    return dimensions;
  }

  private Dimension[] getDimensions() {
    try (Stream<String> stream = Files.lines(Paths.get(this.getClass().getResource(fileName).toURI()))) {
      return stream.map((s -> {
        String[] dimensions = s.split(" ");
        return new Dimension(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
      })).toArray(Dimension[]::new);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
