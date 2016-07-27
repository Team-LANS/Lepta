package com.teamlans.lepta.view.bill;

import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.service.bill.BillService;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


@SpringView(name = EditBillView.VIEW_NAME)
public class EditBillView extends VerticalLayout implements View {

  public static final String VIEW_NAME = "Bills/Edit";

  @Autowired
  BillService billService;

  private Bill billToEdit;

  @PostConstruct
  void init() {
    setMargin(false);
    setSpacing(true);
  }


  private void setBillToEdit(int billNr){
    billToEdit = billService.getBillBy(billNr);

  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    String parameter = viewChangeEvent.getParameters();
    int billId = Integer.parseInt(parameter);
    setBillToEdit(billId);
  }
}
