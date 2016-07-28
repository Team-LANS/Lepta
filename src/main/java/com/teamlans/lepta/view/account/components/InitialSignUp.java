package com.teamlans.lepta.view.account.components;

import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.Credentials;
import com.teamlans.lepta.view.LeptaNotification;
import com.teamlans.lepta.view.account.SignUpView;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

/**
 * Visible for first time visitors (database is empty). Contains a sign up form for the first user
 * and leads to a second sign up form.
 */
public final class InitialSignUp extends HorizontalLayout {

  private final SignUpView parent;

  private static final Logger logger = LoggerFactory.getLogger(InitialSignUp.class);

  public InitialSignUp(SignUpView parent) {
    this.parent = parent;
    build();
  }

  private void build() {
    setSizeFull();

    final Panel container = new Panel("Create your account:");
    container.setSizeUndefined();
    container.setContent(buildSignUpForm());

    addComponent(container);
    setComponentAlignment(container, Alignment.MIDDLE_LEFT);
  }

  private SignUpForm buildSignUpForm() {
    final SignUpForm signUpForm = new SignUpForm();
    addOkListener(signUpForm);
    addCancelListener(signUpForm);
    return signUpForm;
  }

  private void addOkListener(SignUpForm signUpForm) {
    signUpForm.addLoginListener(event -> {
      try {
        parent.setInitialAccount(new Credentials(event.getUserName(), event.getPassword()));
        parent.showPartnerSignUp();
      } catch (LeptaServiceException e) {
        logger.error("Error", e);
        LeptaNotification.showError(e.getMessage());
      }
    });
  }

  private void addCancelListener(SignUpForm signUpForm) {
    final Button cancelButton = signUpForm.getCancelButton();
    cancelButton.addClickListener(event -> parent.showWelcomePage());
  }

}
