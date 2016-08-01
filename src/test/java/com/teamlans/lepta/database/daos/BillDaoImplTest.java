package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.HibernateTestConfiguration;
import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.entities.Item;
import com.teamlans.lepta.entities.enums.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateTestConfiguration.class,
    DaoConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class BillDaoImplTest {

  @Autowired
  BillDao billDao;

  @Autowired
  UserDao userDao;

  @Autowired
  ItemDao itemDao;

  @Test
  public void addOrUpdateBill_newBill_billCreated() throws Exception {
    int billCount = billDao.listBills().size();
    Bill bill = new Bill("name", new Date(), userDao.listUsers().get(0));
    billDao.addOrUpdateBill(bill);
    int newBillCount = billDao.listBills().size();
    assertEquals(billCount + 1, newBillCount);
  }

  @Test
  public void addOrUpdateBill_updateStatus_billUpdated() throws Exception {
    Bill bill = billDao.listBills().get(0);
    bill.setStatus(Status.ASSIGNED);

    billDao.addOrUpdateBill(bill);

    Bill updatedBill = billDao.listBills().get(0);
    assertEquals(updatedBill.getStatus(), Status.ASSIGNED);
  }

  @Test
  public void addOrUpdateBill_updateItems_itemsRemoved() throws Exception {
    Bill bill = billDao.listBills().get(0);
    int itemCount = itemDao.listItems().size();

    bill.getItems().clear();
    billDao.addOrUpdateBill(bill);

    int newItemCount = itemDao.listItems().size();
    assertTrue(itemCount > newItemCount);
  }

  @Test
  public void addOrUpdateBill_updateItemInBill_itemUpdated() throws Exception {
    Bill bill = billDao.listBills().get(0);
    Item item = new ArrayList<>(bill.getItems()).get(0);

    item.setName("New Description");

    billDao.addOrUpdateBill(bill);

    Bill newBill = billDao.listBills().get(0);
    Item updatedItem = new ArrayList<>(newBill.getItems()).get(0);
    assertEquals(updatedItem.getName(), "New Description");
  }

  @Test
  public void deleteBill_validBill_billDeleted() throws Exception {
    int billCount = billDao.listBills().size();
    Bill bill = billDao.listBills().get(0);

    billDao.deleteBill(bill);

    int newBillCount = billDao.listBills().size();
    assertEquals(billCount - 1, newBillCount);
  }

  @Test
  public void deleteBill_validBill_itemsDeleted() throws Exception {
    int itemCount = itemDao.listItems().size();
    Bill bill = billDao.listBills().get(0);

    billDao.deleteBill(bill);

    int newItemCount = itemDao.listItems().size();
    assertTrue(itemCount > newItemCount);
  }

  @Test(expected = PersistenceException.class)
  public void deleteBill_assignedBill_exceptionThrown() throws Exception {
    int itemCount = itemDao.listItems().size();
    Bill bill = getFirstAssignedBill();

    billDao.deleteBill(bill);

    int newItemCount = itemDao.listItems().size();
    assertTrue(itemCount > newItemCount);
  }

  private Bill getFirstAssignedBill() throws Exception {
    List<Bill> bills = billDao.listBills();
    for(Bill bill : bills){
      if(bill.getStatus() == Status.ASSIGNED){
        return bill;
      }
    }
    throw new Exception("No assigned bill found");
  }


}
