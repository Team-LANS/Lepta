package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Item;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ItemDao {

  Integer addItem(Item newItem);

  void deleteItem(Integer id);

  List<Item> listItems();

  void updateItem(Item newItem);

}
