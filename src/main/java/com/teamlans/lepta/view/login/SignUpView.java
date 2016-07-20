package com.teamlans.lepta.view.login;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class SignUpView extends VerticalLayout {

  public SignUpView() {
    build();
  }

  private void build() {
    setSizeFull();

    final Component loginForm = buildLoginForm();
    addComponent(loginForm);
    setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
  }

  private Component buildLoginForm() {
    final VerticalLayout loginPanel = new VerticalLayout();
    loginPanel.setSizeUndefined();
    loginPanel.setSpacing(true);

    loginPanel.addComponent(new Label("Create your account"));
    loginPanel.addComponent(buildFields());
    return loginPanel;
  }

  private Component buildFields() {
    final VerticalLayout fields = new VerticalLayout();
    fields.setSpacing(true);

    final TextField username = new TextField("Username");
    username.setIcon(FontAwesome.USER);
    username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
    username.focus();

    final PasswordField password = new PasswordField("Password");
    password.setIcon(FontAwesome.LOCK);
    password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

    final HorizontalLayout align = new HorizontalLayout();
    align.setSpacing(true);

    final Button cancel = new Button("Cancel");
    cancel.addStyleName(ValoTheme.BUTTON_PRIMARY);
    cancel.setClickShortcut(ShortcutAction.KeyCode.ENTER);

    final Button ok = new Button("OK");
    ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
    ok.setClickShortcut(ShortcutAction.KeyCode.ENTER);

    fields.addComponents(username, password, align);
    fields.setComponentAlignment(align, Alignment.BOTTOM_RIGHT);
    align.addComponent(cancel);
    align.addComponent(ok);

    return fields;
  }

}
