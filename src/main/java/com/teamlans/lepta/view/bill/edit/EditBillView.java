package com.teamlans.lepta.view.bill.edit;

import com.teamlans.lepta.database.daos.UserDao;
import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.service.bill.BillService;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.view.LeptaNotification;
import com.teamlans.lepta.view.ProtectedVerticalView;
import com.teamlans.lepta.view.bill.NewBillsView;
import com.teamlans.lepta.view.bill.edit.component.BillDataLayout;
import com.teamlans.lepta.view.bill.edit.component.BillItemLayout;
import com.teamlans.lepta.view.bill.edit.component.ButtonBarLayout;
import com.vaadin.data.Validator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

@SpringView(name = EditBillView.VIEW_NAME)
public class EditBillView extends ProtectedVerticalView {

  public static final String VIEW_NAME = "Bills/Edit";

  private BillService billService;
  private UserDao userDao;

  private BillDataLayout billDataLayout;
  private BillItemLayout billItemLayout;

  private Bill billToEdit;

  @Autowired
  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  @Autowired
  public void setBillService(BillService billService) {
    this.billService = billService;
  }

  @PostConstruct
  void init() {
    setSizeFull();
    createGridLayout();
  }

  private void createGridLayout() {
    GridLayout gridLayout = new GridLayout(2, 3);
    gridLayout.setWidth("70%");
    gridLayout.setRowExpandRatio(1, 1);
    gridLayout.setSpacing(true);
    Label label = new Label("Create new bill");
    label.setStyleName(ValoTheme.LABEL_H1);
    gridLayout.addComponent(label, 0, 0, 1, 0);
    billDataLayout = new BillDataLayout();
    gridLayout.addComponent(billDataLayout, 0, 1);
    billItemLayout = new BillItemLayout();
    gridLayout.addComponent(billItemLayout, 1, 1);
    ButtonBarLayout buttonBarLayout = new ButtonBarLayout();
    buttonBarLayout.addCancelListener(clickEvent -> handleCancel());
    buttonBarLayout.addOkayListener(clickEvent -> handleCreate());
    gridLayout.addComponent(buttonBarLayout, 0, 2, 1, 2);
    buttonBarLayout.setSizeFull();
    addComponent(gridLayout);
    setComponentAlignment(gridLayout, Alignment.TOP_CENTER);
  }


  private void handleCancel() {
    getUI().getNavigator().navigateTo(NewBillsView.VIEW_NAME);
  }

  private void handleCreate() {
    try {
      billDataLayout.validate();
      billItemLayout.validate();
    } catch (Validator.InvalidValueException e) {
      LeptaNotification.showError(e.getMessage());
      return;
    }
    tryAddBill();
  }

  private void tryAddBill() {
    try {
      saveBill();
      LeptaNotification.show("Bill updated");
      getUI().getNavigator().navigateTo(NewBillsView.VIEW_NAME);
    } catch (LeptaServiceException | DataAccessException e) {
      LeptaNotification.showError("Uh, oh, something bad happened!", e.getMessage());
    }
  }


  private void saveBill() throws LeptaServiceException {
    billToEdit.setName(billDataLayout.getName());
    billToEdit.setDate(billDataLayout.getDate());
    billService.addOrUpdate(billToEdit);
  }

  private void setBillToEdit(int billId) {
    if (billId != -1) {
      billToEdit = billService.getBillBy(billId);
    } else {
      createNewBill();
    }
    billDataLayout.initializeWith(billToEdit);
    billItemLayout.initializeWith(billToEdit);
  }

  private void createNewBill() {
    Calendar today = Calendar.getInstance();
    today.clear(Calendar.HOUR);
    today.clear(Calendar.MINUTE);
    today.clear(Calendar.SECOND);
    Date todayDate = today.getTime();
    billToEdit = new Bill("", todayDate, getLeptaUi().getLoggedInUser());
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    String parameter = viewChangeEvent.getParameters();
    int billId = Integer.parseInt(parameter);
    setBillToEdit(billId);
  }
}