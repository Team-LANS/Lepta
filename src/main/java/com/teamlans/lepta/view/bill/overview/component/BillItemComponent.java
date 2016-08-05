package com.teamlans.lepta.view.bill.overview.component;

import com.teamlans.lepta.entities.Item;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class BillItemComponent extends HorizontalLayout {

  private Label nameLabel;

  private Label priceLabel;

  public BillItemComponent(Item item){
    nameLabel = new Label(item.getName());
    addComponent(nameLabel);
    priceLabel = new Label(String.valueOf(item.getPrice()));
    addComponent(priceLabel);
  }
}
