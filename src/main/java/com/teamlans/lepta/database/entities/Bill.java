package com.teamlans.lepta.database.entities;

import com.teamlans.lepta.database.enums.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "BILL") public class Bill {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "NR") private int nr;
  @Column(name = "BILL_NAME") private String name;
  @Column(name = "DATE") private Date date;
  @Column(name = "STATUS") @Enumerated(EnumType.STRING) private Status status = Status.NEW;
  @ManyToOne @JoinColumn(name = "USER_NR") private User user;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill", orphanRemoval = true) private Set<Item>
      items = new HashSet<>();

  // needed for hibernate
  public Bill() {
  }

  public Bill(String name, Date date, User user) {
    this.name = name;
    this.date = date;
    this.user = user;
  }

  public int getNr() {
    return nr;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date timestamp) {
    this.date = timestamp;
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
