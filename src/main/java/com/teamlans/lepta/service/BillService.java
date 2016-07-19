package com.teamlans.lepta.service;

import com.teamlans.lepta.database.daos.BillDao;
import com.teamlans.lepta.database.daos.BillDaoImpl;
import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.enums.Color;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hans-Joerg Schroedl
 */
@Service public class BillService {


  BillDao billDao;

  @Autowired
  public BillService(BillDao billDao) throws LeptaDatabaseException {
    this.billDao = billDao;
  }

  public List<Bill> listBills() throws LeptaServiceException {
    try {
      return billDao.listBills();
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }

  public void addBill() throws LeptaServiceException {
    try {
      billDao.addBill(new Bill("123", new User(0, "test", Color.BLUE, "passwd")));
    } catch (LeptaDatabaseException e) {
      throw new LeptaServiceException(e);
    }
  }
}
