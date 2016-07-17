package com.teamlans.lepta.view.component;

import com.teamlans.lepta.view.OtherView;
import com.teamlans.lepta.view.ScopedView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Hans-Joerg Schroedl
 */
public class NavigationBar extends HorizontalLayout {

  public NavigationBar(){
    this.setSpacing(true);
    this.addStyleName("navbar");
    this.addComponent(createNavigationButton("View Scoped View", ScopedView.VIEW_NAME));
    this.addComponent(createNavigationButton("View Scoped View", OtherView.VIEW_NAME));
  }

  private Button createNavigationButton(String caption, final String viewName) {
    Button button = new Button(caption);
    button.addStyleName(ValoTheme.BUTTON_SMALL);
    // If you didn't choose Java 8 when creating the project, convert this to an anonymous listener class
    button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
    return button;
  }




}
