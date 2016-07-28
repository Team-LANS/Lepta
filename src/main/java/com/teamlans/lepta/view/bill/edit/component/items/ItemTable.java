package com.teamlans.lepta.view.bill.edit.component.items;

import com.teamlans.lepta.entities.Item;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;

import java.util.ArrayList;
import java.util.List;

public class ItemTable extends ListSelect {

  private BeanItemContainer<Item> itemContainer;

  public ItemTable() {
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

  private class DeleteButtonGenerator implements Table.ColumnGenerator {
    @Override
    public Object generateCell(Table table, Object itemId, Object columnId) {
      setData(itemId);
      Button button = new Button("X");
      button.addClickListener(clickEvent -> table.removeItem(getData()));
      return button;
    }
  }

  private class EditButtonGenerator implements Table.ColumnGenerator {
    @Override
    public Object generateCell(Table table, Object itemId, Object columnId) {
      Button button = new Button(itemId == getData() ? "Save" : "Edit");
      button.addClickListener(event -> editTable(event));
      return button;
    }
  }

  private class CustomFieldFactory implements TableFieldFactory {
    @Override
    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
      if (itemId == getData()) {
        return DefaultFieldFactory.get().createField(container, itemId, propertyId, uiContext);
      }
      return null;
    }
  }

  private void editTable(final Button.ClickEvent ce) {
    setTableFieldFactory(new TableFieldFactory() {
      @Override
      public Field createField(Container container, Object itemId, Object propertyId, Component uiContext) {
          if (propertyId.toString().equals("expDir")) {
            return new TextField();
          }
          if (propertyId.toString().equals("participant")) {
            return new TextField();
          }
        return null;
      }
    });
    setEditable(true);
  }


}
