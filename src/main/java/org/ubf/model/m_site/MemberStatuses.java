/*
 * Member management
 * Church member management system that allows for:  keeping basic information about church attendees,  tracking worship service attendance and tracking Bible Studies.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package org.ubf.model.m_site;

import java.util.Objects;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.ubf.model.m_site.MemberStatus;
/**
 * MemberStatuses
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-12-31T22:46:11.968117300-06:00[America/Chicago]")
public class MemberStatuses extends ArrayList<MemberStatus> {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MemberStatuses {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
