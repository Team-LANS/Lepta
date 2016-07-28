package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.service.bill.BillService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillDaoImpl implements BillDao {

  private static final Logger logger = LoggerFactory.getLogger(BillService.class);

  @Autowired
  private SessionFactory factory;

  @Override
  public void addBill(Bill bill) {
    logger.debug("Adding bill with {}", bill);
    factory.getCurrentSession().saveOrUpdate(bill);
  }

  @Override
  public void deleteBill(Bill bill) {
    logger.debug("Deleting bill with {}", bill);
    Session session = factory.getCurrentSession();
    session.delete(bill);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Bill> listBills() {
    logger.debug("Listing bill...");
    return factory.getCurrentSession().createQuery("FROM Bill").list();
  }

  @Override
  public void updateBill(Bill newBill) {
    logger.debug("Updating bill with {}", newBill);
    Session session = factory.getCurrentSession();
    session.update(newBill);
  }
}

