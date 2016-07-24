package com.teamlans.lepta.view.login;

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
    SignUpForm leftSignUpForm = new SignUpForm("Create your account:", Alignment.MIDDLE_LEFT);
    addComponent(leftSignUpForm);
    setComponentAlignment(leftSignUpForm, Alignment.MIDDLE_LEFT);
  }

  private void continueSignUp() {
    removeAllComponents();
    addComponent(createInfo());

    VerticalLayout right = new VerticalLayout();
    addComponent(right);
    setComponentAlignment(right, Alignment.TOP_RIGHT);

    Component rightSignUpForm = createPartnerForm();
    right.addComponent(rightSignUpForm);
    right.setComponentAlignment(rightSignUpForm, Alignment.MIDDLE_RIGHT);
  }

  private Component createInfo() {
    VerticalLayout layout = new VerticalLayout();

    Label title = new Label("Your account:");
    layout.addComponent(title);
    layout.setComponentAlignment(title, Alignment.MIDDLE_LEFT);

    Label username = new Label(account0.getName());
    layout.addComponent(username);
    layout.setComponentAlignment(username, Alignment.MIDDLE_LEFT);

    return layout;
  }

  private Component createPartnerForm() {
    SignUpForm signUpForm = new SignUpForm("Add your partner:", Alignment.MIDDLE_RIGHT);
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
