package com.teamlans.lepta.database.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ITEM")
public class Item {

  @Id
  @GeneratedValue
  @Column(name = "ID")
  private int id;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "PRICE")
  private double price;

  @ManyToOne
  @JoinColumn(name = "BILL_NR")
  private Bill bill;

  @ManyToMany(mappedBy = "items")
  private Set<User> users = new HashSet<>();



  // why?
  public Item() {}

  public Item(String description, double price, Bill bill) {
    this.description = description;
    this.price = price;
    this.bill = bill;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

}
