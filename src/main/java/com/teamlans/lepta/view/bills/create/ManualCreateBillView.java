package com.teamlans.lepta.view.bills.create;

import com.teamlans.lepta.database.daos.UserDao;
import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.entities.Item;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import com.teamlans.lepta.service.bill.BillService;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.view.bills.newBills.NewBillsView;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;

@SpringView(name = ManualCreateBillView.VIEW_NAME) public class ManualCreateBillView
    extends VerticalLayout implements View {

  public static final String VIEW_NAME = "MANUAL_CREATE_BILL";

  private BillService billService;
  private UserDao userDao;
  private TextField nameField;
  private DateField dateField;

  private BillItemList itemList;

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
    layout.setResponsive(true);
    addComponent(layout);
  }

  private VerticalLayout buildRootLayout() {
    VerticalLayout layout = new VerticalLayout();
    layout.setSizeFull();
    createGridLayout(layout);
    return layout;
  }

  private void createGridLayout(VerticalLayout layout) {
    GridLayout gridLayout = new GridLayout(2, 3);
    gridLayout.setWidth("600px");
    gridLayout.setHeight("200px");
    gridLayout.setRowExpandRatio(1,1);
    gridLayout.setSpacing(true);
    createHeaderLabel(gridLayout);
    createInputFields(gridLayout);
    itemList = new BillItemList();
    gridLayout.addComponent(itemList,1,1);
    createButtonBar(gridLayout);
    layout.addComponent(gridLayout);
    layout.setComponentAlignment(gridLayout, Alignment.MIDDLE_CENTER);
  }

  private void createInputFields(GridLayout gridLayout) {
    VerticalLayout inputContainer = new VerticalLayout();
    inputContainer.setSpacing(true);
    nameField = new TextField();
    nameField.setInputPrompt("Enter bill name");
    nameField.addValidator(new StringLengthValidator("Name must not be empty", 1, 50, false));
    nameField.setNullRepresentation("");
    nameField.setValidationVisible(false);
    inputContainer.addComponent(nameField);
    dateField = new DateField();
    dateField.setDateFormat("yyyy-MM-dd");
    inputContainer.addComponent(dateField);
    gridLayout.addComponent(inputContainer, 0, 1);
  }

  private void createHeaderLabel(GridLayout layout) {
    Label label = new Label("Create new bill");
    label.setStyleName(ValoTheme.LABEL_H1);
    layout.addComponent(label, 0, 0, 1, 0);
  }

  private void createButtonBar(GridLayout layout) {
    Button cancelButton = new Button("Cancel");
    cancelButton.addClickListener(event -> handleCancel());
    layout.addComponent(cancelButton, 0, 2);
    Button okayButton = new Button("Okay");
    okayButton.addClickListener(clickEvent -> tryAddBill());
    layout.addComponent(okayButton, 1, 2);
    layout.setComponentAlignment(okayButton, Alignment.MIDDLE_RIGHT);
  }

  private void handleCancel(){
    getUI().getNavigator().navigateTo(NewBillsView.VIEW_NAME);
  }

  private void tryAddBill() {
    if (validateFields())
      return;
    try{
      billService.addBill(createBill());
    }
    catch (LeptaServiceException | LeptaDatabaseException | DataAccessException e) {
      new Notification("Uh, oh, something bad happened!", e.getMessage(),
          Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
    }
  }

  private boolean validateFields() {
    nameField.setValidationVisible(false);
    dateField.setValidationVisible(false);
    try {
      nameField.validate();
      dateField.validate();
    } catch (Validator.InvalidValueException e) {
      Notification.show(e.getMessage());
      nameField.setValidationVisible(true);
      dateField.setValidationVisible(true);
      return true;
    }
    return false;
  }

  private Bill createBill() throws LeptaDatabaseException {
    Bill bill = new Bill(nameField.getValue(), dateField.getValue(), userDao.listUsers().get(0));
    List<Item> billItems = itemList.getBillItems();
    billItems.stream().forEach(item -> item.setBill(bill));
    bill.setItems(new HashSet<>(billItems));
    return bill;
  }


  @Override public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // the view is constructed in the init() method.
  }
}
