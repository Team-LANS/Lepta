package com.teamlans.lepta.entities;

import com.teamlans.lepta.entities.enums.Color;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {

  @Id // no value generation necessary since there are only two users
  @Column(name = "USER_ID")
  private int id;

  @Column(name = "USER_NAME", unique = true)
  private String name;

  @Column(name = "COLOR", unique = true)
  @Enumerated(EnumType.STRING)
  private Color color;

  @Column(name = "PASSWORD")
  private String password; // TODO: no plaintext

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
  private Set<Bill> bills = new HashSet<>();

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "OWNER",
      joinColumns = {@JoinColumn(name = "USER_ID")},
      inverseJoinColumns = {@JoinColumn(name = "ITEM_ID"),})
  private Set<Item> items = new HashSet<>();

  public User() {
    // needed for hibernate
  }

  public User(int userId, String name, String password, Color color) {
    this.id = userId;
    this.name = name;
    this.color = color;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Bill> getBills() {
    return bills;
  }

  public void addBill(Bill bill) {
    bills.add(bill);
  }

  public void removeBills(Set<Bill> deletedBills) {
    bills.removeAll(deletedBills);
  }

  public Set<Item> getItems() {
    return items;
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public void removeItems(Set<Item> deletedItems) {
    items.removeAll(deletedItems);
  }
}
