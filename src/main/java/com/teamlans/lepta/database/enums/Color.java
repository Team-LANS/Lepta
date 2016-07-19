package com.teamlans.lepta.database.enums;

public enum Color {

  RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE;

  public static Color getRandom() {
    return values()[(int) (Math.random() * values().length)];
  }

  public static Color getRandom(Color prohibited) {
    Color randomColor = getRandom();
    while (randomColor == prohibited) {
      randomColor = getRandom();
    }
    return randomColor;
  }

}
