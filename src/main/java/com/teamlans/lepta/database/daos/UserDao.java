package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import com.teamlans.lepta.entities.User;

import java.util.List;

public interface UserDao {

  void addUser(User newUser) throws LeptaDatabaseException;

  List<User> listUsers() throws LeptaDatabaseException;

  void updateUser(User newUser) throws LeptaDatabaseException;

}
