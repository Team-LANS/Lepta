package com.teamlans.lepta.service.accounts;

import com.teamlans.lepta.database.daos.UserDao;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The AccountCreation class validates account details from both templates. If all fields are valid,
 * users are created and added to the database.
 */
@Service public class AccountCreation {

  private UserDao userDao;

  @Autowired public AccountCreation(UserDao userDao) throws LeptaServiceException {
    this.userDao = userDao;
  }

  public void createAccounts(AccountTemplate u0, AccountTemplate u1) throws LeptaServiceException {
    if (u0 == null || u1 == null || u0.equals(u1)) {
      throw new LeptaServiceException("Invalid account templates.");
    }
    try {
      // assign unique ids 0 and 1
      userDao.addUser(new User(0, u0.getName(), u0.getPassword(), u0.getColor()));
      userDao.addUser(new User(1, u1.getName(), u1.getPassword(), u1.getColor()));
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

}
