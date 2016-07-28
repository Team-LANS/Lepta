package com.teamlans.lepta.service.validation;

import com.teamlans.lepta.service.exceptions.LeptaServiceException;

import org.springframework.stereotype.Service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Service
public class EntityValidationService {

  private final Validator validator;

  public EntityValidationService(){
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  public void validate(ValidatableEntity entity) throws LeptaServiceException {
    Set<ConstraintViolation<ValidatableEntity>> violations = validator.validate(entity);
    if(violations.isEmpty()){
      return;
    }
    ConstraintViolation violation = violations.iterator().next();
    throw new LeptaServiceException(createMessage(violation));
  }


  private String createMessage(ConstraintViolation violation){
    String message = violation.getMessage();
    String property = "Property '" + violation.getPropertyPath().toString() +"'";
    return property + " " + message;
  }
}
