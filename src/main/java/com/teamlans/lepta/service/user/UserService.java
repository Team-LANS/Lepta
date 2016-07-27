package com.teamlans.lepta.service.user;

import com.teamlans.lepta.database.daos.UserDao;
import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.entities.enums.Color;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import com.teamlans.lepta.service.exceptions.LeptaLoginException;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Validates everything related to users: Throws a LeptaLoginException if a given username-password
 * combination is invalid. Checks if given credentials are valid and adds valid account pairs to the
 * data base. Checks if there are users in the database.
 */
@Service
public class UserService {

  @Autowired
  private UserDao userDao;

  public User authenticate(String userName, String password)
      throws LeptaLoginException, LeptaServiceException {
    try {
      List<User> users = userDao.listUsers();
      for (User user : users) {
        if (user.getName().equals(userName) && user.getPassword().equals(password)) {
          return user;
        }
      }
      throw new LeptaLoginException("Login failed!");
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

  public boolean noUsersExist() {
    try {
      return userDao.listUsers().isEmpty();
    } catch (LeptaDatabaseException e) {
      // TODO: decide what to do here
      return true;
    }
  }

  @Transactional
  public void createAccounts(Credentials account0, Credentials account1)
      throws LeptaServiceException {
    if (account0 == null || account1 == null || account0.equals(account1)) {
      throw new LeptaServiceException("Invalid account templates.");
    }
    try {
      // assign unique ids (0 and 1) and initial colors (blue and yellow)
      userDao.addUser(new User(0, account0.getName(), account0.getPassword(), Color.DARK_BLUE));
      userDao.addUser(new User(1, account1.getName(), account1.getPassword(), Color.YELLOW));
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

}
