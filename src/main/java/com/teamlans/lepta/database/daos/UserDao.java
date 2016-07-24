package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao {

  void addUser(User newUser) throws LeptaDatabaseException;

  void deleteUser(Integer userNr) throws LeptaDatabaseException;

  List<User> listUsers() throws LeptaDatabaseException;

  void updateUser(User newUser) throws LeptaDatabaseException;

}
