package com.teamlans.lepta.view.bills.create;

import com.teamlans.lepta.database.entities.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;
import org.vaadin.ui.NumberField;

import java.util.ArrayList;
import java.util.List;

class BillItemList extends VerticalLayout {

  private Table itemTable;

  private TextField nameField;

  private NumberField priceField;

  private List<Item> itemList;

  BillItemList() {
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
    createItemNameField();
    itemInputContainer.addComponent(nameField);
    createItemPriceField(itemInputContainer);
    Button addItem = new Button("Add");
    addItem.addClickListener(event -> tryAddItem());
    addItem.setWidth("20%");
    itemInputContainer.addComponent(addItem);
    this.addComponent(itemInputContainer);
  }

  private void createItemPriceField(HorizontalLayout itemInputContainer) {
    priceField = new NumberField();
    priceField.setNegativeAllowed(false);
    priceField.setWidth("30%");
    priceField.setDecimalPrecision(2);
    priceField.setMinValue(0.01);
    priceField.setValidationVisible(false);
    priceField.setNullRepresentation("");
    priceField.setErrorText("Invalid number. Number must be greater than 0.01");
    priceField.addValidator(new StringLengthValidator("Price must not be empty", 1, null, true));
    priceField.setValidationVisible(false);
    itemInputContainer.addComponent(priceField);
  }

  private void createItemNameField() {
    nameField = new TextField();
    nameField.addValidator(new StringLengthValidator("Name must not be empty", 1, 50, true));
    nameField.setNullRepresentation("");
    nameField.setValidationVisible(false);
    nameField.setInputPrompt("Enter item name");
  }

  private void tryAddItem() {
    if (validateFields())
      return;
    addItem();
  }

  private void addItem() {
    String name = nameField.getValue();
    Double price = Double.parseDouble(priceField.getValue());
    Item item = new Item(name, price);
    itemList.add(item);
    itemTable.addItem(new Object[] {item.getDescription(), item.getPrice()}, itemTable.getId());
    nameField.clear();
    priceField.clear();
  }

  private boolean validateFields() {
    nameField.setValidationVisible(false);
    priceField.setValidationVisible(false);
    try {
      nameField.validate();
      priceField.validate();
    } catch (Validator.InvalidValueException e) {
      Notification.show(e.getMessage());
      nameField.setValidationVisible(true);
      priceField.setValidationVisible(true);
      return true;
    }
    return false;
  }

  List<Item> getBillItems() {
    return itemList;
  }

}
