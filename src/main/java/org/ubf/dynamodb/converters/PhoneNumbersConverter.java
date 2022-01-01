package org.ubf.dynamodb.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ubf.model.m_site.PhoneNumbers;

public class PhoneNumbersConverter implements DynamoDBTypeConverter<String, PhoneNumbers> {
  private final Logger logger = LoggerFactory.getLogger(PhoneNumbersConverter.class);

  @Override
  public String convert(PhoneNumbers phoneNumbers) {
    String phoneNumbersJson = "";

    try {
      phoneNumbersJson = new GsonBuilder().create().toJson(phoneNumbers);
    } catch (Exception e) {
      logger.error("Failed to convert Address object to string: {}", e.getMessage());
    }

    return phoneNumbersJson;
  }

  @Override
  public PhoneNumbers unconvert(String s) {
    PhoneNumbers phoneNumbers = null;

    try {
      phoneNumbers = new Gson().fromJson(s, PhoneNumbers.class);
    } catch (Exception e) {
      logger.error("Failed to convert to Address object: {}", e.getMessage());
    }

    return phoneNumbers;
  }
}
