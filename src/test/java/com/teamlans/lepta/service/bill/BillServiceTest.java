package com.teamlans.lepta.service.bill;

import com.teamlans.lepta.database.daos.BillDao;
import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.entities.Item;
import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.entities.enums.Color;
import com.teamlans.lepta.entities.enums.Status;
import com.teamlans.lepta.service.validation.EntityValidationService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

  @Mock
  BillDao mockBillDao;

  @Mock
  EntityValidationService validationService;

  private BillService billService;

  @Before
  public void setUp() {
    billService = new BillService();
    billService.setBillDao(mockBillDao);
    billService.setValidationService(validationService);
  }

  @Test
  public void getBillBy_validId_billReturned() {
    when(mockBillDao.listBills()).thenReturn(createDummyBills());

    Bill result = billService.getBill(1);

    assertTrue(result.getId() == 1);
  }


  @Test
  public void listBillsForUser_noBillsForUser_emptyListReturned() {
    when(mockBillDao.listBills()).thenReturn(createDummyBills());

    List<Bill> result = billService.listBillsFor(createDummyUser(3));

    assertTrue(result.isEmpty());
  }

  @Test
  public void listBillsForUser_billsForUser_billsReturned() {
    when(mockBillDao.listBills()).thenReturn(createDummyBills());

    List<Bill> result = billService.listBillsFor(createDummyUser(1));

    assertFalse(result.isEmpty());
  }

  @Test
  public void listNewBillsForUser_noNewBillsExist_emptyListReturned() {
    when(mockBillDao.listBills()).thenReturn(createDummyBills());

    List<Bill> result = billService.listNewBillsFor(createDummyUser(1));

    assertTrue(result.isEmpty());
  }

  @Test
  public void addOrUpdate_validBill_daoCalled() throws Exception {
    Bill bill = new Bill(1, "test", new Date(), new User());
    bill.getItems().add(new Item());

    billService.addOrUpdate(bill);

    verify(mockBillDao).addOrUpdateBill(bill);
  }

  @Test
  public void deleteBill_validBill_daoCalled() throws Exception {
    Bill billToDelete = new Bill(1, "test", new Date(), new User());
    billToDelete.getItems().add(new Item());

    billService.deleteBill(billToDelete);

    verify(mockBillDao).addOrUpdateBill(billToDelete);
    verify(mockBillDao).deleteBill(billToDelete);
  }

  private User createDummyUser(int id) {
    return new User(id, "name", "pwd".getBytes(), "salt".getBytes(), Color.DARK_BLUE);
  }

  private List<Bill> createDummyBills() {
    List<Bill> bills = new ArrayList<>();
    User user1 = createDummyUser(1);
    User user2 = createDummyUser(0);
    Bill bill1 = new Bill(1, "name1", new Date(), user1);
    bill1.getItems().add(new Item());
    bill1.setStatus(Status.ARCHIVED);
    Bill bill2 = new Bill(2, "name2", new Date(), user2);
    bill2.setStatus(Status.ARCHIVED);
    bill2.getItems().add(new Item());
    bills.add(bill1);
    bills.add(bill2);
    return bills;
  }

}
