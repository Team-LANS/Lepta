package com.teamlans.lepta.view.bill.edit.component.items;

import com.teamlans.lepta.entities.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class ItemRow extends HorizontalLayout {

  private Item item;

  private Button deleteButton;

  private Button editSaveButton;

  private HorizontalLayout infoContainer;

  public ItemRow(Item item){
    this.item = item;
    Label nameLabel = new Label(item.getName());
    infoContainer = new HorizontalLayout();
    infoContainer.addComponent(nameLabel);
    Label priceLabel = new Label(Double.toString(item.getPrice()));
    infoContainer.addComponent(priceLabel);
    deleteButton = new Button("Delete");
    editSaveButton = new Button("Edit");
  }

  public Item getItem() {
    return item;
  }
}
