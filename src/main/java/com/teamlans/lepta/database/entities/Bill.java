package com.teamlans.lepta.database.entities;

import com.teamlans.lepta.database.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "BILL")
public class Bill {

  @Id
  @GeneratedValue
  @Column(name = "NR")
  private int billNr;

  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private Status status;

  @Column(name = "TIMESTAMP")
  private String timestamp; // will be changed

  public Bill() {
  }

  public Bill(Status status, String timestamp) {
    this.status = status;
    this.timestamp = timestamp;
  }

  public int getBillNr() {
    return billNr;
  }

  public void setBillNr(int nr) {
    this.billNr = billNr;
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
}
