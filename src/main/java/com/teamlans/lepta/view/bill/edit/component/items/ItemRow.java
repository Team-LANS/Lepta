package com.teamlans.lepta.view.bill.edit.component.items;

import com.teamlans.lepta.entities.Item;
import com.vaadin.data.Validator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

public class ItemRow extends HorizontalLayout {

  private Item item;

  private Button deleteButton;

  private Button editSaveButton;

  private HorizontalLayout infoContainer;

  private ItemNameField itemNameField;

  private ItemPriceField itemPriceField;

  private boolean isEditable;

  public ItemRow(Item item, ItemCollection parent) {
    this.item = item;
    setSizeFull();
    setSpacing(true);
    buildInfoContainer();
    buildEditFields();
    editSaveButton = new Button("Edit");
    editSaveButton.addClickListener(new EditSaveClickListener());
    addComponent(editSaveButton);
    setComponentAlignment(editSaveButton, Alignment.MIDDLE_RIGHT);
    deleteButton = new Button("Delete");
    deleteButton.addClickListener(clickEvent -> parent.delete(this));
    addComponent(deleteButton);
  }

  private void buildEditFields() {
    itemNameField = new ItemNameField();
    itemNameField.setWidth("120px");
    itemPriceField = new ItemPriceField();
    itemPriceField.setWidth("60px");
  }

  private void buildInfoContainer() {
    infoContainer = new HorizontalLayout();
    createInfoContainerLabels();
    addComponent(infoContainer);
  }

  private void createInfoContainerLabels() {
    infoContainer.removeAllComponents();
    Label nameLabel = new Label(item.getName());
    nameLabel.setWidthUndefined();
    infoContainer.addComponent(nameLabel);
    Label priceLabel = new Label(Double.toString(item.getPrice()));
    priceLabel.setWidthUndefined();
    infoContainer.addComponent(priceLabel);
    infoContainer.setComponentAlignment(priceLabel, Alignment.MIDDLE_RIGHT);
    infoContainer.setSizeFull();
    infoContainer.setSpacing(true);
  }

  public Item getItem() {
    return item;
  }

  private void createInfoContainerEditboxes() {
    infoContainer.removeAllComponents();
    itemNameField.setValue(item.getName());
    itemPriceField.setValue(item.getPrice());
    infoContainer.addComponent(itemNameField);
    infoContainer.addComponent(itemPriceField);
    infoContainer.setComponentAlignment(itemPriceField, Alignment.MIDDLE_RIGHT);

  }

  private void edit() {
    isEditable = true;
    editSaveButton.setCaption("Save");
    createInfoContainerEditboxes();
  }

  private void save() {
    try {
      itemNameField.validate();
      itemPriceField.validate();
    } catch (Validator.InvalidValueException e) {
      Notification.show(e.getMessage());
      return;
    }
    item.setName(itemNameField.getValue());
    item.setPrice(Double.parseDouble(itemPriceField.getValue()));
    isEditable = false;
    editSaveButton.setCaption("Edit");
    createInfoContainerLabels();
  }

  private class EditSaveClickListener implements Button.ClickListener {
    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
      if (isEditable) {
        save();
      } else {
        edit();
      }
    }
  }


}
