package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.enums.Color;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Set;

public class UserDaoImpl implements UserDao {
  private static SessionFactory factory;

  public UserDaoImpl() throws LeptaDatabaseException {
    try {
      factory = new Configuration().configure().buildSessionFactory();
    } catch (Exception e) {
      throw new LeptaDatabaseException();
    }
  }

  public void addUser(String name, Color color, String password) throws LeptaDatabaseException {
    Transaction tx = null;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      User user = new User(name, color, password);
      session.save(user);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException();
    }
  }

  public void deleteUser(String name) throws LeptaDatabaseException {
    Transaction tx = null;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      User user = session.get(User.class, name);
      session.delete(user);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException();
    }
  }

  public List listUsers() throws LeptaDatabaseException {
    Transaction tx = null;
    List users;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      users = session.createQuery("FROM User").list();
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException();
    }
    return users;
  }

  public void updateUser(User newUser) throws LeptaDatabaseException {
    Transaction tx = null;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();

      String name = newUser.getName();
      User user = session.get(User.class, name);

      Color newColor = newUser.getColor();
      user.setColor(newColor);

      String newPassword = newUser.getPassword();
      user.setPassword(newPassword);

      Set<Bill> newBills = newUser.getBills();
      Set<Bill> bills = user.getBills();
      for (Bill bill : bills) {
        if (!newBills.contains(bill)) {
          user.removeBill(bill);
        }
      }
      for (Bill newBill : newBills) {
        if (!bills.contains(newBill)) {
          user.addBill(newBill);
        }
      }

      session.update(user);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException();
    }
  }

}
