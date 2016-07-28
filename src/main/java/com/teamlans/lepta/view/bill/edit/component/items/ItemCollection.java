package com.teamlans.lepta.view.bill.edit.component.items;

import com.teamlans.lepta.entities.Item;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemCollection extends VerticalLayout {

  private List<ItemRow> itemRows;

  public ItemCollection() {
    itemRows = new ArrayList<>();
    setSpacing(true);
  }

  public List<Item> getItems() {
    return itemRows.stream().map(x -> x.getItem()).collect(Collectors.toList());
  }

  public void addItems(List<Item> items) {
    List<ItemRow> itemRows = items.stream().map(x -> new ItemRow(x, this)).collect(Collectors.toList());
    this.itemRows.addAll(itemRows);
    this.itemRows.forEach(this::addComponent);
  }

  public void addItem(Item item) {
    ItemRow itemRow = new ItemRow(item, this);
    itemRows.add(itemRow);
    addComponent(itemRow);
  }


  public void delete(ItemRow itemRow) {
    itemRows.remove(itemRow);
    removeComponent(itemRow);
  }
}
