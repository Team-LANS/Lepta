package com.teamlans.lepta.view.login;

import com.ejt.vaadin.loginform.LoginForm;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.Credentials;
import com.teamlans.lepta.service.user.UserService;
import com.teamlans.lepta.view.component.forms.SignUpForm;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


public class SignUpView extends HorizontalLayout {

  private UserService userService;
  private Credentials account0;
  private Credentials account1;

  public SignUpView() {
    setSizeFull();
    createSignUpForm();
  }

  private void createSignUpForm() {
    SignUpForm leftSignUpForm = new SignUpForm();
    leftSignUpForm.addLoginListener(new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        try {
          account0 = new Credentials(event.getUserName(), event.getPassword());
          continueSignUp();
        } catch (LeptaServiceException e) {
          showNotification(e.getMessage());
        }
      }
    });

    // event
    // TODO: show landing page

    addComponent(leftSignUpForm);
    setComponentAlignment(leftSignUpForm, Alignment.MIDDLE_LEFT);
  }

  private void continueSignUp() {
    removeAllComponents();
    Component info = createInfo();
    addComponent(info);
    setComponentAlignment(info, Alignment.MIDDLE_LEFT);

    VerticalLayout right = new VerticalLayout();
    right.setSizeFull();
    addComponent(right);
    setComponentAlignment(right, Alignment.MIDDLE_RIGHT);

    Component rightSignUpForm = createPartnerForm();
    rightSignUpForm.setSizeUndefined();

    // event
    // TODO: go back


    right.addComponent(rightSignUpForm);
    right.setComponentAlignment(rightSignUpForm, Alignment.MIDDLE_RIGHT);
  }

  private Component createInfo() {
    VerticalLayout layout = new VerticalLayout();
    setSizeUndefined();

    Label title = new Label("Your account:");
    layout.addComponent(title);
    layout.setComponentAlignment(title, Alignment.MIDDLE_LEFT);

    Label username = new Label(account0.getName());
    layout.addComponent(username);
    layout.setComponentAlignment(username, Alignment.MIDDLE_LEFT);

    return layout;
  }

  private Component createPartnerForm() {
    SignUpForm signUpForm = new SignUpForm();
    signUpForm.addLoginListener(new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        try {
          account1 = new Credentials(event.getUserName(), event.getPassword());
          userService = new UserService();
          userService.createAccounts(account0, account1);
        } catch (LeptaServiceException e) {
          showNotification(e.getMessage());
        }
      }
    });
    return signUpForm;
  }

  private void showNotification(String description) {
    Notification notification = new Notification("", description);
    notification.setPosition(Position.BOTTOM_CENTER);
    notification.setStyleName(ValoTheme.NOTIFICATION_ERROR + " " + ValoTheme.NOTIFICATION_CLOSABLE);
    notification.setDelayMsec(5000);
    notification.show(Page.getCurrent());
  }

}
