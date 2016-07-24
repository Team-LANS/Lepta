package com.teamlans.lepta.view.bills.create;

import com.teamlans.lepta.database.entities.Item;
import com.vaadin.ui.*;
import org.vaadin.ui.NumberField;

import java.util.ArrayList;
import java.util.List;

public class BillItemList extends VerticalLayout {

  private Table itemTable;

  private TextField itemName;

  private NumberField itemPrice;

  private List<Item> itemList;

  public BillItemList(){
    this.setSpacing(true);
    itemList = new ArrayList<>();
    createItemTable();
  }

  private void createItemTable() {
    itemTable = new Table();
    itemTable.setHeight("300px");
    itemTable.addContainerProperty("Name", String.class, null);
    itemTable.addContainerProperty("Price", Double.class, null);
    itemTable.setWidth("100%");
    this.addComponent(itemTable);
    this.setExpandRatio(itemTable, 1);
    createItemInput();
  }

  private void createItemInput() {
    HorizontalLayout itemInputContainer = new HorizontalLayout();
    itemInputContainer.setSpacing(true);
    itemName = new TextField();
    itemInputContainer.addComponent(itemName);
    itemPrice = new NumberField();
    itemInputContainer.addComponent(itemPrice);
    Button addItem = new Button("Add");
    addItem.addClickListener(event -> addItem());
    itemInputContainer.addComponent(addItem);
    this.addComponent(itemInputContainer);
  }

  private void addItem() {
    String name = itemName.getValue();
    Double price = Double.parseDouble(itemPrice.getValue());
    Item item = new Item(name, price);
    itemList.add(item);
    itemTable.addItem(new Object[] {item.getDescription(), item.getPrice()},itemTable.getId());
    itemName.clear();
    itemPrice.clear();
  }

  public List<Item> getBillItems() {
    return itemList;
  }

}
