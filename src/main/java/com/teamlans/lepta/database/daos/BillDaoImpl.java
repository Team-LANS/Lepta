package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Item;
import com.teamlans.lepta.database.enums.Status;
import com.teamlans.lepta.database.entities.Bill;
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
public class BillDaoImpl implements BillDao {

  @Autowired private SessionFactory factory;

  public Integer addBill(Bill newBill) throws LeptaDatabaseException {
    Transaction tx = null;
    Integer nr;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      nr = (Integer) session.save(newBill);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException("Transaction failed in addBill.\n" + e.getStackTrace());
    }
    return nr;
  }

  public void deleteBill(Integer nr) throws LeptaDatabaseException {
    Transaction tx = null;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      Bill bill = session.get(Bill.class, nr);
      session.delete(bill);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException("Transaction failed in deleteBill.\n" + e.getStackTrace());
    }
  }

  public List listBills() throws LeptaDatabaseException {
    Transaction tx = null;
    List bills;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      bills = session.createQuery("FROM Bill").list();
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException("Transaction failed in listBills.\n" + e.getStackTrace());
    }
    return bills;
  }

  public void updateBill(Bill newBill) throws LeptaDatabaseException {
    Transaction tx = null;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();

      Integer nr = newBill.getNr();
      Bill bill = session.get(Bill.class, nr);

      Status newStatus = newBill.getStatus();
      bill.setStatus(newStatus);

      String newTimestamp = newBill.getTimestamp();
      bill.setTimestamp(newTimestamp);

      Set<Item> newItems = newBill.getItems();
      Set<Item> items = bill.getItems();
      Set<Item> deletedItems = new HashSet<>();
      for (Item item : items) {
        if (!newItems.contains(item)) {
          deletedItems.add(item);
        }
      }
      bill.removeItems(deletedItems);
      for (Item newItem : newItems) {
        if (!items.contains(newItem)) {
          bill.addItem(newItem);
        }
      }

      session.update(bill);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException("Transaction failed in updateBill.\n" + e.getStackTrace());
    }
  }

}
