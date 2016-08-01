package com.teamlans.lepta.service.validation;

import com.teamlans.lepta.service.exceptions.LeptaServiceException;

import org.springframework.stereotype.Service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Service provides functionality for validating entities such as {@link com.teamlans.lepta.entities.Bill}.
 * Relies on {@link Validation}, so rules for validating entities are found in classes implementing
 * {@link ValidatableEntity}.
 */
@Service
public class EntityValidationService {

  private final Validator validator;

  public EntityValidationService() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  public void validate(ValidatableEntity entity) throws LeptaServiceException {
    Set<ConstraintViolation<ValidatableEntity>> violations = validator.validate(entity);
    if (violations.isEmpty()) {
      return;
    }
    ConstraintViolation violation = violations.iterator().next();
    throw new LeptaServiceException(createMessage(violation));
  }


  private String createMessage(ConstraintViolation violation) {
    String message = violation.getMessage();
    String property = "Property '" + violation.getPropertyPath().toString() + "'";
    return property + " " + message;
  }
}
