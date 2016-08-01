package com.teamlans.lepta.service.bill;

import com.teamlans.lepta.database.daos.BillDao;
import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.entities.enums.Status;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.validation.EntityValidationService;
import com.teamlans.lepta.service.validation.ValidatableBill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * This class provides access to CRUD functionality regarding the {@link Bill} entity.
 * The service should be used in GUI components to retrieve data from the database and
 * update modified data.
 * When persisting data, bills are validated using {@link EntityValidationService}
 */
@Service
public class BillService {

  private static final Logger logger = LoggerFactory.getLogger(BillService.class);

  private BillDao billDao;
  private EntityValidationService validationService;

  @Autowired
  public void setBillDao(BillDao billDao) {
    this.billDao = billDao;
  }

  @Autowired
  public void setValidationService(EntityValidationService validationService) {

    this.validationService = validationService;
  }

  @Transactional
  public Bill getBill(int billId) {
    logger.debug("Retrieving bill with id {}", billId);
    List<Bill> bills = billDao.listBills();
    for (Bill bill : bills) {
      if (bill.getId() == billId) {
        return bill;
      }
    }
    throw new DataRetrievalFailureException("Bill with id " + billId + "not found");
  }

  @Transactional
  public List<Bill> listBillsFor(User user) {
    logger.debug("Listing bills for user {}", user);
    List<Bill> bills = billDao.listBills();
    List<Bill> billsForUser = new ArrayList<>();
    for (Bill bill : bills) {
      if (bill.getUser().getId() == user.getId()) {
        billsForUser.add(bill);
      }
    }
    return billsForUser;
  }

  @Transactional
  public List<Bill> listNewBillsFor(User user) {
    logger.debug("Listing new bills for user {}", user);
    List<Bill> billsForUser = listBillsFor(user);
    List<Bill> newBills = new ArrayList<>();
    for (Bill bill : billsForUser) {
      if (bill.getStatus() == Status.NEW) {
        newBills.add(bill);
      }
    }
    return newBills;
  }


  @Transactional
  public void addOrUpdate(Bill bill) throws LeptaServiceException {
    logger.debug("Adding bill with {}", bill);
    validateBill(bill);
    billDao.addOrUpdateBill(bill);
  }

  @Transactional
  public void deleteBill(Bill bill) throws LeptaServiceException {
    logger.debug("Deleting bill {}", bill);
    validateBill(bill);
    bill.getItems().clear();
    billDao.addOrUpdateBill(bill);
    billDao.deleteBill(bill);
  }

  private void validateBill(Bill bill) throws LeptaServiceException {
    validationService.validate(new ValidatableBill(bill));
  }
}
