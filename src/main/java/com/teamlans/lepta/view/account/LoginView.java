package com.teamlans.lepta.view.account;

import com.ejt.vaadin.loginform.LoginForm;
import com.teamlans.lepta.LeptaUi;
import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.service.exceptions.LeptaLoginException;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.UserService;
import com.teamlans.lepta.view.account.components.LeptaLoginForm;
import com.teamlans.lepta.view.bill.NewBillsView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Custom login view. Credentials can be entered into a LeptaLoginForm and are handled by a
 * UserService.
 */
@SpringView(name = LoginView.VIEW_NAME)
public final class LoginView extends VerticalLayout implements View {

  public static final String VIEW_NAME = "Login";
  @Autowired
  private UserService userService;

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
    loginForm.addLoginListener(new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        try {
          User user = userService.authenticate(event.getUserName(), event.getPassword());
          ((LeptaUi)getUI()).setLoggedInUser(user); // TODO: why does UI.getCurrent() not work?
          getUI().getNavigator().navigateTo(NewBillsView.VIEW_NAME);
        } catch (LeptaLoginException e) {
          showNotification("Login failed",
              "This username and password combination does not exist.\nPlease try again.");
        } catch (LeptaServiceException e) {
          showNotification("Login failed", "Something went wrong.\nPlease try again.");
        }
      }
    });
    return loginForm;
  }

  private void showNotification(String caption, String description) {
    final Notification notification = new Notification(caption, description);
    notification.setPosition(Position.BOTTOM_CENTER);
    notification.setStyleName(ValoTheme.NOTIFICATION_ERROR + " " + ValoTheme.NOTIFICATION_CLOSABLE);
    notification.setDelayMsec(5000);
    notification.show(Page.getCurrent());
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // needed for interface implementation
  }
}
