package org.ubf.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.ubf.model.m_site.Member;

import java.util.List;

public class MemberDao extends BaseDynamoDb {

  public Member put(Member member){

    mapper.save(member);

    return member;
  }

  public Member get(String memberId) {
    return mapper.load(Member.class, memberId);
  }

  public void delete(String id) {
    Member m = new Member();
    m.memberId(id);

    mapper.delete(m);
  }

  public List<Member> getAll() {
    return mapper.scan(Member.class, new DynamoDBScanExpression());
  }
}
