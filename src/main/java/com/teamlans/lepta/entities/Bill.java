package com.teamlans.lepta.entities;

import com.teamlans.lepta.entities.enums.Status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BILL")
public class Bill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "BILL_ID")
  private int id;
  @Column(name = "BILL_NAME")
  private String name;
  @Column(name = "DATE")
  private Date date;
  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private Status status = Status.NEW;
  @ManyToOne
  @JoinColumn(name = "USER_ID")
  private User user;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "bill", orphanRemoval = true,
      cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE})
  private List<Item> items = new ArrayList<>();

  public Bill() {
    // used when constructing a new bill
  }

  public Bill(String name, Date date, User user) {
    this.name = name;
    this.date = date;
    this.user = user;
  }

  public Bill(int id, String name, Date date, User user){
    //Use this only for unit testing
    this(name,date,user);
    this.id = id;
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

  public List<Item> getItems() {
    return items;
  }

}
