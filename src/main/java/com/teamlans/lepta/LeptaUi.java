package com.teamlans.lepta;

import com.teamlans.lepta.view.home.component.Header;
import com.teamlans.lepta.view.home.component.NavigationBar;
import com.teamlans.lepta.view.account.SignUpView;
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

  @Override
  protected void init(VaadinRequest request) {
    final VerticalLayout root = new VerticalLayout();
    root.setSizeFull();
    root.setMargin(true);
    root.setSpacing(true);
    Responsive.makeResponsive(root);
    setContent(root);

    if (true) {
      // first visit

      Navigator navigator = new Navigator(this, root);
      navigator.addProvider(viewProvider);
      navigator.navigateTo(SignUpView.VIEW_NAME);

    } else {
      root.addComponent(new Header());
      root.addComponent(new NavigationBar());

      final VerticalLayout viewContainer = new VerticalLayout();
      viewContainer.setSizeFull();
      root.addComponent(viewContainer);
      root.setExpandRatio(viewContainer, 1.0f);
    }

  }

  @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)

  @VaadinServletConfiguration(ui = LeptaUi.class, productionMode = false)
  public static class MyUIServlet extends VaadinServlet {
  }
}
