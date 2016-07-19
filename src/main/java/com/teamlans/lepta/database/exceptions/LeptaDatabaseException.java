package com.teamlans.lepta.database.exceptions;

public class LeptaDatabaseException extends Exception {

  public LeptaDatabaseException(String message) {
    super("LeptaDatabaseException: " + message);
  }

}
