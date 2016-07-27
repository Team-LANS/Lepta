package com.teamlans.lepta.view.bill.edit.component.items;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.StringLengthValidator;

import org.vaadin.ui.NumberField;

public class ItemPriceField extends NumberField {

  public ItemPriceField(){
    setNegativeAllowed(false);
    setDecimalPrecision(2);
    setMinValue(0.01);
    setValidationVisible(false);
    setNullRepresentation("");
    setErrorText("Invalid number. Number must be greater than 0.01");
    addValidator(new StringLengthValidator("Price must not be empty", 1, null, true));
    setValidationVisible(false);
  }

  @Override
  public void validate(){
    setValidationVisible(false);
    try {
      super.validate();
    }
    catch (Validator.InvalidValueException e){
      setValidationVisible(true);
      throw e;
    }
  }
}
