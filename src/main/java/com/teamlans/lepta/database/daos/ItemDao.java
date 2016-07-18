package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.entities.Item;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;

import java.util.List;

public interface ItemDao {

  Integer addItem(Item newItem) throws LeptaDatabaseException;

  void deleteItem(Integer id) throws LeptaDatabaseException;

  List listItems() throws LeptaDatabaseException;

  void updateItem(Item newItem) throws LeptaDatabaseException;

}
