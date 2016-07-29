package com.teamlans.lepta.service.user;

import com.teamlans.lepta.database.daos.UserDao;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.entities.enums.Color;
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
  private UserDao dao;

  @Autowired
  private PasswordEncryptionService encryptionService;

  public UserService(UserDao dao, PasswordEncryptionService encryptionService) {
    // needed because of autowired with try-block bug
    this.dao = dao;
    this.encryptionService = encryptionService;
  }

  @Transactional
  public User authenticate(String userName, String plainPassword)
      throws LeptaServiceException {
    try {
      List<User> users = dao.listUsers();
      for (User user : users) {
        if (user.getName().equals(userName) && encryptionService.isValid(plainPassword, user)) {
          return user;
        }
      }
      throw new LeptaLoginException("Login failed!");
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

  @Transactional
  public List<User> listUsers() throws LeptaServiceException {
    try {
      return dao.listUsers();
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

  @Transactional
  public boolean noUsersExist() throws LeptaServiceException {
    try {
      List<User> users = dao.listUsers();
      return users.isEmpty();
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

  @Transactional
  public boolean isTaken(Color color) throws LeptaServiceException {
    try {
      List<User> users = dao.listUsers();
      for (User user : users) {
        if (user.getColor() == color) {
          return true;
        }
      }
      return false;
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

  @Transactional
  public void updateUser(User user) throws LeptaServiceException {
    try {
      dao.updateUser(user);
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

  @Transactional
  public User createAccounts(Credentials account0, Credentials account1)
      throws LeptaServiceException {
    if (!noUsersExist()) {
      throw new LeptaServiceException("Database full");
    } else if (account0 == null || account1 == null || account0.equals(account1)) {
      throw new LeptaServiceException("Invalid account templates.");
    }
    try {
      // assign unique ids (0 and 1) and initial colors (blue and yellow)
      User loggedIn = buildUser(0, account0, Color.DARK_BLUE);
      dao.addUser(loggedIn);
      dao.addUser(buildUser(1, account1, Color.YELLOW));
      return loggedIn;
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

  private User buildUser(int id, Credentials credentials, Color color)
      throws LeptaServiceException {
    byte[] salt = encryptionService.generateSalt();
    byte[] password = encryptionService.getEncryptedPassword(credentials.getPassword(), salt);
    return new User(id, credentials.getName(), password, salt, color);
  }

}
