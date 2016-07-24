package com.teamlans.lepta;

import com.teamlans.lepta.view.component.Header;
import com.teamlans.lepta.view.component.NavigationBar;
import com.teamlans.lepta.view.login.LoginView;
import com.teamlans.lepta.view.login.SignUpView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;

import org.springframework.beans.factory.annotation.Autowired;

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

  @Override
  protected void init(VaadinRequest request) {
    final VerticalLayout root = new VerticalLayout();
    root.setSizeFull();
    root.setMargin(true);
    root.setSpacing(true);
    Responsive.makeResponsive(root);
    setContent(root);

    if (false) {
      // first visit
      root.addComponent(new SignUpView());

    } else {
      root.addComponent(new Header());
      root.addComponent(new NavigationBar());

      final VerticalLayout viewContainer = new VerticalLayout();
      viewContainer.setSizeFull();
      root.addComponent(viewContainer);
      root.setExpandRatio(viewContainer, 1.0f);

      Navigator navigator = new Navigator(this, viewContainer);
      navigator.addProvider(viewProvider);
    }

  }

  @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)

  @VaadinServletConfiguration(ui = LeptaUi.class, productionMode = false)
  public static class MyUIServlet extends VaadinServlet {
  }
}
