package com.teamlans.lepta.service.bill;

import com.teamlans.lepta.database.daos.BillDao;
import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.entities.enums.Status;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {

  private static final Logger logger = LoggerFactory.getLogger(BillService.class);

  private BillDao billDao;

  public BillService() {
  }

  @Autowired
  public void setBillDao(BillDao billDao) {
    this.billDao = billDao;
  }

  @Transactional
  public Bill getBillBy(int billId) {
    logger.debug("Retrieving bill with id {}", billId);
    return billDao.listBills().stream()
        .filter(x -> x.getId() == billId).findFirst().get();
  }

  @Transactional
  public List<Bill> listBillsFor(User user) {
    logger.debug("Getting bill");
    List<Bill> bills = billDao.listBills();
    return bills.stream().filter(x -> x.getUser().getId() == user.getId()).collect(Collectors.toList());
  }

  @Transactional
  public List<Bill> listNewBillsFor(User user) {
    List<Bill> newBills = listBillsFor(user);
    return newBills.stream().filter(x -> x.getStatus() == Status.NEW).collect(Collectors.toList());
  }


  @Transactional
  public void addOrUpdate(Bill bill) throws LeptaServiceException {
    logger.debug("Adding bill with {}", bill);
    validateBill(bill);
    billDao.addBill(bill);
  }



  @Transactional
  public void deleteBill(Bill bill) {
    logger.debug("Deleting bill {}", bill);
    bill.getItems().clear();
    billDao.updateBill(bill);
    billDao.deleteBill(bill);
  }


  private void validateBill(Bill bill) throws LeptaServiceException {
    if (bill.getUser() == null) {
      throw new LeptaServiceException("Bill user must not be null");
    }
    if (bill.getItems().isEmpty()) {
      throw new LeptaServiceException("Bill items must not be empty");
    }
  }
}
