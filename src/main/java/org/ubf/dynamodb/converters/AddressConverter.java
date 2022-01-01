package org.ubf.dynamodb.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ubf.model.m_site.Address;

public class AddressConverter implements DynamoDBTypeConverter<String, Address> {
  private final Logger logger = LoggerFactory.getLogger(AddressConverter.class);

  @Override
  public String convert(Address address) {
    String addressJson = "";

    try {
      addressJson = new GsonBuilder().create().toJson(address);
    } catch (Exception e) {
      logger.error("Failed to convert Address object to string: {}", e.getMessage());
    }

    return addressJson;
  }

  @Override
  public Address unconvert(String s) {
    Address address = null;

    try {
      address = new Gson().fromJson(s, Address.class);
    } catch (Exception e) {
      logger.error("Failed to convert to Address object: {}", e.getMessage());
    }

    return address;
  }
}
