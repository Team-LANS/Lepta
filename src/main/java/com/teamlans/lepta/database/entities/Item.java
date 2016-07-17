package com.teamlans.lepta.database.entities;

public class Item {

  private int id;
  private String description;
  private double price;
  private Bill bill;

  public Item(int id, String description, double price, Bill bill) {
    this.id = id;
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
}
