package com.teamlans.lepta.view.component;

import com.teamlans.lepta.view.HomeView;
import com.teamlans.lepta.view.NewBillsView;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;

public class NavigationBar extends HorizontalLayout {

  public NavigationBar() {
    this.setSpacing(true);
    this.addStyleName("navbar");
    this.addComponent(createNavigationButton("Home", HomeView.VIEW_NAME));
    this.addComponent(createBillMenu());
    this.addComponent(createNavigationButton("Assign Bills", NewBillsView.VIEW_NAME));
    this.addComponent(createNavigationButton("Clear Debt", NewBillsView.VIEW_NAME));
  }

  private Button createNavigationButton(String caption, final String viewName) {
    Button button = new Button(caption);
    button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
    button.addStyleName("navbutton");
    // If you didn't choose Java 8 when creating the project, convert this to an anonymous listener class
    button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
    return button;
  }

  private MenuBar createBillMenu() {
    MenuBar menuBar = new MenuBar();
    menuBar.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
    menuBar.addStyleName("navmenu");
    MenuBar.MenuItem item = menuBar.addItem("My Bills", null, null);
    item.addItem("New Bills", event -> getUI().getNavigator().navigateTo(NewBillsView.VIEW_NAME));
    item.addItem("Assigned Bills",
        event -> getUI().getNavigator().navigateTo(NewBillsView.VIEW_NAME));
    item.addItem("Archive", event -> getUI().getNavigator().navigateTo(NewBillsView.VIEW_NAME));
    return menuBar;
  }



}
