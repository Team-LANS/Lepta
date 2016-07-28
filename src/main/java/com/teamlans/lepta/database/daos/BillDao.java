package com.teamlans.lepta.database.daos;

import com.sun.istack.internal.NotNull;
import com.teamlans.lepta.entities.Bill;

import java.util.List;

public interface BillDao {

  void addOrUpdateBill(Bill newBill);

  void deleteBill(Bill bill);

  List<Bill> listBills();
}
