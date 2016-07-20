package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.entities.Item;
import com.teamlans.lepta.database.enums.Color;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  private  SessionFactory factory;

  @Override
  public void addUser(User newUser) throws LeptaDatabaseException {
    Transaction tx = null;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      session.save(newUser);
      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException("Transaction failed in addUser.\n", e);
    }
  }

  @Override
  public void deleteUser(Integer userNr) throws LeptaDatabaseException {
    Transaction tx = null;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      User user = session.get(User.class, userNr);
      if(user == null) {
        throw new LeptaDatabaseException("Invalid user id");
      }
      user.getItems().clear();
      session.delete(user);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException("Transaction failed in deleteUser.\n", e);
    }
  }

  @Override
  public List<User> listUsers() throws LeptaDatabaseException {
    Transaction tx = null;
    List<User> users;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      users = session.createQuery("FROM User").list();
      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException("Transaction failed in listUsers.\n", e);
    }
    return users;
  }

  @Override
  public void updateUser(User newUser) throws LeptaDatabaseException {
    Transaction tx = null;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();

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
      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException("Transaction failed in updateUser.\n", e);
    }
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
