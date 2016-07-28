package com.teamlans.lepta.view.home.component;

import com.teamlans.lepta.LeptaUi;
import com.teamlans.lepta.view.account.EditProfileView;
import com.teamlans.lepta.view.home.HomeView;
import com.teamlans.lepta.view.bill.NewBillsView;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class NavigationBar extends HorizontalLayout {

  public NavigationBar() {
    setSizeUndefined();
    setWidth("100%");
    setSpacing(true);
    addStyleName("navbar");

    build();
  }

  public void build() {
    MenuBar mainMenu = buildMainMenu();
    addComponent(mainMenu);
    setComponentAlignment(mainMenu, Alignment.TOP_LEFT);

    MenuBar userMenu = buildUserMenu();
    addComponent(userMenu);
    setComponentAlignment(userMenu, Alignment.TOP_RIGHT);
  }

  private MenuBar buildMainMenu() {
    MenuBar menuBar = new MenuBar();
    menuBar.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
    menuBar.addStyleName("navmenu");

    menuBar.addItem("Home", FontAwesome.HOME, event -> goTo(HomeView.VIEW_NAME));

    MenuBar.MenuItem myBills = menuBar.addItem("My Bills", null);
    myBills.addItem("New Bills", null, event -> goTo(NewBillsView.VIEW_NAME));
    myBills.addItem("Assigned Bills", null, null);
    myBills.addItem("Archive", null, null);

    menuBar.addItem("Assign Bills", null, event -> goTo(NewBillsView.VIEW_NAME));

    menuBar.addItem("ClearDebt", null, null);

    return menuBar;
  }

  private MenuBar buildUserMenu() {
    MenuBar menuBar = new MenuBar();
    menuBar.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
    menuBar.addStyleName("navmenu");

    LeptaUi ui = ((LeptaUi) UI.getCurrent());

    MenuBar.MenuItem account = menuBar.addItem(ui.getLoggedInUser().getName(), null, null);
    account.addItem("Edit profile", FontAwesome.COG, event -> goTo(EditProfileView.VIEW_NAME));
    account.addItem("Log out", FontAwesome.SIGN_OUT, event -> {ui.setLoggedInUser(null); ui.goToCorrectWelcomeView();});

    return menuBar;
  }

  private void goTo(String goal) {
    getUI().getNavigator().navigateTo(goal);
  }

}
