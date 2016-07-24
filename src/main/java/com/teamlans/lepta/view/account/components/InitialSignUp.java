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

public class InitialSignUp extends HorizontalLayout {

  final private SignUpView parent;

  public InitialSignUp(SignUpView parent) {
    this.parent = parent;
    build();
  }

  private void build() {
    setSizeFull();
    VerticalLayout container = new VerticalLayout();
    addComponent(container);

    Label title = new Label("Create your account:");
    container.addComponent(title);

    SignUpForm signUpForm = buildSignUpForm();
    container.addComponent(signUpForm);
    container.setComponentAlignment(signUpForm, Alignment.MIDDLE_LEFT);
  }

  private SignUpForm buildSignUpForm() {
    SignUpForm signUpForm = new SignUpForm();
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
    Button cancelButton = signUpForm.getCancelButton();
    cancelButton.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        parent.showWelcomePage();
      }
    });
    return signUpForm;
  }


}
