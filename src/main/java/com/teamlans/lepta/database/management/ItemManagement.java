package com.teamlans.lepta.database.management;

import com.teamlans.lepta.database.entities.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class ItemManagement {
  private static SessionFactory factory;

  public static void main(String[] args) {
    try {
      factory = new Configuration().configure().buildSessionFactory();
    } catch (Exception e) {
      System.err.println("Failed to create sessionFactory object." + e);
      throw new ExceptionInInitializerError(e);
    }

  }

  public Integer addItem(String description, double price) {
    Session session = factory.openSession();
    Transaction tx = null;
    Integer id = null;
    try {
      tx = session.beginTransaction();
      Item item = new Item(description, price);
      id = (Integer) session.save(item);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return id;
  }

  public void deleteItem(Integer id) {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      Item item = (Item) session.get(Item.class, id);
      session.delete(item);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  public void listItems() {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      List items = session.createQuery("FROM Bill").list();
      for (Iterator iterator =
           items.iterator(); iterator.hasNext(); ) {
        Item item = (Item) iterator.next();
        System.out.print("ID: " + item.getId());
        System.out.print("  Description: " + item.getDescription());
        System.out.println("  Price: " + item.getPrice());
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