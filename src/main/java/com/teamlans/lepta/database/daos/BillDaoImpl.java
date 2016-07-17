package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Item;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.enums.Status;
import com.teamlans.lepta.database.entities.Bill;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BillDaoImpl {
  private static SessionFactory factory;

  public static void main(String[] args) {
    try {
      factory = new Configuration().configure().buildSessionFactory();
    } catch (Exception e) {
      System.err.println("Failed to create sessionFactory object." + e);
      throw new ExceptionInInitializerError(e);
    }

  }

  public Integer addBill(Status status, String timestamp, User user, Set<Item> items) {
    Session session = factory.openSession();
    Transaction tx = null;
    Integer nr = null;
    try {
      tx = session.beginTransaction();
      Bill bill = new Bill(status, timestamp, user, items);
      nr = (Integer) session.save(bill);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return nr;
  }

  public void deleteBill(Integer billNr) {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      Bill bill = (Bill) session.get(Bill.class, billNr);
      session.delete(bill);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  public void listBills() {
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      List bills = session.createQuery("FROM Bill").list();
      for (Iterator iterator =
           bills.iterator(); iterator.hasNext(); ) {
        Bill bill = (Bill) iterator.next();
        System.out.print("BillNr: " + bill.getNr());
        System.out.print("  Status: " + bill.getStatus());
        System.out.println("  Timestamp: " + bill.getTimestamp());
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