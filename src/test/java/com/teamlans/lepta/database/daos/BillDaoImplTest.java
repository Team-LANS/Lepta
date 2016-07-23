package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.HibernateTestConfiguration;
import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.enums.Status;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateTestConfiguration.class,
    DaoConfig.class}, loader = AnnotationConfigContextLoader.class) @Transactional
public class BillDaoImplTest {

  @Autowired BillDao billDao;

  @Autowired UserDao userDao;

  @Autowired SessionFactory sessionFactory;


  @Test public void createBill_validBill_billCreated() throws Exception {
    int billCount = billDao.listBills().size();
    Bill bill = new Bill("name",new Date(), userDao.listUsers().get(0));
    billDao.addBill(bill);
    int newBillCount = billDao.listBills().size();
    assertEquals(billCount + 1, newBillCount);
  }

  @Test public void updateBill_updateStatus_billUpdated() throws Exception {
    Bill bill = billDao.listBills().get(0);
    bill.setStatus(Status.ASSIGNED);
    billDao.updateBill(bill);
    sessionFactory.getCurrentSession().flush();
    Bill updatedBill = billDao.listBills().get(0);
    assertEquals(updatedBill.getStatus(), Status.ASSIGNED);
  }

  @Test public void deleteBill_validBill_billDeleted() throws Exception {
    int billCount = billDao.listBills().size();
    billDao.deleteBill(1);
    int newBillCount = billDao.listBills().size();
    assertEquals(billCount - 1, newBillCount);
  }


}
