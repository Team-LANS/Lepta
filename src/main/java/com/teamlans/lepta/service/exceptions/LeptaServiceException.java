package com.teamlans.lepta.service.exceptions;

public class LeptaServiceException extends Exception {

  public LeptaServiceException(String message) {
    super(message);
  }

  public LeptaServiceException(Exception e) {
    super(e);
  }

  public LeptaServiceException(String message, Exception e) {
    super(message, e);
  }

}
