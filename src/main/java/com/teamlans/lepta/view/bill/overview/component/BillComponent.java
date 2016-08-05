package com.teamlans.lepta.view.bill.overview.component;

import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.view.bill.overview.component.overlay.OverlayAction;
import com.teamlans.lepta.view.bill.overview.component.overlay.OverlayEvent;
import com.teamlans.lepta.view.bill.overview.component.overlay.OverlayEventListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class BillComponent extends VerticalLayout implements OverlayEventListener {

  private Label nameLabel;

  private Label date;

  private BillItemListComponent billItemList;

  private BillOverlayComponent billOverlayComponent;

  private List<BillChangeListener> billChangeListeners;

  private Bill bill;

  public BillComponent(Bill bill) {
    this.bill = bill;
    billChangeListeners = new ArrayList<>();
    nameLabel = new Label();
    addComponent(nameLabel);
    date = new Label();
    addComponent(date);
    billItemList = new BillItemListComponent(bill.getItems());
    addComponent(billItemList);
    nameLabel.setValue(bill.getName());
    date.setValue(String.valueOf(bill.getDate()));
    billOverlayComponent = new BillOverlayComponent();
    billOverlayComponent.addOverlayListener(this);
    addComponent(billOverlayComponent);
  }

  public Bill getBill() {
    return bill;
  }

  public void addBillChangeListener(BillChangeListener changeListener) {
    billChangeListeners.add(changeListener);
  }

  @Override
  public void notify(OverlayEvent event) {
    if (event.getAction() == OverlayAction.EDIT) {
      notifyListeners(new BillChangeEvent(BillChangeAction.EDIT, this));
    }
    if (event.getAction() == OverlayAction.DELETE) {
      notifyListeners(new BillChangeEvent(BillChangeAction.DELETE, this));
    }
  }

  private void notifyListeners(BillChangeEvent changeEvent) {
    for (BillChangeListener listener : billChangeListeners) {
      listener.notify(changeEvent);
    }
  }



}
