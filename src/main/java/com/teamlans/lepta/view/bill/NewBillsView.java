package com.teamlans.lepta.view.bill;

import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.service.bill.BillService;
import com.teamlans.lepta.view.bill.create.EditBillView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = NewBillsView.VIEW_NAME)
public class NewBillsView extends VerticalLayout
    implements View {

  public static final String VIEW_NAME = "Bills";

  @Autowired
  private BillService billService;

  private Table table;

  private BeanItemContainer<Bill> container;

  @PostConstruct
  void init() {
    setMargin(false);
    setSpacing(true);
    addComponent(buildBillsTable());
    addComponent(buildAddBillButton());
    addComponent(buildEditBillButton());
  }

  private Table buildBillsTable() {
    table = new Table();
    container = new BeanItemContainer<>(Bill.class);
    container.addAll(billService.listBills());
    table.setContainerDataSource(container);
    table.setVisibleColumns("name");
    table.setSelectable(true);
    return table;
  }


  private Button buildAddBillButton() {
    Button addButton = new Button("Add");
    addButton.addClickListener(
        event -> getUI().getNavigator().navigateTo(EditBillView.VIEW_NAME + "/" + -1));
    return addButton;
  }

  private Button buildEditBillButton() {
    Button editButton = new Button("Edit");
    editButton.addClickListener(clickEvent -> {
      Bill selectedBill = (Bill)table.getValue();
      int id = selectedBill.getId();
      getUI().getNavigator().navigateTo(EditBillView.VIEW_NAME +"/"+ id);
    });
    return editButton;
  }


  @Override
  public void enter(ViewChangeListener.ViewChangeEvent event) {
    // the view is constructed in the init() method()
  }
}
