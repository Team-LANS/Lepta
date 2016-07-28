package com.teamlans.lepta.view.bill.edit.component;

import com.teamlans.lepta.entities.Bill;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.util.Date;

public class BillDataLayout extends VerticalLayout {

  private TextField nameField;

  private DateField dateField;

  public BillDataLayout() {
    nameField = new TextField();
    nameField.setInputPrompt("Enter bill name");
    nameField.addValidator(new StringLengthValidator("Name must not be empty", 1, 50, false));
    nameField.setNullRepresentation("");
    nameField.setValidationVisible(false);
    addComponent(nameField);
    dateField = new DateField();
    dateField.setDateFormat("yyyy-MM-dd");
    addComponent(dateField);
  }

  public void validate() throws Validator.InvalidValueException {
    nameField.setValidationVisible(false);
    dateField.setValidationVisible(false);
    try {
      nameField.validate();
      dateField.validate();
    } catch (Validator.InvalidValueException e) {
      nameField.setValidationVisible(true);
      dateField.setValidationVisible(true);
      throw e;
    }
  }

  public void initializeWith(Bill bill){
    nameField.setValue(bill.getName());
    dateField.setValue(bill.getDate());
  }

  public String getName() {
    return nameField.getValue();
  }

  public Date getDate() {
    return dateField.getValue();
  }


}
