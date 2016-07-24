package com.teamlans.lepta.view.component.forms;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


/**
 *
 */
public class SignUpForm extends VerticalLayout {

  public SignUpForm(String title, Alignment alignment) {
    setSizeUndefined();
    setSpacing(true);

    Component header = createTitle(title);
    addComponent(header);
    setComponentAlignment(header, alignment);
    addComponent(createUserNameField());
    addComponent(createPasswordField());

    addComponent(createButtonContainer());
  }

  private Component createTitle(String title) {
    Label label = new Label(title);
    return label;
  }

  private TextField createUserNameField() {
    final TextField userName = new TextField("Username");
    userName.setIcon(FontAwesome.USER);
    userName.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
    userName.focus();
    return userName;
  }

  private PasswordField createPasswordField() {
    final PasswordField password = new PasswordField("Password");
    password.setIcon(FontAwesome.LOCK);
    password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
    return password;
  }

  private Component createButtonContainer() {
    HorizontalLayout buttonContainer = new HorizontalLayout();
    buttonContainer.setSizeFull();

    Button cancel = createCancelButton();
    buttonContainer.addComponent(cancel);
    buttonContainer.setComponentAlignment(cancel, Alignment.BOTTOM_LEFT);

    Button login = createLoginButton();
    buttonContainer.addComponent(login);
    buttonContainer.setComponentAlignment(login, Alignment.BOTTOM_RIGHT);
    return buttonContainer;
  }

  private Button createCancelButton() {
    final Button cancel = new Button("Back");
    cancel.addStyleName(ValoTheme.BUTTON_PRIMARY);
    return cancel;
  }

  private Button createLoginButton() {
    final Button login = new Button("OK");
    login.addStyleName("left-sign-up-button");
    login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    return login;
  }

}
