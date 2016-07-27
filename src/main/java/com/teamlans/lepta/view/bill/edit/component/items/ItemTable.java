package com.teamlans.lepta.view.bill.edit.component.items;

import com.teamlans.lepta.entities.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;

import java.util.ArrayList;
import java.util.List;

public class ItemTable extends Table {

  private BeanItemContainer<Item> itemContainer;

  public ItemTable() {
    addContainerProperty("name", String.class, null);
    addContainerProperty("price", Double.class, null);
    addGeneratedColumn("", new DeleteButtonGenerator());
    setVisibleColumns("name", "price");
    itemContainer = new BeanItemContainer<>(Item.class);
    itemContainer.addAll(new ArrayList<>());
    setContainerDataSource(itemContainer);
  }

  public List<Item> getItems() {
    return itemContainer.getItemIds();
  }

  public void addItems(List<Item> items) {
    itemContainer.addAll(items);
  }

  public void addItem(Item item) {
    itemContainer.addItem(item);
  }

  private class DeleteButtonGenerator implements ColumnGenerator {
    @Override
    public Object generateCell(Table table, Object itemId, Object columnId) {
      Button button = new Button(itemId == table.getData() ? "Save" : "Edit");
      button.addClickListener(new Button.ClickListener() {
        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(Button.ClickEvent event) {
          if (table.getData() == null) {
            table.setData(itemId);
            table.refreshRowCache();
          } else if (itemId == table.getData()) {
            table.setData(null);
            table.refreshRowCache();
          }
        }
      });
      return button;
    }
  }

  private class EditButtonGenerator implements ColumnGenerator {
    @Override
    public Object generateCell(Table table, Object itemId, Object columnId) {
      Button button = new Button(itemId == table.getData() ? "Save" : "Edit");
      return button;
    }
  }
}
