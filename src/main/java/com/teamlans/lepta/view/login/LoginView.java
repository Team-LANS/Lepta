package com.teamlans.lepta.view.login;

import com.ejt.vaadin.loginform.LoginForm;
import com.teamlans.lepta.service.exceptions.LeptaLoginException;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.UserService;
import com.teamlans.lepta.view.component.forms.LeptaLoginForm;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout {

  private UserService userService;

  public LoginView() {
    build();
  }

  private void build() {
    setSizeFull();

    final VerticalLayout center = new VerticalLayout();
    addComponent(center);
    setComponentAlignment(center, Alignment.MIDDLE_CENTER);

    Label welcome = new Label("WELCOME");
    welcome.setSizeUndefined();
    center.addComponent(welcome);
    center.setComponentAlignment(welcome, Alignment.TOP_CENTER);

    userService = new UserService();
    final LeptaLoginForm loginForm = new LeptaLoginForm();
    loginForm.addLoginListener(new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        try {
          userService.authenticate(event.getUserName(), event.getPassword());
        } catch (LeptaLoginException e) {
          // TODO
        } catch (LeptaServiceException e) {
          // TODO
        }
      }
    });
    center.addComponent(loginForm);
    center.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
  }

}
