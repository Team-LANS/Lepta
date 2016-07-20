package com.teamlans.lepta.database.exceptions;

public class LeptaDatabaseException extends Exception {

  public LeptaDatabaseException(String message) {
    super(message);
  }

  public LeptaDatabaseException(String message, Exception e) {
    super(message, e);
  }

}
