package com.teamlans.lepta.database.entities;

import com.teamlans.lepta.database.enums.Status;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BILL")
public class Bill {

  @Id
  @GeneratedValue
  @Column(name = "NR")
  private int nr;

  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private Status status;

  @Column(name = "TIMESTAMP")
  private String timestamp; // will be changed

  @ManyToOne
  @JoinColumn(name = "USER_NAME")
  private User user;

  @OneToMany(mappedBy = "bills")
  private Set<Item> items;

  // why?
  public Bill() {
  }

  public Bill(Status status, String timestamp, User user, Set<Item> items) {
    this.status = status;
    this.timestamp = timestamp;
    this.user = user;
    this.items = items;
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

  public Set<Item> getItems() {
    return items;
  }

  public void setItems(Set<Item> items) {
    this.items = items;
  }
}
