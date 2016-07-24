package com.teamlans.lepta.view.component.forms;

import com.ejt.vaadin.loginform.LoginForm;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * SignUpForm is a custom user form based on DefaultVerticalLoginForm added by the vaadin user
 * form add on. Each text field contains an icon.
 */
public class SignUpForm extends LoginForm {

  @Override
  protected Component createContent(TextField userNameField, PasswordField passwordField, Button loginButton) {
    VerticalLayout layout = new VerticalLayout();
    layout.setSpacing(true);
    layout.setMargin(true);
    layout.setSizeUndefined();

    layout.addComponent(userNameField);
    layout.addComponent(passwordField);

    HorizontalLayout buttonContainer = new HorizontalLayout();
    buttonContainer.setSizeFull();
    layout.addComponent(buttonContainer);

    buttonContainer.addComponent(createCancelButton());
    buttonContainer.addComponent(loginButton);
    buttonContainer.setComponentAlignment(loginButton, Alignment.BOTTOM_RIGHT);
    return layout;
  }

  @Override
  protected TextField createUserNameField() {
    final TextField userName = new TextField("Username");
    userName.setIcon(FontAwesome.USER);
    userName.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
    userName.focus();
    return userName;
  }

  @Override
  protected PasswordField createPasswordField() {
    final PasswordField password = new PasswordField("Password");
    password.setIcon(FontAwesome.LOCK);
    password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
    return password;
  }

  @Override
  protected Button createLoginButton() {
    final Button logIn = new Button("OK");
    logIn.addStyleName("left-sign-up-button");
    logIn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    return logIn;
  }

  private Button createCancelButton() {
    final Button cancel = new Button("Back");
    cancel.addStyleName(ValoTheme.BUTTON_PRIMARY);
    return cancel;
  }

}
