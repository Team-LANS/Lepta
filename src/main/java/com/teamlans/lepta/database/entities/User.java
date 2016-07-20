package com.teamlans.lepta.database.entities;

import com.teamlans.lepta.database.enums.Color;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {

  @Id // no value generation necessary since there are only two users
  @Column(name = "U_NR")
  private int userNr;

  @Column(name = "NAME", unique = true)
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
      joinColumns = {@JoinColumn(name = "USER_NR")},
      inverseJoinColumns = {@JoinColumn(name = "ITEM_ID"),})
  private Set<Item> items = new HashSet<>();

  // needed for hibernate
  public User() {
  }

  public User(int userNr, String name, String password, Color color) {
    this.userNr = userNr;
    this.name = name;
    this.color = color;
    this.password = password;
  }

  public int getUserNr() {
    return userNr;
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

  public void removeBills(Set <Bill> deletedBills) {
    bills.removeAll(deletedBills);
  }

  public Set<Item> getItems() {
    return items;
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public void removeItems(Set <Item> deletedItems) {
    items.removeAll(deletedItems);
  }
}
