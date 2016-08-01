package com.teamlans.lepta.view.bill.edit.component.items;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.TextField;

public class ItemNameField extends TextField {

  public ItemNameField() {
    addValidator(new StringLengthValidator("Name must not be empty", 1, 50, true));
    setNullRepresentation("");
    setInputPrompt("Enter item name");
    setValidationVisible(false);
  }

  @Override
  public void validate() {
    setValidationVisible(false);
    try {
      super.validate();
    } catch (Validator.InvalidValueException e) {
      setValidationVisible(true);
      throw e;
    }
  }
}
