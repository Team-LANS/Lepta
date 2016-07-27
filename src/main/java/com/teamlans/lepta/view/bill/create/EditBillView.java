package com.teamlans.lepta.view.bill.create;

import com.teamlans.lepta.database.daos.UserDao;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.entities.Item;
import com.teamlans.lepta.service.bill.BillService;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.view.bill.NewBillsView;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

@SpringView(name = EditBillView.VIEW_NAME)
public class EditBillView
    extends VerticalLayout implements View {

  public static final String VIEW_NAME = "Bills/Edit";

  private BillService billService;
  private UserDao userDao;
  private TextField nameField;
  private DateField dateField;

  private BillItemList billItemList;
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
    setMargin(false);
    setSpacing(true);
  }

  private VerticalLayout buildRootLayout() {
    VerticalLayout layout = new VerticalLayout();
    layout.setSizeFull();
    createGridLayout(layout);
    return layout;
  }

  private void createGridLayout(VerticalLayout layout) {
    GridLayout gridLayout = new GridLayout(2, 3);
    gridLayout.setWidth("70%");
    gridLayout.setHeight("200px");
    gridLayout.setRowExpandRatio(1, 1);
    gridLayout.setSpacing(true);
    createHeaderLabel(gridLayout);
    createInputFields(gridLayout);
    billItemList = new BillItemList(new ArrayList<>(billToEdit.getItems()));
    gridLayout.addComponent(billItemList, 1, 1);
    createButtonBar(gridLayout);
    layout.addComponent(gridLayout);
    layout.setComponentAlignment(gridLayout, Alignment.MIDDLE_CENTER);
  }

  private void createInputFields(GridLayout gridLayout) {
    VerticalLayout inputContainer = new VerticalLayout();
    inputContainer.setSpacing(true);
    nameField = new TextField();
    nameField.setInputPrompt("Enter bill name");
    nameField.setValue(billToEdit.getName());
    nameField.addValidator(new StringLengthValidator("Name must not be empty", 1, 50, false));
    nameField.setNullRepresentation("");
    nameField.setValidationVisible(false);
    inputContainer.addComponent(nameField);
    dateField = new DateField();
    dateField.setDateFormat("yyyy-MM-dd");
    dateField.setValue(billToEdit.getDate());
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

  private void handleCancel() {
    getUI().getNavigator().navigateTo(NewBillsView.VIEW_NAME);
  }

  private void tryAddBill() {
    if (validateFields())
      return;
    try {
      saveBill();
      Notification notification = new Notification("Bill created successfully!", "", Notification.Type.HUMANIZED_MESSAGE);
      notification.setPosition(Position.BOTTOM_CENTER);
      notification.setDelayMsec(5000);
      notification.show(Page.getCurrent());
      getUI().getNavigator().navigateTo(NewBillsView.VIEW_NAME);
    } catch (LeptaServiceException | DataAccessException e) {
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

  private void saveBill() throws LeptaServiceException {
    billToEdit.setName(nameField.getValue());
    billToEdit.setDate(dateField.getValue());
    List<Item> billItems = billItemList.getBillItems();
    billItems.forEach(x -> x.setBill(billToEdit));
    billToEdit.setItems(new HashSet<>(billItems));
    billService.addBill(billToEdit);
  }

  private void setBillToEdit(int billId) {
    if (billId != -1) {
      billToEdit = billService.getBillBy(billId);
    } else {
      Calendar today = Calendar.getInstance();
      today.clear(Calendar.HOUR);
      today.clear(Calendar.MINUTE);
      today.clear(Calendar.SECOND);
      Date todayDate = today.getTime();
      try {
        billToEdit = new Bill("", todayDate, userDao.listUsers().get(0));
      } catch (LeptaDatabaseException e) {
        e.printStackTrace();
      }

    }
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    String parameter = viewChangeEvent.getParameters();
    int billId = Integer.parseInt(parameter);
    setBillToEdit(billId);
    VerticalLayout layout = buildRootLayout();
    layout.setResponsive(true);
    addComponent(layout);
  }
}
