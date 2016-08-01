package com.teamlans.lepta.view.bill.edit.component.items;

import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.entities.Item;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemCollection extends VerticalLayout {

  private Bill bill;

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
    List<ItemRow> itemRows = items.stream().map(x -> new ItemRow(x, this)).collect(Collectors.toList());
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
