package com.teamlans.lepta.view.login;

import com.ejt.vaadin.loginform.LoginForm;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.Credentials;
import com.teamlans.lepta.service.user.UserService;
import com.teamlans.lepta.view.component.forms.SignUpForm;
import com.vaadin.ui.*;

public class SignUpView extends HorizontalLayout {

  private UserService userService;
  private Credentials account0;
  private Credentials account1;

  public SignUpView() {
    buildLeft();
  }

  private void buildLeft() {
    setSizeFull();

    VerticalLayout left = new VerticalLayout();
    addComponent(left);
    setComponentAlignment(left, Alignment.MIDDLE_LEFT);

    left.addComponent(new Label("TITLE"));

    SignUpForm signUpForm = new SignUpForm();
    signUpForm.addLoginListener(new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        try {
          account0 = new Credentials(event.getUserName(), event.getPassword());
          buildRight(account0.getName());
        } catch (LeptaServiceException e) {
          // TODO
        }
      }
    });
    left.addComponent(signUpForm);
    left.setComponentAlignment(signUpForm, Alignment.MIDDLE_LEFT);
  }

  private void buildRight(String name) {
    setSizeFull();

    addComponent(buildAccountInformation(name));

    // right
    VerticalLayout rightContainer = new VerticalLayout();
    addComponent(rightContainer);

    VerticalLayout right = new VerticalLayout();
    rightContainer.addComponent(right);
    rightContainer.setComponentAlignment(right, Alignment.MIDDLE_RIGHT);

    right.addComponent(new Label("TITLE"));

    userService = new UserService();
    SignUpForm signUpForm2 = new SignUpForm();
    signUpForm2.addLoginListener(new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        try {
          account1 = new Credentials(event.getUserName(), event.getPassword());
          userService.createAccounts(account0, account1);
          // TODO: Load account0 resources
        } catch (LeptaServiceException e) {
          // TODO
        }
      }
    });
  }

  private Component buildAccountInformation(String name) {
    VerticalLayout container = new VerticalLayout();
    addComponent(container);

    VerticalLayout left = new VerticalLayout();
    container.addComponent(left);
    container.setComponentAlignment(left, Alignment.MIDDLE_LEFT);

    left.addComponent(new Label("TITLE"));
    left.addComponent(new Label(name));

    return container;
  }

}
