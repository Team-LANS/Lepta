package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.enums.Color;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;

import java.util.List;

interface UserDao {

  void addUser(String name, Color color, String password) throws LeptaDatabaseException;

  void deleteUser(String name) throws LeptaDatabaseException;

  List listUsers() throws LeptaDatabaseException;

  void updateUser(User newUser) throws LeptaDatabaseException;

}
