package com.teamlans.lepta.view.bills.create;

import com.teamlans.lepta.database.daos.UserDao;
import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import com.teamlans.lepta.service.BillService;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
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

@SpringView(name = ManualCreateBillViewImpl.VIEW_NAME) public class ManualCreateBillViewImpl
    extends VerticalLayout implements View {

  public static final String VIEW_NAME = "MANUAL_CREATE_BILL_VIEW";

  private BillService billService;
  private UserDao userDao;
  private TextField nameField;
  private DateField dateField;
  private Table itemTable;

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
    createHeaderLabel(gridLayout);
    createInputFields(gridLayout);
    createItemList(gridLayout);
    createButtonBar(gridLayout);
    layout.addComponent(gridLayout);
    layout.setComponentAlignment(gridLayout, Alignment.MIDDLE_CENTER);
  }

  private void createInputFields(GridLayout gridLayout) {
    VerticalLayout inputContainer = new VerticalLayout();
    inputContainer.setSpacing(true);
    dateField = new DateField();
    dateField.setDateFormat("yyyy-MM-dd");
    inputContainer.addComponent(dateField);
    nameField = new TextField();
    nameField.setInputPrompt("Enter bill name");
    nameField.addValidator(new StringLengthValidator("Name must not be empty.", 1, 50, false));
    nameField.setNullRepresentation("");
    nameField.setValidationVisible(false);
    inputContainer.addComponent(nameField);
    gridLayout.addComponent(inputContainer, 0, 1);
  }

  private void createItemList(GridLayout gridLayout) {
    VerticalLayout listContainer = new VerticalLayout();
    listContainer.setSpacing(true);
    itemTable = new Table();
    itemTable.setHeight("300px");
    itemTable.addContainerProperty("Name", String.class, null);
    itemTable.addContainerProperty("Price", Double.class, null);
    itemTable.setWidth("100%");
    listContainer.addComponent(itemTable);
    listContainer.setExpandRatio(itemTable, 1);
    createItemInput(listContainer);
    gridLayout.addComponent(listContainer, 1, 1);
  }

  private void createItemInput(VerticalLayout listContainer) {
    HorizontalLayout itemInputContainer = new HorizontalLayout();
    itemInputContainer.setSpacing(true);
    TextField itemName = new TextField();
    itemInputContainer.addComponent(itemName);
    TextField itemPrice = new TextField();
    itemInputContainer.addComponent(itemPrice);
    Button addItem = new Button("Add");
    itemInputContainer.addComponent(addItem);
    listContainer.addComponent(itemInputContainer);
  }

  private void createHeaderLabel(GridLayout layout) {
    Label label = new Label("Create new bill");
    label.setStyleName(ValoTheme.LABEL_H1);
    layout.addComponent(label, 0, 0, 1, 0);
  }

  private void createButtonBar(GridLayout layout) {
    Button cancelButton = new Button("Cancel");
    layout.addComponent(cancelButton, 0, 2);
    Button okayButton = new Button("Okay");
    layout.addComponent(okayButton, 1, 2);
    layout.setComponentAlignment(okayButton, Alignment.MIDDLE_RIGHT);
  }

  private void tryAddBill() {
    nameField.setValidationVisible(false);
    try {
      nameField.validate();
      dateField.validate();
      billService.addBill(createBill());
    } catch (Validator.InvalidValueException e) {
      Notification.show(e.getMessage());
      nameField.setValidationVisible(true);
    } catch (LeptaServiceException | LeptaDatabaseException | DataAccessException e) {
      new Notification("Uh, oh, something bad happened!", e.getMessage(),
          Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
    }
  }

  private Bill createBill() throws LeptaDatabaseException {
    return new Bill(nameField.getValue(), dateField.getValue(), userDao.listUsers().get(0));
  }


  @Override public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // the view is constructed in the init() method.
  }
}
