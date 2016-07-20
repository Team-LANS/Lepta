package com.teamlans.lepta.service.exceptions;

import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;

public class LeptaServiceException extends Exception {

  public LeptaServiceException(String message) {
    super(message);
  }

  public LeptaServiceException(LeptaDatabaseException e) {
    super(e);
  }

  public LeptaServiceException(String message, Exception e) {
    super(message, e);
  }

}
