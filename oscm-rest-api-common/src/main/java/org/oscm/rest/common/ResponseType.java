package org.oscm.rest.common;

public enum ResponseType {
  OK(200),
  CREATED(201),
  NOT_IMPLEMENTED(501),
  FORBIDDEN(403);

  private final int statusCode;

  private ResponseType(int statusCode) {
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
