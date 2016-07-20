package com.teamlans.lepta.service.login;

import com.teamlans.lepta.database.daos.UserDao;
import com.teamlans.lepta.database.daos.UserDaoImpl;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;


/**
 * The AccountCreation class validates account details from both templates. If all fields are valid,
 * users are created and added to the database.
 */
public class AccountCreation {

  private UserDao userDao;

  public AccountCreation() throws LeptaServiceException {
    try {
      userDao = new UserDaoImpl();
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

  public void createAccounts(AccountTemplate u0, AccountTemplate u1) throws LeptaServiceException {
    if (u0 == null || u1 == null || u0.equals(u1)) {
      throw new LeptaServiceException("Invalid account templates.");
    }
    try {
      // assign unique ids 0 and 1
      userDao.addUser(new User(0, u0.getName(), u0.getPassword(), u0.getColor()));
      userDao.addUser(new User(1, u1.getName(), u1.getPassword(), u1.getColor()));
    }
    catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

}
