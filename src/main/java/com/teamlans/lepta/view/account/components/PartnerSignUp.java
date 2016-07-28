package com.teamlans.lepta.view.account.components;

import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.Credentials;
import com.teamlans.lepta.view.LeptaNotification;
import com.teamlans.lepta.view.account.SignUpView;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * PartnerSignUp displays a users name and offers a SignUpForm for their partner.
 */
public final class PartnerSignUp extends HorizontalLayout {

  final private SignUpView parent;
  final private String firstName;

  private static final Logger logger = LoggerFactory.getLogger(PartnerSignUp.class);

  public PartnerSignUp(SignUpView parent, String firstName) {
    this.parent = parent;
    this.firstName = firstName;

    final Component left = buildLeft();
    setSizeFull();
    left.setWidth("400");
    addComponent(left);
    setComponentAlignment(left, Alignment.MIDDLE_LEFT);

    final Component right = buildRight();
    right.setSizeFull();
    addComponent(right);
  }

  private Component buildLeft() {
    final VerticalLayout container = new VerticalLayout();

    final VerticalLayout center = new VerticalLayout();
    center.setSizeUndefined();
    container.addComponent(center);
    container.setComponentAlignment(center, Alignment.MIDDLE_LEFT);
    addInfo(center);

    return container;
  }

  private void addInfo(VerticalLayout center) {
    final Label title = new Label("Your account:");
    title.setSizeUndefined();
    center.addComponent(title);

    final Label username = new Label(firstName);
    title.setSizeUndefined();
    center.addComponent(username);
  }

  private Component buildRight() {
    final VerticalLayout container = new VerticalLayout();

    final Panel center = new Panel("Add your partner:");
    center.setSizeUndefined();
    center.setContent(buildSignUpForm());

    container.addComponent(center);
    container.setComponentAlignment(center, Alignment.MIDDLE_RIGHT);
    return container;
  }

  private SignUpForm buildSignUpForm() {
    final SignUpForm signUpForm = new SignUpForm();
    signUpForm.setSizeUndefined();
    addOkListener(signUpForm);
    addCancelListener(signUpForm);
    return signUpForm;
  }

  private void addOkListener(SignUpForm signUpForm) {
    signUpForm.addLoginListener(event -> {
      try {
        parent.setPartnerAccount(new Credentials(event.getUserName(), event.getPassword()));
        parent.finishAndGoHome();
      } catch (LeptaServiceException e) {
        logger.error("Error", e);
        LeptaNotification.showError(e.getMessage());
      }
    });
  }

  private void addCancelListener(SignUpForm signUpForm) {
    final Button cancelButton = signUpForm.getCancelButton();
    cancelButton.addClickListener(event -> parent.showInitialSignUp());
  }

}
