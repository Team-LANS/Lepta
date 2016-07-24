package com.teamlans.lepta.view.account;

import com.ejt.vaadin.loginform.LoginForm;
import com.teamlans.lepta.service.exceptions.LeptaLoginException;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.UserService;
import com.teamlans.lepta.view.account.components.LeptaLoginForm;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


/**
 * Custom account layout. Credentials can be entered into a LeptaLoginForm and are handled by a
 * UserService.
 */
public class LoginView extends VerticalLayout {

  private UserService userService;

  public LoginView() {
    userService = new UserService();
    build();
  }

  private void build() {
    setSizeFull();

    final VerticalLayout center = createContainerWithTitle("WELCOME");
    addComponent(center);
    setComponentAlignment(center, Alignment.MIDDLE_CENTER);

    final LeptaLoginForm loginForm = createLoginForm();
    center.addComponent(loginForm);
    center.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
  }

  private VerticalLayout createContainerWithTitle(String text) {
    final VerticalLayout container = new VerticalLayout();

    final Label title = new Label(text);
    title.setSizeUndefined();
    container.addComponent(title);
    container.setComponentAlignment(title, Alignment.TOP_CENTER);

    return container;
  }

  private LeptaLoginForm createLoginForm() {
    final LeptaLoginForm loginForm = new LeptaLoginForm();
    loginForm.addLoginListener(new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        try {
          userService.authenticate(event.getUserName(), event.getPassword());
          // TODO: load content
        } catch (LeptaLoginException e) {
          showNotification("Login failed", "This username and password combination does not " +
              "exist.\nPlease try again.");
        } catch (LeptaServiceException e) {
          showNotification("Login failed", "Something went wrong.\nPlease try again.");
        }
      }
    });
    return loginForm;
  }

  private void showNotification(String caption, String description) {
    Notification notification = new Notification(caption, description);
    notification.setPosition(Position.BOTTOM_CENTER);
    notification.setStyleName(ValoTheme.NOTIFICATION_ERROR + " " + ValoTheme.NOTIFICATION_CLOSABLE);
    notification.setDelayMsec(5000);
    notification.show(Page.getCurrent());
  }

}
