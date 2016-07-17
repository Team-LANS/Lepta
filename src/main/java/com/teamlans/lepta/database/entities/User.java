package com.teamlans.lepta.database.entities;

import com.teamlans.lepta.database.enums.Color;

import javax.persistence.*;
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
  private String password;

  @OneToMany(mappedBy = "user")
  private Set<Bill> bills;


  // why?
  public User() {
  }

  public User(String name, Color color, String password, Set<Bill> bills) {
    this.name = name;
    this.color = color;
    this.password = password;
    this.bills = bills;
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

  public void setBills(Set<Bill> bills) {
    this.bills = bills;
  }
}
