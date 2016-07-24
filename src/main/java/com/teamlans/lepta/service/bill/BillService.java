package com.teamlans.lepta.service.bill;

import com.teamlans.lepta.database.daos.BillDao;
import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service public class BillService {

  private static final Logger logger = LoggerFactory.getLogger(BillService.class);

  private BillDao billDao;

  @Autowired public BillService(BillDao billDao) {
    this.billDao = billDao;
  }

  @Transactional
  public List<Bill> listBills()  {
    logger.debug("Getting bill");
    return billDao.listBills();
  }

  @Transactional public void addBill(Bill bill) throws LeptaServiceException {
    logger.debug("Adding bill with {}", bill);
    validateBill(bill);
    billDao.addBill(bill);
  }

  private void validateBill(Bill bill) throws LeptaServiceException {
      if(bill.getUser() == null) {
        throw new LeptaServiceException("Bill user must not be null");
      }
      if(bill.getItems().isEmpty()) {
        throw new LeptaServiceException("Bill items must not be empty");
      }
  }

}
