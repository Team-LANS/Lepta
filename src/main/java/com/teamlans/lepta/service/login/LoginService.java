package com.teamlans.lepta.service.login;

import com.teamlans.lepta.database.daos.UserDao;
import com.teamlans.lepta.database.daos.UserDaoImpl;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import com.teamlans.lepta.service.exceptions.LeptaLoginException;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;

import java.util.List;

public class LoginService {

  private UserDao userDao;

  public void authenticate(String userName, String password)
      throws LeptaLoginException, LeptaServiceException {
    userDao = new UserDaoImpl();
    if (isValidCombination(userName, password)) {
      loadProtectedResources();
    } else {
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

  private void loadProtectedResources() {
    // TODO: show MainWindow
  }

}
