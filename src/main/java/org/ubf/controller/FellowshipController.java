package org.ubf.controller;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.ubf.dynamodb.MemberDao;
import org.ubf.model.m_site.Member;
import org.ubf.model.m_site.MemberStatus;
import org.ubf.model.m_site.MemberStatuses;
import org.ubf.model.m_site.Members;
import org.ubf.utils.Error;

import java.util.Map;
import java.util.UUID;

@RestController
@EnableWebMvc
@RequestMapping(value = "/m-site/v1")
@ResponseBody @ResponseStatus(value = HttpStatus.OK)
public class FellowshipController {
  private static final Logger logger = LoggerFactory.getLogger(FellowshipController.class);

  @GetMapping("/fellowships")
  public ResponseEntity<Object> getMembers(@RequestParam(required = false) Map<String, String> queryParams) {
    logger.info("Getting members: {}", queryParams);

    return ResponseEntity.ok("");
  }

  @PostMapping("/fellowships")
  public ResponseEntity<Object> addNewMembers(@RequestBody Members request) {
    logger.info("Posting members: {}", request.toString());

    return ResponseEntity.ok("");
  }

  @PutMapping("/fellowships/{fellowshipId}")
  public ResponseEntity<Object> updateMember(@RequestBody Member request, @PathVariable String memberId) {
    return ResponseEntity.ok(new MemberStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleNotReadableException(Exception e) {
    if (e instanceof DynamoDBMappingException) {
      return ResponseEntity
          .internalServerError()
          .body(new Error("500.001", "Database error (" + e.getMessage() + ")."));

    } else if (e instanceof AmazonDynamoDBException) {
      return ResponseEntity
          .internalServerError()
          .body(new Error("500.002", "Database error (" + ((AmazonDynamoDBException) e).getErrorMessage() + ")."));
    }

    return ResponseEntity
        .badRequest()
        .body(new Error("400.001", "Required request body is missing or can't be parsed."));
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException e) {
    return ResponseEntity
        .status(e.getStatus())
        .body(new Error(String.valueOf(e.getStatus().value()), e.getReason()));
  }
}
