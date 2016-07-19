package com.teamlans.lepta.database.entities;

import com.teamlans.lepta.database.enums.Status;

import javax.persistence.*;
import java.util.HashSet;
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
  private Status status = Status.NEW;

  @Column(name = "TIMESTAMP")
  private String timestamp; // TODO: use real timestamps

  @ManyToOne
  @JoinColumn(name = "USER_NR")
  private User user;

  @OneToMany(fetch=FetchType.LAZY, mappedBy = "bill", orphanRemoval = true)
  private Set<Item> items = new HashSet<>();

  // needed for hibernate
  public Bill() {
  }

  public Bill(String timestamp, User user) {
    this.timestamp = timestamp;
    this.user = user;
  }

  public int getNr() {
    return nr;
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

  public void addItem(Item item) {
    items.add(item);
  }

  public void removeItems(Set<Item> deletedItems) {
    items.removeAll(deletedItems);
  }

  public Set<Item> getItems() {
    return items;
  }
}