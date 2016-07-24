package com.teamlans.lepta.view.account.components;

import com.ejt.vaadin.loginform.LoginForm;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * LeptaLoginForm is a custom account form based on DefaultHorizontalLoginForm (vaadin account form add
 * on). Each text field contains an icon.
 */
public class LeptaLoginForm extends LoginForm {

  @Override
  protected Component createContent(TextField userNameField, PasswordField passwordField,
                                    Button loginButton) {
    final HorizontalLayout layout = new HorizontalLayout();
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
    final TextField userNameField = new TextField("Username");
    userNameField.setIcon(FontAwesome.USER);
    userNameField.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
    userNameField.focus();
    return userNameField;
  }

  @Override
  protected PasswordField createPasswordField() {
    final PasswordField passwordField = new PasswordField("Password");
    passwordField.setIcon(FontAwesome.LOCK);
    passwordField.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
    return passwordField;
  }

  @Override
  protected Button createLoginButton() {
    final Button loginButton = new Button("Log In");
    loginButton.addStyleName("user-button");
    loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    return loginButton;
  }

}
