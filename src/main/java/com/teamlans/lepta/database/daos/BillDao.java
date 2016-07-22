package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.enums.Status;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;

import java.util.List;

public interface BillDao {


  Integer addBill(Bill newBill) throws LeptaDatabaseException;

  void deleteBill(Integer nr) throws LeptaDatabaseException;

  List<Bill> listBills() throws LeptaDatabaseException;

  void updateBill(Bill newBill) throws LeptaDatabaseException;

}
