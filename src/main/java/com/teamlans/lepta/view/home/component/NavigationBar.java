package com.teamlans.lepta.view.home.component;

import com.teamlans.lepta.view.account.EditProfileView;
import com.teamlans.lepta.view.account.LoginView;
import com.teamlans.lepta.view.home.HomeView;
import com.teamlans.lepta.view.bill.NewBillsView;
import com.vaadin.server.FontAwesome;
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

    addProfileMenu();
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


  private void addProfileMenu() {
    // TODO: get username, add icon, align right
    MenuBar menuBar = new MenuBar();

    MenuBar.MenuItem profileMenu = menuBar.addItem("Username", null, null);
    addSubMenuLink(profileMenu, "Edit profile", FontAwesome.COG, EditProfileView.VIEW_NAME);
    addSubMenuLink(profileMenu, "Log out", FontAwesome.SIGN_OUT, LoginView.VIEW_NAME);

    addComponent(menuBar);
  }

  private void addSubMenuLink(MenuBar.MenuItem item, String name, FontAwesome icon, String goal) {
    item.addItem(name, icon, new MenuBar.Command() {
      @Override
      public void menuSelected(MenuBar.MenuItem menuItem) {
        goTo(goal);
      }
    });
  }

  private void goTo(String goal) {
    getUI().getNavigator().navigateTo(goal);
  }

}
