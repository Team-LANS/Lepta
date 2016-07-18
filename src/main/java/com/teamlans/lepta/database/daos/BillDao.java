package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.enums.Status;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;

import java.util.List;

interface BillDao {

  Integer addBill(Status status, String timestamp, User user) throws LeptaDatabaseException;

  void deleteBill(Integer nr) throws LeptaDatabaseException;

  List listBills() throws LeptaDatabaseException;

  void updateBill(Bill newBill) throws LeptaDatabaseException;

}
