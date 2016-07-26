package com.teamlans.lepta.view.account.components;

import com.ejt.vaadin.loginform.LoginForm;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.Credentials;
import com.teamlans.lepta.view.account.SignUpView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Visible for first time visitors (database is empty). Contains a sign up form for the first user
 * and leads to a second sign up form.
 */
public final class InitialSignUp extends HorizontalLayout {

  final private SignUpView parent;

  public InitialSignUp(SignUpView parent) {
    this.parent = parent;
    build();
  }

  private void build() {
    setSizeFull();

    final VerticalLayout container = new VerticalLayout();
    addComponent(container);
    setComponentAlignment(container, Alignment.MIDDLE_LEFT);

    final Label title = new Label("Create your account:");
    container.addComponent(title);

    final SignUpForm signUpForm = buildSignUpForm();
    container.addComponent(signUpForm);
  }

  private SignUpForm buildSignUpForm() {
    final SignUpForm signUpForm = new SignUpForm();
    addOkListener(signUpForm);
    addCancelListener(signUpForm);
    return signUpForm;
  }

  private void addOkListener(SignUpForm signUpForm) {
    signUpForm.addLoginListener(new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        try {
          parent.setInitialAccount(new Credentials(event.getUserName(), event.getPassword()));
          parent.showPartnerSignUp();
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
        parent.showWelcomePage();
      }
    });
  }

}
