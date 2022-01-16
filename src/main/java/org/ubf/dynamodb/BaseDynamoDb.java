package org.ubf.dynamodb;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public abstract class BaseDynamoDb {
  protected static AmazonDynamoDB dynamoDB;
  protected static DynamoDBMapper mapper;
  private static boolean localMode = false;

  public BaseDynamoDb() {
    if (!localMode) {
      setRemoteEndpoint();
    }
  }

  public void setLocalEndpoint() {
    localMode = true;

    dynamoDB = AmazonDynamoDBClientBuilder
        .standard()
        .withEndpointConfiguration(
            new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", ""))
        .build();
    mapper = new DynamoDBMapper(dynamoDB);
  }

  protected void setRemoteEndpoint() {
    dynamoDB = AmazonDynamoDBClientBuilder
        .standard()
        .withRegion(Regions.US_EAST_2)
        .build();
    mapper = new DynamoDBMapper(dynamoDB);
  }
}
