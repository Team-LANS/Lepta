package com.teamlans.lepta.view;

import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import com.teamlans.lepta.service.BillService;
import com.teamlans.lepta.service.LeptaServiceException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView(name = NewBillsView.VIEW_NAME) public class NewBillsView extends VerticalLayout
    implements View {

  public static final String VIEW_NAME = "NEW_BILLS";


  BillService service;

  @Autowired public void setService(BillService billService) {
    this.service = billService;
  }

  @PostConstruct void init() {
    setMargin(false);
    setSpacing(true);
    VerticalLayout layout = buildRootLayout();
    addComponent(layout);
  }

  private VerticalLayout buildRootLayout() {
    VerticalLayout layout = new VerticalLayout();
    ListSelect listSelect = new ListSelect();
    listSelect.setWidth("20%");
    List<Bill> bills = null;
    try {
      bills = service.listBills();
    } catch (LeptaServiceException e) {
      e.printStackTrace();
    }
    listSelect.addItems(bills);
    layout.addComponent(listSelect);
    layout.addComponent(buildAddBillButton());
    return layout;
  }

  private Button buildAddBillButton(){
    Button addButton = new Button("Add");
    addButton.addClickListener(event -> {
      try {
        service.addBill();
      } catch (LeptaServiceException e) {
        e.printStackTrace();
      }
    });
    return addButton;
  }



  @Override public void enter(ViewChangeListener.ViewChangeEvent event) {
    // the view is constructed in the init() method()
  }
}
