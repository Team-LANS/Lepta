package com.teamlans.lepta.view.bill.edit.component.items;

import com.teamlans.lepta.entities.Item;
import com.vaadin.data.Validator;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

public class AddItemControl extends HorizontalLayout {

  private ItemNameField itemNameField;

  private ItemPriceField itemItemPriceField;

  private ItemTable itemTable;

  public AddItemControl() {
    itemNameField = new ItemNameField();
    addComponent(itemNameField);
    itemItemPriceField = new ItemPriceField();
    addComponent(itemItemPriceField);
    Button addItemButton = new Button("Add");
    addItemButton.addClickListener(event -> addItem());
    addComponent(addItemButton);
  }

  private void addItem() {
    try{
      itemNameField.validate();
      itemItemPriceField.validate();
    }
    catch (Validator.InvalidValueException e){
      Notification.show(e.getMessage());
      return;
    }
    String name = itemNameField.getValue();
    Double price = Double.parseDouble(itemItemPriceField.getValue());
    itemNameField.clear();
    itemItemPriceField.clear();
    Item item = new Item(name, price);
    itemTable.addItem(item);
  }

  public void setItemTable(ItemTable itemTable) {
    this.itemTable = itemTable;
  }


}
