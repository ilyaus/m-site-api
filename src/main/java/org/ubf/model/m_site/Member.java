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

import java.util.List;
import java.util.Objects;
import java.util.Arrays;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.UUID;

import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters;
import org.ubf.dynamodb.converters.AddressConverter;
import org.ubf.dynamodb.converters.PhoneNumbersConverter;
import org.ubf.dynamodb.converters.UuidConverter;
import org.ubf.model.m_site.Address;
import org.ubf.model.m_site.PhoneNumbers;
/**
 * Details of a single member
 */
@Schema(description = "Details of a single member")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-12-29T22:00:45.549199200-06:00[America/Chicago]")
@DynamoDBTable(tableName = "ubf-msite-members")
public class Member {
  @DynamoDBHashKey(attributeName = "memberId")
  @DynamoDBAutoGeneratedKey
  @SerializedName("memberId")
  private String memberId = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("firstName")
  private String firstName = null;

  @SerializedName("lastName")
  private String lastName = null;

  @SerializedName("middleName")
  private String middleName = null;

  @SerializedName("email")
  private String email = null;

  @DynamoDBTypeConverted(converter = UuidConverter.class)
  @SerializedName("fellowship")
  private UUID fellowship = null;

  @DynamoDBTypeConverted(converter = AddressConverter.class)
  @SerializedName("address")
  private Address address = null;

  @DynamoDBTypeConverted(converter = PhoneNumbersConverter.class)
  @SerializedName("phone")
  private PhoneNumbers phone = null;

  public Member memberId(String memberId) {
    this.memberId = memberId;
    return this;
  }

   /**
   * Get memberId
   * @return memberId
  **/
  @Schema(description = "")
  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public Member title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @Schema(example = "Mr.", description = "")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Member firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

   /**
   * Get firstName
   * @return firstName
  **/
  @Schema(example = "John", description = "")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Member lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

   /**
   * Get lastName
   * @return lastName
  **/
  @Schema(example = "Smith", description = "")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Member middleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

   /**
   * Get middleName
   * @return middleName
  **/
  @Schema(example = "Paul", description = "")
  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public Member email(String email) {
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @Schema(example = "some@email.com", description = "")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Member fellowship(UUID fellowship) {
    this.fellowship = fellowship;
    return this;
  }

   /**
   * Get fellowship
   * @return fellowship
  **/
  @Schema(description = "")
  public UUID getFellowship() {
    return fellowship;
  }

  public void setFellowship(UUID fellowship) {
    this.fellowship = fellowship;
  }

  public Member address(Address address) {
    this.address = address;
    return this;
  }

   /**
   * Get address
   * @return address
  **/
  @Schema(description = "")
  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Member phone(PhoneNumbers phone) {
    this.phone = phone;
    return this;
  }

   /**
   * Get phone
   * @return phone
  **/
  @Schema(description = "")
  public PhoneNumbers getPhone() {
    return phone;
  }

  public void setPhone(PhoneNumbers phone) {
    this.phone = phone;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Member member = (Member) o;
    return Objects.equals(this.memberId, member.memberId) &&
        Objects.equals(this.title, member.title) &&
        Objects.equals(this.firstName, member.firstName) &&
        Objects.equals(this.lastName, member.lastName) &&
        Objects.equals(this.middleName, member.middleName) &&
        Objects.equals(this.email, member.email) &&
        Objects.equals(this.fellowship, member.fellowship) &&
        Objects.equals(this.address, member.address) &&
        Objects.equals(this.phone, member.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberId, title, firstName, lastName, middleName, email, fellowship, address, phone);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Member {\n");
    
    sb.append("    memberId: ").append(toIndentedString(memberId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    middleName: ").append(toIndentedString(middleName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    fellowship: ").append(toIndentedString(fellowship)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
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
