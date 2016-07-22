package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.HibernateTestConfiguration;
import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.enums.Status;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateTestConfiguration.class,
    DaoConfig.class}, loader = AnnotationConfigContextLoader.class) @Transactional
public class BillDaoImplTest {

  @Autowired BillDao billDao;

  @Autowired SessionFactory sessionFactory;


  @Test public void updateBill_updateStatus_billUpdated() throws Exception {
    Bill bill = billDao.getBillBy(1);
    bill.setStatus(Status.ASSIGNED);
    billDao.updateBill(bill);

    assertEquals(billDao.getBillBy(1).getStatus(), Status.ASSIGNED);
  }

  @Test(expected = LeptaDatabaseException.class) public void updateBill_invalidBill_exceptionThrown() throws Exception {
    Bill bill = new Bill();
    bill.setNr(-1);
    billDao.updateBill(bill);
    sessionFactory.getCurrentSession().getTransaction().commit();
  }


  @Test public void deleteBill_validBill_billDeleted() throws Exception {
    int billCount = billDao.listBills().size();
    billDao.deleteBill(1);
    int newBillCount = billDao.listBills().size();
    assertEquals(billCount - 1, newBillCount);
  }

  @Test(expected = LeptaDatabaseException.class) public void deleteBill_invalidBill_throwsLeptaException()
      throws Exception {
    billDao.deleteBill(-1);
  }

}
