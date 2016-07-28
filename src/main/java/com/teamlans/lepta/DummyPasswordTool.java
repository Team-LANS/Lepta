package com.teamlans.lepta;

import com.teamlans.lepta.database.daos.UserDao;
import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.service.user.PasswordEncryptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Sets correct dummy passwords and salts.
 */
@Component
public class DummyPasswordTool {

  @Autowired
  private UserDao dao;

  @Autowired
  private PasswordEncryptionService encryptionService;

  @Transactional
  public void setCorrectPassword(User user) {
    try {
      byte[] salt = encryptionService.generateSalt();
      byte[] password = encryptionService.getEncryptedPassword(user.getName().toLowerCase(), salt);

      user.setSalt(salt);
      user.setPassword(password);

      dao.updateUser(user);

    } catch (Exception e) {

    }

  }

}
