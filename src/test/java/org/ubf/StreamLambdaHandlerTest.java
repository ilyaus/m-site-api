package org.ubf;

import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class StreamLambdaHandlerTest {

  private static StreamLambdaHandler handler;
  private static Context lambdaContext;

  private final LambdaLogger logger = lambdaContext.getLogger();

  @BeforeClass
  public static void setUp() {
    handler = new StreamLambdaHandler();
    lambdaContext = new MockLambdaContext();
  }

  @Test
  public void members_streamRequest_respondsWithData() {
    InputStream requestStream = new AwsProxyRequestBuilder("/m-site/v1/members", HttpMethod.GET)
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
        .queryString("memberId", "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        .buildStream();
    ByteArrayOutputStream responseStream = new ByteArrayOutputStream();

    handle(requestStream, responseStream);

    AwsProxyResponse response = readResponse(responseStream);
    assertNotNull(response);
    logger.log(response.getBody());

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCode());

    assertFalse(response.isBase64Encoded());

    assertTrue(response.getMultiValueHeaders().containsKey(HttpHeaders.CONTENT_TYPE));
    assertTrue(response.getMultiValueHeaders().getFirst(HttpHeaders.CONTENT_TYPE).startsWith(MediaType.APPLICATION_JSON));
  }

  @Test
  public void members_streamRequest_respondsWithOutData() {
    InputStream requestStream = new AwsProxyRequestBuilder("/m-site/v1/members", HttpMethod.GET)
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
        .queryString("memberId", "non-existent")
        .buildStream();
    ByteArrayOutputStream responseStream = new ByteArrayOutputStream();

    handle(requestStream, responseStream);

    AwsProxyResponse response = readResponse(responseStream);

    assertNotNull(response);
    logger.log(response.getBody());
    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatusCode());

    assertFalse(response.isBase64Encoded());

    assertTrue(response.getMultiValueHeaders().containsKey(HttpHeaders.CONTENT_TYPE));
    assertTrue(response.getMultiValueHeaders().getFirst(HttpHeaders.CONTENT_TYPE).startsWith(MediaType.APPLICATION_JSON));
  }

  @Test
  public void members_streamRequest_postMemberValidData() {
    InputStream requestStream = new AwsProxyRequestBuilder("/m-site/v1/members", HttpMethod.POST)
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .body("[\n" +
            "  {\n" +
            "    \"memberId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
            "    \"title\": \"Mr.\",\n" +
            "    \"firstName\": \"John\",\n" +
            "    \"lastName\": \"Smith\",\n" +
            "    \"middleName\": \"Paul\",\n" +
            "    \"email\": \"some@email.com\",\n" +
            "    \"fellowship\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
            "    \"address\": {\n" +
            "      \"street\": \"5555 S Street Ave.\",\n" +
            "      \"city\": \"Chicago\",\n" +
            "      \"state\": \"AZ\",\n" +
            "      \"country\": \"China\"\n" +
            "    },\n" +
            "    \"phone\": [\n" +
            "      {\n" +
            "        \"type\": \"home\",\n" +
            "        \"number\": \"+1 555 555-5555\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]")
        .buildStream();
    ByteArrayOutputStream responseStream = new ByteArrayOutputStream();

    handle(requestStream, responseStream);

    AwsProxyResponse response = readResponse(responseStream);

    assertNotNull(response);
    logger.log(response.getBody());
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCode());

    assertFalse(response.isBase64Encoded());

    assertTrue(response.getMultiValueHeaders().containsKey(HttpHeaders.CONTENT_TYPE));
    assertTrue(response.getMultiValueHeaders().getFirst(HttpHeaders.CONTENT_TYPE).startsWith(MediaType.APPLICATION_JSON));
  }

  @Test
  public void members_streamRequest_postEmptyData() {
    InputStream requestStream = new AwsProxyRequestBuilder("/m-site/v1/members", HttpMethod.POST)
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .body("")
        .buildStream();
    ByteArrayOutputStream responseStream = new ByteArrayOutputStream();

    handle(requestStream, responseStream);

    AwsProxyResponse response = readResponse(responseStream);

    if (response != null) {
      logger.log(response.getBody());
    } else {
      logger.log("Response is null");
    }

    assertNotNull(response);
    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatusCode());

    assertFalse(response.isBase64Encoded());

    assertTrue(response.getMultiValueHeaders().containsKey(HttpHeaders.CONTENT_TYPE));
    assertTrue(response.getMultiValueHeaders().getFirst(HttpHeaders.CONTENT_TYPE).startsWith(MediaType.APPLICATION_JSON));
  }

  @Test
  public void invalidResource_streamRequest_responds404() {
    InputStream requestStream = new AwsProxyRequestBuilder("/m-site/v1/non-existent", HttpMethod.GET)
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
        .buildStream();
    ByteArrayOutputStream responseStream = new ByteArrayOutputStream();

    handle(requestStream, responseStream);

    AwsProxyResponse response = readResponse(responseStream);
    assertNotNull(response);
    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatusCode());
  }

  private void handle(InputStream is, ByteArrayOutputStream os) {
    try {
      handler.handleRequest(is, os, lambdaContext);
    } catch (IOException e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }

  private AwsProxyResponse readResponse(ByteArrayOutputStream responseStream) {
    try {
      return LambdaContainerHandler.getObjectMapper().readValue(responseStream.toByteArray(), AwsProxyResponse.class);
    } catch (IOException e) {
      e.printStackTrace();
      fail("Error while parsing response: " + e.getMessage());
    }
    return null;
  }
}
