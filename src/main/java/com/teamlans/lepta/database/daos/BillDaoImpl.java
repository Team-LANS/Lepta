package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.entities.Item;
import com.teamlans.lepta.database.enums.Status;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class BillDaoImpl implements BillDao {

  @Autowired
  private SessionFactory factory;

  public Integer addBill(Bill newBill) throws LeptaDatabaseException {
    return (Integer) factory.getCurrentSession().save(newBill);
  }

  public void deleteBill(Integer nr) throws LeptaDatabaseException {
    Session session = factory.getCurrentSession();
    Bill bill = session.get(Bill.class, nr);
    session.delete(bill);
  }

  @SuppressWarnings("unchecked")
  public List<Bill> listBills() throws LeptaDatabaseException {
    return factory.getCurrentSession().createQuery("FROM Bill").list();
  }

  public void updateBill(Bill newBill) throws LeptaDatabaseException {
    Session session = factory.getCurrentSession();

    Integer nr = newBill.getNr();
    Bill bill = session.get(Bill.class, nr);

    Status newStatus = newBill.getStatus();
    bill.setStatus(newStatus);

    Date newTimestamp = newBill.getDate();
    bill.setDate(newTimestamp);

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
  }
}

