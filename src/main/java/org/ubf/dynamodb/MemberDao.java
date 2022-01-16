package org.ubf.dynamodb;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.ubf.model.m_site.Member;
import org.ubf.model.m_site.Members;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MemberDao extends BaseDynamoDb {

  public Member put(Member member){
    mapper.save(member);

    return member;
  }

  public Members getMembersById(UUID memberId) {
    DynamoDBQueryExpression<Member> queryExpression = new DynamoDBQueryExpression<Member>()
        .withKeyConditionExpression("memberId = :memberId")
        .withExpressionAttributeValues(Map.of(":memberId", new AttributeValue().withS(memberId.toString())));

    return getMembers(queryExpression);
  }

  public Members getMembersByFellowshipId(UUID fellowshipId) {
    Member member = new Member();
    member.setFellowshipId(fellowshipId);

    DynamoDBQueryExpression<Member> queryExpression = new DynamoDBQueryExpression<Member>()
        .withHashKeyValues(member)
        .withConsistentRead(false)
        .withScanIndexForward(false);

    return getMembers(queryExpression);
  }

  public Members getMembers(Map<String, String> filter) {
    return new Members();
  }

  public Member get(UUID memberId) {
    return mapper.load(Member.class, memberId);
  }

  public void delete(UUID id) {
    Member m = new Member();
    m.memberId(id);

    mapper.delete(m);
  }

  public List<Member> getAll() {
    return mapper.scan(Member.class, new DynamoDBScanExpression());
  }

  private Members getMembers(DynamoDBQueryExpression<Member> queryExpression) {
    List<Member> result = mapper.query(Member.class, queryExpression);

    Members members = new Members();
    members.addAll(result);

    return members;
  }
}
