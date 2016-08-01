package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.entities.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Basic dao implementation for {@link User}. Sessions are managed in the service layer using
 * {@link org.hibernate.Transaction} so no custom exceptions are thrown here.
 * No functionality for removing users is provided, as removing users is only feasible when the
 * whoe database is purged.
 */
@Repository
public class UserDaoImpl implements UserDao {

  private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

  @Autowired
  private SessionFactory factory;

  @Override
  public void addUser(User newUser) {
    logger.debug("Adding user with {}", newUser);
    factory.getCurrentSession().save(newUser);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<User> listUsers() {
    logger.debug("Listing users...");
    return factory.getCurrentSession().createQuery("FROM User").list();
  }

  @Override
  public void updateUser(User newUser) {
    logger.debug("Updating user with {}", newUser);
    Session session = factory.getCurrentSession();
    session.update(newUser);
  }


}
