package org.ubf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.ubf.dynamodb.MemberDao;
import org.ubf.model.m_site.*;
import org.ubf.utils.Error;

import java.util.Map;

@RestController
@EnableWebMvc
@RequestMapping(value = "/m-site/v1")
@ResponseBody @ResponseStatus(value = HttpStatus.OK)
public class MembersController {
  private static final Logger logger = LoggerFactory.getLogger(MembersController.class);
  MemberDao memberDao = new MemberDao();

  @GetMapping("/members")
  public ResponseEntity<Object> getMembers(@RequestParam(required = false) Map<String, String> queryParams) {
    logger.info("Getting members: {}", queryParams);

    // Valid query parameters:
    String memberId = queryParams.getOrDefault("memberId", "");
    String fellowshipId = queryParams.getOrDefault("fellowshipId", "");
    String campusId = queryParams.getOrDefault("campusId", "");
    String lastName = queryParams.getOrDefault("lastName", "");

    return ResponseEntity.ok(memberDao.get(memberId));
  }

  @PostMapping("/members")
  public ResponseEntity<Object> addNewMembers(@RequestBody Members request) {
    logger.info("Posting members: {}", request.toString());

    request.forEach(memberDao::put);

    return ResponseEntity.ok(new MemberStatuses());
  }

  @PutMapping("/members/{memberId}")
  public ResponseEntity<Object> updateMember(
      @RequestBody NewMember request, @PathVariable String memberId) {
    return ResponseEntity.ok(new MemberStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleNotReadableException(Exception e) {
    return ResponseEntity
        .badRequest()
        .body(new Error("400.001", "Required request body is missing or can't be parsed."));
  }
}
