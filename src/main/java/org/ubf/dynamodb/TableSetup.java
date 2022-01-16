package org.ubf.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableSetup extends BaseDynamoDb{
  private final Logger logger = LoggerFactory.getLogger(TableSetup.class);

  public void createTable(Class<?> domainClass) {
    CreateTableRequest tableRequest = mapper.generateCreateTableRequest(domainClass);
    tableRequest.withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

    tableRequest
        .getGlobalSecondaryIndexes().get(0)
        .setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

    if (tableExists(dynamoDB, tableRequest.getTableName())) {
      logger.info("Table for class {} already exists!", domainClass.getName());
      return;
    }

    dynamoDB.createTable(tableRequest);

    waitForTableToCreate(dynamoDB, tableRequest.getTableName());
  }

  public boolean tableExists(AmazonDynamoDB dynamoDB, String tableName) {
    try {
      dynamoDB.describeTable(tableName);
      return true;
    } catch (ResourceNotFoundException ex) {
      return false;
    }
  }

  public void waitForTableToCreate(AmazonDynamoDB dynamoDB, String tableName) {
    while (true) {
      try {
        Thread.sleep(2000);
        TableDescription table = dynamoDB.describeTable(tableName).getTable();

        if (table == null) {
          continue;
        }

        String tableStatus = table.getTableStatus();

        if (tableStatus.equals(TableStatus.ACTIVE.toString())) {
          logger.info("Table created and active");
          return;
        }
      } catch (ResourceNotFoundException ex) {
        logger.info("Table not created ... waiting.");
      } catch (Exception ex) {
        throw new RuntimeException();
      }
    }
  }
}
