package com.teamlans.lepta.view.login;

import com.ejt.vaadin.loginform.LoginForm;
import com.teamlans.lepta.service.exceptions.LeptaLoginException;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.login.LoginService;
import com.teamlans.lepta.view.component.forms.LeptaLoginForm;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout {

  private LoginService loginService;

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

    loginService = new LoginService();
    LeptaLoginForm loginForm = new LeptaLoginForm();
    loginForm.addLoginListener(new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        try {
          loginService.authenticate(event.getUserName(), event.getPassword());
        } catch (LeptaLoginException e) {

        } catch (LeptaServiceException e) {

        }
      }
    });
    center.addComponent(loginForm);
    center.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
  }

}