package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.enums.Status;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BillDao {

  Integer addBill(Bill newBill);

  void deleteBill(Integer nr);

  List<Bill> listBills();

  void updateBill(Bill newBill);

}
