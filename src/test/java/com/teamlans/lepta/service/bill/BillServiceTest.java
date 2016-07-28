package com.teamlans.lepta.service.bill;

import com.teamlans.lepta.database.daos.BillDao;
import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.entities.enums.Color;
import com.teamlans.lepta.entities.enums.Status;

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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

  @Mock
  BillDao billDao;
  private BillService billService;

  @Before
  public void setUp() {
    billService = new BillService();
    billService.setBillDao(billDao);
  }

  @Test
  public void getBillBy_validId_billReturned() {
    when(billDao.listBills()).thenReturn(createDummyBills());

    Bill result = billService.getBillBy(1);

    assertTrue(result.getId() == 1);
  }


  @Test
  public void listBillsForUser_noBillsForUser_emptyListReturned() {
    when(billDao.listBills()).thenReturn(createDummyBills());

    List<Bill> result = billService.listBillsFor(new User(3, "name", "pwd", Color.LIGHT_BLUE));

    assertTrue(result.isEmpty());
  }

  @Test
  public void listBillsForUser_billsForUser_billsReturned() {
    when(billDao.listBills()).thenReturn(createDummyBills());

    List<Bill> result = billService.listBillsFor(new User(1, "name", "pwd", Color.LIGHT_BLUE));

    assertFalse(result.isEmpty());
  }

  @Test
  public void listNewBillsForUser_noNewBillsExist_emptyListReturned() {
    when(billDao.listBills()).thenReturn(createDummyBills());

    List<Bill> result = billService.listNewBillsFor(new User(1, "name", "pwd", Color.LIGHT_BLUE));

    assertTrue(result.isEmpty());
  }


  private List<Bill> createDummyBills() {
    List<Bill> bills = new ArrayList<>();
    User user1 = new User(1, "name", "pwd", Color.DARK_BLUE);
    User user2 = new User(0, "name", "pwd", Color.GREEN);
    Bill bill1 = new Bill(1,"name1", new Date(), user1);
    bill1.setStatus(Status.ARCHIVED);
    Bill bill2 = new Bill(2,"name2", new Date(), user2);
    bill2.setStatus(Status.ARCHIVED);
    bills.add(bill1);
    bills.add(bill2);
    return bills;
  }

}
