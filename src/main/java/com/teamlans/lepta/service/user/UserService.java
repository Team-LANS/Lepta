package com.teamlans.lepta.service.user;

import com.teamlans.lepta.database.daos.UserDao;
import com.teamlans.lepta.database.daos.UserDaoImpl;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.enums.Color;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import com.teamlans.lepta.service.exceptions.LeptaLoginException;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;

import java.util.List;


/**
 * Throws a LeptaLoginException if the given user name and password combination is invalid. Checks
 * if given credentials are valid and adds valid account pairs to the data base.
 */
public class UserService {

  private UserDao userDao;

  public void authenticate(String userName, String password)
      throws LeptaLoginException, LeptaServiceException {
    userDao = new UserDaoImpl();
    if (!isValidCombination(userName, password)) {
      throw new LeptaLoginException("Login failed!");
    }
  }

  private boolean isValidCombination(String userName, String password)
      throws LeptaServiceException {
    try {
      List<User> users = userDao.listUsers();
      for (User user : users) {
        if (user.getName().equals(userName)) {
          return user.getPassword().equals(password);
        }
      }
      return false;
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

  public void createAccounts(Credentials account0, Credentials account1)
      throws LeptaServiceException {
    if (account0 == null || account1 == null || account0.equals(account1)) {
      throw new LeptaServiceException("Invalid account templates.");
    }
    try {
      // assign unique ids (0 and 1) and initial colors (blue and yellow)
      userDao = new UserDaoImpl();
      userDao.addUser(new User(0, account0.getName(), account0.getPassword(), Color.BLUE));
      userDao.addUser(new User(1, account1.getName(), account1.getPassword(), Color.YELLOW));
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

}
