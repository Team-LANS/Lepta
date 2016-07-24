package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.entities.Item;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.enums.Color;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

  private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

  @Autowired
  private SessionFactory factory;

  @Override
  public void addUser(User newUser) throws LeptaDatabaseException {
    logger.debug("Adding user with {}", newUser);
    factory.getCurrentSession().save(newUser);
  }

  @Override
  @SuppressWarnings("unchecked")
  @Transactional
  public List<User> listUsers() throws LeptaDatabaseException {
    logger.debug("Listing users...");
    return factory.getCurrentSession().createQuery("FROM User").list();
  }

  @Override
  public void updateUser(User newUser) throws LeptaDatabaseException {
    logger.debug("Updating user with {}", newUser);
    Session session = factory.getCurrentSession();
    session.update(newUser);
  }


}
