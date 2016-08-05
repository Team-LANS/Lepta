package com.teamlans.lepta.view.bill;

import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.service.bill.BillService;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.view.ProtectedVerticalView;
import com.teamlans.lepta.view.bill.edit.EditBillView;
import com.teamlans.lepta.view.bill.overview.component.BillChangeAction;
import com.teamlans.lepta.view.bill.overview.component.BillChangeEvent;
import com.teamlans.lepta.view.bill.overview.component.BillChangeListener;
import com.teamlans.lepta.view.bill.overview.component.BillComponent;
import com.teamlans.lepta.view.bill.overview.component.BillComponentContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.List;

import javax.annotation.PostConstruct;

@SpringView(name = NewBillsView.VIEW_NAME)
@Scope("request")
public class NewBillsView extends ProtectedVerticalView implements BillChangeListener {

  public static final String VIEW_NAME = "Bills";

  @Autowired
  private BillService billService;

  private BillComponentContainer billContainer;

  @PostConstruct
  void init() {
    setSpacing(true);
    setMargin(true);
    Label headerLabel = new Label("My new bills");
    headerLabel.setStyleName(ValoTheme.LABEL_H2);
    addComponent(headerLabel);
    billContainer = new BillComponentContainer();
    List<Bill> bills = billService.listNewBillsFor(getLeptaUi().getLoggedInUser());
    for (Bill bill : bills) {
      BillComponent billComponent = new BillComponent(bill);
      billComponent.addBillChangeListener(this);
      billComponent.addBillChangeListener(billContainer);
      billContainer.addComponent(billComponent);
    }
    addComponent(billContainer);
    addComponent(buildAddBillButton());
  }


  private Button buildAddBillButton() {
    Button addButton = new Button("Add");
    addButton.addClickListener(
        event -> getUI().getNavigator().navigateTo(EditBillView.VIEW_NAME + "/" + -1));
    return addButton;
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent event) {
    //View is constructed via spring
  }

  @Override
  public void notify(BillChangeEvent event) {
    if (event.getAction() == BillChangeAction.DELETE) {
      try {
        billService.deleteBill(event.getBill());
      } catch (LeptaServiceException e) {
        e.printStackTrace();
      }
    }
    if (event.getAction() == BillChangeAction.EDIT) {
      getUI().getNavigator().navigateTo(EditBillView.VIEW_NAME + "/" + event.getBill().getId());
    }
  }
}
