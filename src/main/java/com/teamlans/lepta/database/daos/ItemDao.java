package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.entities.Item;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;

import java.util.List;

interface ItemDao {

  Integer addItem(String description, double price, Bill bill) throws LeptaDatabaseException;

  void deleteItem(Integer id) throws LeptaDatabaseException;

  List listItems() throws LeptaDatabaseException;

  void updateItem(Item newItem) throws LeptaDatabaseException;

}
