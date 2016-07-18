package com.teamlans.lepta.database.entities;

import com.teamlans.lepta.database.enums.Color;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {

  @Id
  @Column(name = "NAME")
  private String name;

  @Column(name = "COLOR")
  @Enumerated(EnumType.STRING)
  private Color color;

  @Column(name = "PASSWORD")
  private String password; //TODO: no plaintext

  @OneToMany(mappedBy = "user")
  private Set<Bill> bills = new HashSet<>();

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "OWNER",
      joinColumns = {@JoinColumn(name = "USER_NAME")},
      inverseJoinColumns = {@JoinColumn(name = "ITEM_ID")})
  private Set<Item> items = new HashSet<>();


  // needed for hibernate
  public User() {
  }

  public User(String name, Color color, String password) {
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

  public void addBill(Bill bill) {
    bills.add(bill);
  }

  public void removeBill(Bill bill) {
    bills.remove(bill);
  }

  public Set<Bill> getBills() {
    return bills;
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public void removeItem(Item item) {
    items.remove(item);
  }

  public Set<Item> getItems() {
    return items;
  }
}
