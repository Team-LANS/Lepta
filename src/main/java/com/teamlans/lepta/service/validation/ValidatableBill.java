package com.teamlans.lepta.service.validation;

import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.entities.Item;
import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.entities.enums.Status;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

public class ValidatableBill implements ValidatableEntity {


  @NotBlank
  private String name;

  @NotNull
  private Date date;

  @NotNull
  private Status status = Status.NEW;

  @NotEmpty
  private List<Item> items;

  @NotNull
  private User user;

  public ValidatableBill(Bill bill) {
    name = bill.getName();
    date = bill.getDate();
    status = bill.getStatus();
    user = bill.getUser();
    items = bill.getItems();

  }
}
