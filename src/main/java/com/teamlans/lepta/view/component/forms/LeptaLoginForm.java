package com.teamlans.lepta.view.component.forms;

import com.ejt.vaadin.loginform.LoginForm;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class LeptaLoginForm extends LoginForm {

  protected Component createContent(TextField userNameField, PasswordField passwordField, Button loginButton) {
    HorizontalLayout layout = new HorizontalLayout();
    layout.setSpacing(true);
    layout.setMargin(true);
    layout.setSizeUndefined();

    layout.addComponent(userNameField);
    layout.addComponent(passwordField);
    layout.addComponent(loginButton);
    layout.setComponentAlignment(loginButton, Alignment.BOTTOM_LEFT);
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
    final Button logIn = new Button("Log In");
    logIn.addStyleName("login-button");
    logIn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    return logIn;
  }
}
