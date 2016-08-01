package com.teamlans.lepta.view.bill.edit.component;

import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.entities.Item;
import com.teamlans.lepta.view.bill.edit.component.items.AddItemControl;
import com.teamlans.lepta.view.bill.edit.component.items.ItemCollection;
import com.vaadin.data.Validator;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class BillItemLayout extends VerticalLayout {

  private ItemCollection itemTable;
  private AddItemControl addItemControl;

  public BillItemLayout() {
    setSpacing(true);
    createSubComponents();
  }

  public void initializeWith(Bill bill) {
    addItemControl.setBill(bill);
    itemTable.addItems(bill.getItems());
  }

  private void createSubComponents() {
    itemTable = new ItemCollection();
    addComponent(itemTable);
    setExpandRatio(itemTable, 1);
    addItemControl = new AddItemControl();
    addItemControl.setItemCollection(itemTable);
    addComponent(addItemControl);
  }

  public List<Item> getItems() {
    return itemTable.getItems();
  }

  public void validate() {
    if (itemTable.getItems().isEmpty()) {
      throw new Validator.InvalidValueException("Item list must not be empty!");
    }
  }
}
