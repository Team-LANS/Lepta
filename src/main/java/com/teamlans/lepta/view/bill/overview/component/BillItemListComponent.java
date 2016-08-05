package com.teamlans.lepta.view.bill.overview.component;

import com.teamlans.lepta.entities.Item;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class BillItemListComponent extends VerticalLayout {

  public BillItemListComponent(List<Item> items) {
    for (Item item : items) {
      addComponent(new BillItemComponent(item));
    }
  }
}
