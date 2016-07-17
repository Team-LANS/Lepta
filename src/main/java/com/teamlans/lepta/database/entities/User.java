package com.teamlans.lepta.database.entities;

public class User {

  private String name;
  private String color;
  private String password;

  public User() {
  }


  public User(String name, String color, String password) {
    this.name = name;
    this.color = color;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
