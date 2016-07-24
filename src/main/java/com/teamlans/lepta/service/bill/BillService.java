package com.teamlans.lepta.service.bill;

import com.teamlans.lepta.database.daos.BillDao;
import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service public class BillService {

  private BillDao billDao;

  @Autowired public BillService(BillDao billDao) throws LeptaDatabaseException {
    this.billDao = billDao;
  }

  @Transactional
  public List<Bill> listBills() throws LeptaServiceException {
    try {
      return billDao.listBills();
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

  @Transactional public void addBill(Bill bill) throws LeptaServiceException {
    validateBill(bill);
    try {
      billDao.addBill(bill);
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

  public void validateBill(Bill bill) throws LeptaServiceException {
      if(bill.getUser() == null) {
        throw new LeptaServiceException("Bill user must not be null");
      }
      if(bill.getItems().isEmpty()) {
        throw new LeptaServiceException("Bill items must not be empty");
      }
  }

}
