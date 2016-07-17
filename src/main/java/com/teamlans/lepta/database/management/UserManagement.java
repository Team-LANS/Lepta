package com.teamlans.lepta.database.management;

import com.teamlans.lepta.database.enums.Color;
import com.teamlans.lepta.database.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class UserManagement {
  private static SessionFactory factory;

  public static void main(String[] args) {
    try {
      factory = new Configuration().configure().buildSessionFactory();
    } catch (Exception e) {
      System.err.println("Failed to create sessionFactory object." + e);
      throw new ExceptionInInitializerError(e);
    }

  }

  public void addUser(String name, Color color, String password) {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      User user = new User(name, color, password);
      session.save(user);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  public void deleteUser(String name) {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      User user = (User) session.get(User.class, name);
      session.delete(user);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  public void listUsers() {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      List users = session.createQuery("FROM User").list();
      for (Iterator iterator =
           users.iterator(); iterator.hasNext(); ) {
        User user = (User) iterator.next();
        System.out.print("Name: " + user.getName());
        System.out.print("  Color: " + user.getColor());
        System.out.println("  Password: " + user.getPassword());
      }
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }
}
