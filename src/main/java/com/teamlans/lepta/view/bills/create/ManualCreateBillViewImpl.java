package com.teamlans.lepta.view.bills.create;

import com.teamlans.lepta.database.daos.UserDao;
import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import com.teamlans.lepta.service.BillService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = ManualCreateBillViewImpl.VIEW_NAME) public class ManualCreateBillViewImpl
    extends VerticalLayout implements View {

  public static final String VIEW_NAME = "MANUAL_CREATE_BILL_VIEW";

  private BillService billService;
  private UserDao userDao;
  private TextField nameField;
  private DateField dateField;

  @Autowired public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  @Autowired public void setBillService(BillService billService) {
    this.billService = billService;
  }

  @PostConstruct void init() {
    setMargin(false);
    setSpacing(true);
    VerticalLayout layout = buildRootLayout();
    addComponent(layout);
  }

  private VerticalLayout buildRootLayout() {
    VerticalLayout layout = new VerticalLayout();
    Label label = new Label("Create new bill");
    nameField = new TextField("Enter name here");
    dateField = new DateField();
    layout.addComponent(label);
    layout.addComponent(nameField);
    layout.addComponent(dateField);
    Button button = new Button("Create");
    button.addClickListener(event -> addBill());
    layout.addComponent(button);
    Button cancel = new Button("Cancel");
    layout.addComponent(cancel);
    return layout;
  }

  private void addBill() {
    try {
      billService.addBill(createBill());
    } catch (Exception e) {
      new Notification("Uh, oh, something bad happened!", e.getMessage(),
          Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
    }
  }

  private Bill createBill() throws LeptaDatabaseException {
    return new Bill(nameField.getValue(), dateField.getValue().toString(),
        userDao.listUsers().get(0));
  }


  @Override public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // the view is constructed in the init() method.
  }
}
