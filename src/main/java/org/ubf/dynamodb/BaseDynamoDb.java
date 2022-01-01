package org.ubf.dynamodb;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public abstract class BaseDynamoDb {
  protected final DynamoDBMapper mapper;

  public BaseDynamoDb() {
    AmazonDynamoDB dynamoDb = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
    this.mapper = new DynamoDBMapper(dynamoDb);
  }
}
