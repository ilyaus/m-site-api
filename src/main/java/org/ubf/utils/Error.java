package org.ubf.utils;

public class Error {
  private final String code;
  private final String message;

  public Error(String status, String message) {
    this.code = status;
    this.message = message;
  }

  public String getStatus() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
