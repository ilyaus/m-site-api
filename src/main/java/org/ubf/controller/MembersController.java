package org.ubf.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.ubf.model.m_site.*;

@RestController
@EnableWebMvc
@RequestMapping(value = "/m-site/v1")
public class MembersController {
  private static final Members members = new Members();

  @GetMapping("/members")
  public ResponseEntity<Members> getMembers() {
    members.add(new Member());
    return ResponseEntity.ok(members);
  }

  @PostMapping("/members")
  public ResponseEntity<MemberStatuses> addNewMembers(@RequestBody NewMembers request) {
    return ResponseEntity.ok(new MemberStatuses());
  }

  @PutMapping("/members/{memberId}")
  public ResponseEntity<MemberStatus> updateMember(
      @RequestBody NewMember request, @PathVariable String memberId) {
    return ResponseEntity.ok(new MemberStatus());
  }
}
