package com.teamlans.lepta.view.login;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class LogInView extends VerticalLayout {

  public LogInView() {
    build();
  }

  private void build() {
    setSizeFull();

    final Component logInForm = buildLoginForm();
    addComponent(logInForm);
    setComponentAlignment(logInForm, Alignment.MIDDLE_CENTER);

  }

  private Component buildLoginForm() {
    final VerticalLayout loginPanel = new VerticalLayout();
    loginPanel.setSizeUndefined();
    loginPanel.setSpacing(true);

    loginPanel.addComponent(new Label("Welcome"));
    loginPanel.addComponent(buildFields());
    loginPanel.addComponent(new CheckBox("Remember me", true));
    return loginPanel;
  }

  private Component buildFields() {
    final HorizontalLayout fields = new HorizontalLayout();
    fields.setSpacing(true);

    final TextField username = new TextField("Username");
    username.setIcon(FontAwesome.USER);
    username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
    username.focus();

    final PasswordField password = new PasswordField("Password");
    password.setIcon(FontAwesome.LOCK);
    password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

    final Button signIn = new Button("Sign In");
    signIn.addStyleName(ValoTheme.BUTTON_PRIMARY);
    signIn.setClickShortcut(ShortcutAction.KeyCode.ENTER);

    fields.addComponents(username, password, signIn);
    fields.setComponentAlignment(signIn, Alignment.BOTTOM_RIGHT);

    return fields;
  }

}