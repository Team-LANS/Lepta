package com.teamlans.lepta.view.account;

import com.teamlans.lepta.LeptaUi;
import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.service.exceptions.LeptaLoginException;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.UserService;
import com.teamlans.lepta.view.LeptaNotification;
import com.teamlans.lepta.view.MainView;
import com.teamlans.lepta.view.account.components.LeptaLoginForm;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 * Custom login view. Credentials can be entered into a LeptaLoginForm and are handled by a
 * UserService.
 */
@org.springframework.stereotype.Component
@Scope("request")
public final class LoginView extends VerticalLayout implements View {

  private static final Logger logger = LoggerFactory.getLogger(LoginView.class);

  @Autowired
  private UserService service;

  public LoginView() {
    build();
  }

  private void build() {
    setSizeFull();

    final Panel center = new Panel("WELCOME");
    center.setSizeUndefined();
    addComponent(center);
    setComponentAlignment(center, Alignment.MIDDLE_CENTER);

    final LeptaLoginForm loginForm = buildLoginForm();
    center.setContent(loginForm);
  }

  private LeptaLoginForm buildLoginForm() {
    final LeptaLoginForm loginForm = new LeptaLoginForm();
    loginForm.addLoginListener(event -> {
      try {
        User user = service.authenticate(event.getUserName(), event.getPassword());
        ((LeptaUi) (UI.getCurrent())).setLoggedInUser(user);
        getUI().setContent(new MainView());
        Navigator navigator = UI.getCurrent().getNavigator();
        navigator.navigateTo(navigator.getState());
      } catch (LeptaLoginException e) {
        logger.error("Error", e);
        LeptaNotification.showWarning("Login failed",
            "This username and password combination does not exist.\nPlease try again.");
      } catch (LeptaServiceException e) {
        logger.error("Error", e);
        LeptaNotification.showWarning("Login failed", "Something went wrong.\nPlease try again.");
      }
    });
    return loginForm;
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // needed for interface implementation
  }

}
