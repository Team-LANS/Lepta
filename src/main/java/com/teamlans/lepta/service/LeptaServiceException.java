package com.teamlans.lepta.service;

import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;

/**
 * @author Hans-Joerg Schroedl
 */
public class LeptaServiceException extends Throwable {
  public LeptaServiceException(LeptaDatabaseException e) {
  }
}
