package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.service.bill.BillService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillDaoImpl implements BillDao {

  private static final Logger logger = LoggerFactory.getLogger(BillService.class);

  @Autowired
  private SessionFactory factory;

  @Override
  public Integer addBill(Bill newBill) {
    logger.debug("Adding bill with {}", newBill);
    return (Integer) factory.getCurrentSession().save(newBill);
  }

  @Override
  public void deleteBill(Integer id) {
    logger.debug("Deleting bill with id {}", id);
    Session session = factory.getCurrentSession();
    Bill bill = session.get(Bill.class, id);
    if (bill == null) {
      throw new DataRetrievalFailureException("Could not delete bill with id " + id);
    }
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

  @Override
  public Bill getBillBy(Integer id) {
    return factory.getCurrentSession().get(Bill.class, id);
  }
}

