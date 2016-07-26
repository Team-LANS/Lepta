package com.teamlans.lepta.entities.enums;

public enum Color {

  DARK_BLUE("343D4C"),
  LIGHT_BLUE("3B5E98"),
  GREEN("86CB74"),
  YELLOW("F4BF31"),
  ORANGE("CB783A"),
  RED("CA4754");

  private final String hexcode;

  Color(String hexcode) {
    this.hexcode = hexcode;
  }

  public String getHexcode() {
    return hexcode;
  }

}
