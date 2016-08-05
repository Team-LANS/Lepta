package com.teamlans.lepta.view.bill.overview.component;

import com.teamlans.lepta.entities.Bill;

public class BillChangeEvent {

  private BillChangeAction action;
  private BillComponent billComponent;

  public BillChangeEvent(BillChangeAction action, BillComponent billComponent) {
    this.action = action;
    this.billComponent = billComponent;
  }

  public BillChangeAction getAction() {
    return action;
  }

  public BillComponent getBillComponent() {
    return billComponent;
  }

  public Bill getBill() {
    return billComponent.getBill();
  }


}
