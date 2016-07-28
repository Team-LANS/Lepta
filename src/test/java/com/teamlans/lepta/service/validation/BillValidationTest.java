package com.teamlans.lepta.service.validation;

import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.entities.Item;
import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;

public class BillValidationTest {

  private EntityValidationService entityValidation;

  @Rule
  public ExpectedException expectedEx = ExpectedException.none();


  @Before
  public void setUp(){
    entityValidation = new EntityValidationService();
  }

  @Test
  public void validate_nameEmpty_nameEmptyException() throws Exception {
    expectedEx.expect(LeptaServiceException.class);
    expectedEx.expectMessage("name");
    expectedEx.expectMessage("may not be empty");

    Bill bill = new Bill("", new Date(), new User());
    bill.getItems().add(new Item());

    entityValidation.validate(new ValidatableBill(bill));
  }

  @Test
  public void validate_listEmpty_listEmptyException() throws Exception {
    expectedEx.expect(LeptaServiceException.class);
    expectedEx.expectMessage("items");
    expectedEx.expectMessage("may not be empty");

    Bill bill = new Bill("name", new Date(), new User());

    entityValidation.validate(new ValidatableBill(bill));
  }

  @Test
  public void validate_dateEmpty_dateNullException() throws Exception {
    expectedEx.expect(LeptaServiceException.class);
    expectedEx.expectMessage("date");
    expectedEx.expectMessage("may not be null");

    Bill bill = new Bill("name", null, new User());
    bill.getItems().add(new Item());

    entityValidation.validate(new ValidatableBill(bill));
  }

  @Test
  public void validate_userNull_userNullException() throws Exception {
    expectedEx.expect(LeptaServiceException.class);
    expectedEx.expectMessage("user");
    expectedEx.expectMessage("may not be null");

    Bill bill = new Bill("name", new Date(), null);
    bill.getItems().add(new Item());

    entityValidation.validate(new ValidatableBill(bill));
  }
}
