package com.teamlans.lepta.view.bill.edit.component.items;

import com.teamlans.lepta.entities.Item;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class ItemCollection extends VerticalLayout {

  private List<Item> items;

  public ItemCollection() {
    items = new ArrayList<>();
    setSpacing(true);
  }

  public List<Item> getItems() {
    return items;
  }

  public void addItems(List<Item> items) {
    this.items = items;
    List<ItemRow> itemRows = new ArrayList<>();
    for (Item item : items) {
      itemRows.add(new ItemRow(item, this));
    }
    itemRows.forEach(this::addComponent);
  }

  void addItem(Item item) {
    ItemRow itemRow = new ItemRow(item, this);
    items.add(item);
    addComponent(itemRow);
  }


  void delete(ItemRow itemRow) {
    removeComponent(itemRow);
    items.remove(itemRow.getItem());
  }
}
