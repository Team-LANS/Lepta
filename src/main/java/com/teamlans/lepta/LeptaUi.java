package com.teamlans.lepta;

import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.service.user.UserService;
import com.teamlans.lepta.view.ProtectedHorizontalView;
import com.teamlans.lepta.view.ProtectedVerticalView;
import com.teamlans.lepta.view.account.LoginView;
import com.teamlans.lepta.view.account.SignUpView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window (or tab) or
 * some part of a html page where a Vaadin application is embedded. <p> The UI is initialized using
 * {@link #init(VaadinRequest)}. This method is intended to be overridden to add component to the
 * user interface and initialize non-component functionality.
 */
@SpringUI
@Theme("lepta")
@Widgetset("com.teamlans.lepta.MyAppWidgetset")
public class LeptaUi extends UI {

  @Autowired
  private SpringViewProvider viewProvider;

  @Autowired
  private ApplicationContext context;

  @Autowired
  private UserService userService;
  private User loggedInUser;
  private Navigator navigator;

  @Override
  protected void init(VaadinRequest request) {

    navigator = new Navigator(this, this);
    navigator.addProvider(viewProvider);
    navigator.addViewChangeListener(new ViewChangeListener() {
      @Override
      public boolean beforeViewChange(ViewChangeEvent viewChangeEvent) {
        if (isProtected(viewChangeEvent.getNewView()) &&
            ((LeptaUi) getUI()).getLoggedInUser() == null) {
          goToCorrectWelcomeView();
          return false;
        } else {
          return true;
        }
      }

      @Override
      public void afterViewChange(ViewChangeEvent viewChangeEvent) {
        // needed when overriding beforeViewChange
      }
    });

    goToCorrectWelcomeView();

  }

  private void goToCorrectWelcomeView() {
    if (userService.noUsersExist()) {
      navigator.navigateTo(SignUpView.VIEW_NAME);
    } else {
      navigator.navigateTo(LoginView.VIEW_NAME);
    }
  }

  private boolean isProtected(View view) {
    return view instanceof ProtectedVerticalView || view instanceof ProtectedHorizontalView;
  }

  public User getLoggedInUser() {
    return loggedInUser;
  }

  public void setLoggedInUser(User loggedInUser) {
    this.loggedInUser = loggedInUser;
  }

  @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)

  @VaadinServletConfiguration(ui = LeptaUi.class, productionMode = false)
  public static class MyUIServlet extends VaadinServlet {
  }
}
