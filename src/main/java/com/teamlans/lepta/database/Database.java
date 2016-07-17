package com.teamlans.lepta.database;

import com.teamlans.lepta.database.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class Database {
  private static SessionFactory factory;

  public static void main(String[] args) {
    try {
      factory = new Configuration().configure().buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println("Failed to create sessionFactory object." + ex);
      throw new ExceptionInInitializerError(ex);
    }

    // add users
    addUser("Anton", "blue", "abcde");
    addUser("Berta", "green", "abcde");
    addUser("Caesar", "green", "abcde");
    addUser("Dora", "red", "12345");

    listUsers();

  }

  /* Method to CREATE an employee in the database */
  public static void addUser(String name, String color, String password) {
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
  /* Method to  READ all the employees */
  public static void listUsers( ){
    Session session = factory.openSession();
    Transaction tx = null;
    try{
      tx = session.beginTransaction();
      List users = session.createQuery("FROM User").list();
      for (Iterator iterator =
           users.iterator(); iterator.hasNext();){
        User user = (User) iterator.next();
        System.out.print("Name: " + user.getName());
        System.out.print("  Color: " + user.getColor());
        System.out.println("  Password: " + user.getPassword());
      }
      tx.commit();
    }catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace();
    }finally {
      session.close();
    }
  }
}
