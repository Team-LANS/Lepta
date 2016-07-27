package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.entities.Bill;

import java.util.List;

public interface BillDao {

  Integer addBill(Bill newBill);

  void deleteBill(Integer nr);

  List<Bill> listBills();

  void updateBill(Bill newBill);

  Bill getBillBy(Integer nr);
}
