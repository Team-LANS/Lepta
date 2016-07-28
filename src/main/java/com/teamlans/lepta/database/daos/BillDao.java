package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.entities.Bill;

import java.util.List;

public interface BillDao {

  void addBill(Bill newBill);

  void deleteBill(Bill bill);

  List<Bill> listBills();

  void updateBill(Bill newBill);

}
