package com.teamlans.lepta.service.accounts;

import com.teamlans.lepta.database.enums.Color;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;


/**
 * The AccountTemplate class wraps name, password (both entered by users when creating accounts) and
 * color (randomly assigned).
 */
public class AccountTemplate {

  private String name;
  private String password;
  private Color color;

  private AccountTemplate(String name, String password, Color color) throws LeptaServiceException {
    if (name == null || password == null || color == null) {
      throw new LeptaServiceException("Invalid user data.");
    }
    this.name = name;
    this.password = password;
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public Color getColor() {
    return color;
  }

}
