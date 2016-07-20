package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.HibernateTestConfiguration;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.enums.Color;
import org.dbunit.operation.ExclusiveTransactionException;
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

  @Autowired ItemDao itemDao;

  @Test public void deleteBill_validBill_billDeleted() throws Exception {
    int billCount = billDao.listBills().size();
    billDao.deleteBill(1);
    int newBillCount = billDao.listBills().size();
    assertEquals(billCount -1, newBillCount);
  }

//  @Test public void deleteBill_validBill_cascadeDeleteItems() throws Exception {
//    int itemCount = itemDao.listItems().size();
//    billDao.deleteBill(1);
//    int newItemCount = itemDao.listItems().size();
//    assertTrue(itemCount > newItemCount);
//  }

}
