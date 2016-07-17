package com.teamlans.lepta.database.entities;

public class Bill {

  private int nr;
  private Status status;
  private String timestamp; // will be changed
  private User user;

  public Bill(int nr, Status status, String timestamp, User user) {
    this.nr = nr;
    this.status = status;
    this.timestamp = timestamp;
    this.user = user;
  }

  public int getNr() {
    return nr;
  }

  public void setNr(int nr) {
    this.nr = nr;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
