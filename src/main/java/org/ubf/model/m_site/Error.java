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
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * ErrorResponse represents error code, description for a given API. Every HTTP status code returns different &#x60;code&#x60; value in the response based on the nature of error occurred.
 */
@Schema(description = "ErrorResponse represents error code, description for a given API. Every HTTP status code returns different `code` value in the response based on the nature of error occurred.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-12-29T22:00:45.549199200-06:00[America/Chicago]")
public class Error {
  @SerializedName("code")
  private String code = null;

  @SerializedName("description")
  private String description = null;

  public Error code(String code) {
    this.code = code;
    return this;
  }

   /**
   * Valid HTTP response code.
   * @return code
  **/
  @Schema(example = "400.101", required = true, description = "Valid HTTP response code.")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Error description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Detailed description of the error.
   * @return description
  **/
  @Schema(example = "Request body does not match expected domain object.", required = true, description = "Detailed description of the error.")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.code, error.code) &&
        Objects.equals(this.description, error.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, description);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
