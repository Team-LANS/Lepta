package com.teamlans.lepta.view.bill.overview.component;

import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.view.bill.overview.component.overlay.OverlayEvent;
import com.teamlans.lepta.view.bill.overview.component.overlay.OverlayEventListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BillComponent extends VerticalLayout implements OverlayEventListener<BillComponent> {

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
    String formattedDate = new SimpleDateFormat("dd. MM. yyyy").format(bill.getDate());
    date.setValue(formattedDate);
    billOverlayComponent = new BillOverlayComponent(this);
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
    if (Objects.equals(event.getAction(), "EDIT")) {
      notifyListeners(new BillChangeEvent(BillChangeAction.EDIT, this));
    }
    if (Objects.equals(event.getAction(), "DELETE")) {
      notifyListeners(new BillChangeEvent(BillChangeAction.DELETE, this));
    }
  }

  private void notifyListeners(BillChangeEvent changeEvent) {
    for (BillChangeListener listener : billChangeListeners) {
      listener.notify(changeEvent);
    }
  }

}
