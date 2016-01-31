package adstest;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import adstest.entities.BidRequest;
import adstest.entities.Campaign;

import java.io.IOException;
import java.util.Set;

/**
 * @author kkulagin
 * @since 31.01.2016
 */
public class Serializer {

  private JsonGenerator generator;

  public Serializer() {
  }

  void start() {
    JsonFactory factory = new JsonFactory();
    try {
      generator = factory.createGenerator(System.out);
      generator.setCodec(new ObjectMapper());
      generator.useDefaultPrettyPrinter();
      generator.writeStartObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  void startCollection(String name) {
    try {
      generator.writeArrayFieldStart(name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  void endCollection() {
    try {
      generator.writeEndArray();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  void printCampaign(Campaign campaign) {
    try {
      generator.writeObject(campaign);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  void printField(String name, String value) {
    try {
      generator.writeFieldName(name);
      generator.writeRawValue(value);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  void printRequestResult(BidRequest bidRequest, Set<Long> campaignIds) {
    try {
      generator.writeStartObject();
      generator.writeFieldName("request");
      generator.writeObject(bidRequest);
      generator.writeFieldName("matchedCampaigns");
      generator.writeObject(campaignIds);
      generator.writeEndObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  void printRequestResult(BidRequest bidRequest, long[] campaignIds) {
    try {
      generator.writeStartObject();
      generator.writeFieldName("request");
      generator.writeObject(bidRequest);
      generator.writeFieldName("matchedCampaigns");
      generator.writeObject(campaignIds);
      generator.writeEndObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  void close() {
    try {
      generator.writeEndObject();
      generator.close();
    } catch (IOException ignored) {
    }
  }
}
