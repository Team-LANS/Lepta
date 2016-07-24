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
  public void deleteUser(Integer userNr) throws LeptaDatabaseException {
    logger.debug("Deleting user with {}", userNr);
    Session session = factory.getCurrentSession();
    User user = session.get(User.class, userNr);
    session.delete(user);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<User> listUsers() throws LeptaDatabaseException {
    logger.debug("Listing users...");
    return factory.getCurrentSession().createQuery("FROM User").list();
  }

  @Override
  public void updateUser(User newUser) throws LeptaDatabaseException {
    logger.debug("Updating user with {}", newUser);
    Session session = factory.getCurrentSession();

    String userNr = newUser.getName();
    User user = session.get(User.class, userNr);

    String newName = newUser.getName();
    user.setName(newName);

    Color newColor = newUser.getColor();
    user.setColor(newColor);

    String newPassword = newUser.getPassword();
    user.setPassword(newPassword);
    removeDeletedBills(newUser, user);
    removeDeletedItems(newUser, user);
    session.update(user);

  }


  private void removeDeletedItems(User newUser, User user) {
    Set<Item> newItems = newUser.getItems();
    Set<Item> items = user.getItems();
    Set<Item> deletedItems = new HashSet<>();
    for (Item item : items) {
      if (!newItems.contains(item)) {
        deletedItems.add(item);
      }
    }
    user.removeItems(deletedItems);
    for (Item newItem : newItems) {
      if (!items.contains(newItem)) {
        user.addItem(newItem);
      }
    }
  }

  private void removeDeletedBills(User newUser, User user) {
    Set<Bill> newBills = newUser.getBills();
    Set<Bill> bills = user.getBills();
    Set<Bill> deletedBills = new HashSet<>();
    for (Bill bill : bills) {
      if (!newBills.contains(bill)) {
        deletedBills.add(bill);
      }
    }
    user.removeBills(deletedBills);
    for (Bill newBill : newBills) {
      if (!bills.contains(newBill)) {
        user.addBill(newBill);
      }
    }
  }

}
