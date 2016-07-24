package com.teamlans.lepta.view.account.components;

import com.ejt.vaadin.loginform.LoginForm;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


/**
 * SignUpForm is a custom login form based on DefaultVerticalLoginForm (added by the vaadin
 * login form add on). Each text field contains an icon. A 'Cancel'-Button is added.
 */
public class SignUpForm extends LoginForm {

  // TODO: fix this
  // needs to be initialized here to make getter work
  private Button cancelButton = createCancelButton();

  @Override
  protected Component createContent(TextField userNameField, PasswordField passwordField,
                                    Button loginButton) {
    final VerticalLayout layout = new VerticalLayout();
    layout.setSpacing(true);
    layout.setMargin(true);
    layout.setSizeUndefined();

    layout.addComponent(userNameField);
    layout.addComponent(passwordField);
    layout.addComponent(createButtonContainer(loginButton));
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

  private Component createButtonContainer(Button loginButton) {
    HorizontalLayout buttonContainer = new HorizontalLayout();
    buttonContainer.setSizeFull();

    buttonContainer.addComponent(cancelButton);
    buttonContainer.setComponentAlignment(cancelButton, Alignment.BOTTOM_LEFT);

    buttonContainer.addComponent(loginButton);
    buttonContainer.setComponentAlignment(loginButton, Alignment.BOTTOM_RIGHT);
    return buttonContainer;
  }

  private Button createCancelButton() {
    cancelButton = new Button("Back");
    return cancelButton;
  }

  @Override
  protected Button createLoginButton() {
    final Button loginButton = new Button("OK");
    loginButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
    loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    return loginButton;
  }

  public Button getCancelButton() {
    return cancelButton;
  }

}
