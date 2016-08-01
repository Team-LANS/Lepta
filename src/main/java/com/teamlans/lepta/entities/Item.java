package com.teamlans.lepta.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM")
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ITEM_ID")
  private int id;

  @Column(name = "DESCRIPTION")
  private String name;

  @Column(name = "PRICE")
  private double price;

  @ManyToOne
  @JoinColumn(name = "BILL_ID")
  private Bill bill;

  @ManyToMany(mappedBy = "items")
  private Set<User> users = new HashSet<>();


  public Item() {
    // needed for hibernate
  }

  public Item(String name, double price, Bill bill) {
    this.name = name;
    this.price = price;
    this.bill = bill;
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

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Bill getBill() {
    return bill;
  }

  public void setBill(Bill bill) {
    this.bill = bill;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void addUser(User user) {
    users.add(user);
  }

  public void removeUsers(Set<User> deletedUsers) {
    users.removeAll(deletedUsers);
  }

}
