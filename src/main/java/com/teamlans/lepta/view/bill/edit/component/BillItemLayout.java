package com.teamlans.lepta.view.bill.edit.component;

import com.teamlans.lepta.entities.Item;
import com.teamlans.lepta.view.bill.edit.component.items.AddItemControl;
import com.teamlans.lepta.view.bill.edit.component.items.ItemTable;
import com.vaadin.data.Validator;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class BillItemLayout extends VerticalLayout {

  private ItemTable itemTable;

  private AddItemControl addItemControl;

  public BillItemLayout() {
    setSpacing(true);
    createSubComponents();
  }

  private void createSubComponents() {
    itemTable = new ItemTable();
    itemTable.setHeight("300px");
    itemTable.setWidth("100%");
    itemTable.setVisibleColumns("name", "price");
    addComponent(itemTable);
    setExpandRatio(itemTable, 1);
    addItemControl = new AddItemControl();
    addItemControl.setItemTable(itemTable);
    addComponent(addItemControl);
  }

  public List<Item> getItems() {
    return itemTable.getItems();
  }

  public void addItems(List<Item> items) {
    itemTable.addItems(items);
  }

  public void validate() {
    if (itemTable.getItems().isEmpty()) {
      throw new Validator.InvalidValueException("Item list must not be empty!");
    }
  }
}
