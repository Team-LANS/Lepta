package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.entities.Item;

import java.util.List;

public interface ItemDao {

  Integer addItem(Item newItem);

  void deleteItem(Integer id);

  List<Item> listItems();

  void updateItem(Item newItem);

}
