package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Item;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface ItemDao {

  Integer addItem(Item newItem) throws LeptaDatabaseException;

  void deleteItem(Integer id) throws LeptaDatabaseException;

  List listItems() throws LeptaDatabaseException;

  void updateItem(Item newItem) throws LeptaDatabaseException;

}
