package com.teamlans.lepta.service.exceptions;

import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;

public class LeptaServiceException extends Exception {

  public LeptaServiceException(String message) {
    super("LeptaServiceException: " + message);
  }

  public LeptaServiceException(LeptaDatabaseException e) {
    super("LeptaServiceException: " + e.getMessage());
  }

}
