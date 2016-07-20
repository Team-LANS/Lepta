package com.teamlans.lepta.view.login;

import com.teamlans.lepta.view.component.forms.LeptaLoginForm;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout {

  public LoginView() {
    build();
  }

  private void build() {
    setSizeFull();

    VerticalLayout center = new VerticalLayout();
    addComponent(center);
    setComponentAlignment(center, Alignment.MIDDLE_CENTER);

    Label welcome = new Label("WELCOME");
    welcome.setSizeUndefined();
    center.addComponent(welcome);
    center.setComponentAlignment(welcome, Alignment.TOP_CENTER);

    LeptaLoginForm loginForm = new LeptaLoginForm();
    center.addComponent(loginForm);
    center.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
  }

}