package com.teamlans.lepta.view.account.components;

import com.ejt.vaadin.loginform.LoginForm;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.Credentials;
import com.teamlans.lepta.view.account.SignUpView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * PartnerSignUp displays a users name and offers a SignUpForm for their partner.
 */
public final class PartnerSignUp extends HorizontalLayout {

  final private SignUpView parent;
  final private String firstName;

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

    final VerticalLayout center = new VerticalLayout();
    center.setSizeUndefined();
    container.addComponent(center);
    container.setComponentAlignment(center, Alignment.MIDDLE_RIGHT);

    addContent(center);
    return container;
  }

  private void addContent(VerticalLayout center) {
    final Label title = new Label("Add your partner:");
    title.setSizeUndefined();
    center.addComponent(title);
    center.setComponentAlignment(title, Alignment.MIDDLE_RIGHT);

    final SignUpForm signUpForm = buildSignUpForm();
    center.addComponent(signUpForm);
    center.setComponentAlignment(signUpForm, Alignment.MIDDLE_RIGHT);
  }

  private SignUpForm buildSignUpForm() {
    final SignUpForm signUpForm = new SignUpForm();
    signUpForm.setSizeUndefined();
    addOkListener(signUpForm);
    addCancelListener(signUpForm);
    return signUpForm;
  }

  private void addOkListener(SignUpForm signUpForm) {
    signUpForm.addLoginListener(new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        try {
          parent.setPartnerAccount(new Credentials(event.getUserName(), event.getPassword()));
          parent.finishAndGoHome();
        } catch (LeptaServiceException e) {
          parent.showNotification(e.getMessage());
        }
      }
    });
  }

  private void addCancelListener(SignUpForm signUpForm) {
    final Button cancelButton = signUpForm.getCancelButton();
    cancelButton.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        parent.showInitialSignUp();
      }
    });
  }

}
