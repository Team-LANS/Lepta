package com.teamlans.lepta.view.account;

import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.Credentials;
import com.teamlans.lepta.service.user.UserService;
import com.teamlans.lepta.view.account.components.InitialSignUp;
import com.teamlans.lepta.view.account.components.PartnerSignUp;
import com.teamlans.lepta.view.account.components.WelcomePage;
import com.teamlans.lepta.view.home.HomeView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SignUpView is shown when the database is still empty. Two user accounts are created in three
 * steps.
 */
@Component
public final class SignUpView extends HorizontalLayout implements View {
  @Autowired
  private UserService userService;
  private Credentials initialCredentials;
  private Credentials partnerCredentials;

  public SignUpView() {
    setSizeFull();
    showWelcomePage();
  }

  public void showNotification(String description) {
    final Notification notification = new Notification("", description);
    notification.setPosition(Position.BOTTOM_CENTER);
    notification.setStyleName(ValoTheme.NOTIFICATION_ERROR + " " + ValoTheme.NOTIFICATION_CLOSABLE);
    notification.setDelayMsec(5000);
    notification.show(Page.getCurrent());
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // needed for View implementation
  }

  public void setInitialAccount(Credentials initialCredentials) {
    this.initialCredentials = initialCredentials;
  }

  public void setPartnerAccount(Credentials partnerCredentials) {
    this.partnerCredentials = partnerCredentials;
  }

  public void showWelcomePage() {
    removeAllComponents();
    addComponent(new WelcomePage(this));
  }

  public void showInitialSignUp() {
    removeAllComponents();
    addComponent(new InitialSignUp(this));
  }

  public void showPartnerSignUp() {
    removeAllComponents();
    addComponent(new PartnerSignUp(this, initialCredentials.getName()));

  }

  public void finishAndGoHome() { // ;-)
    try {
      userService.createAccounts(initialCredentials, partnerCredentials);
      getUI().getNavigator().navigateTo(HomeView.VIEW_NAME);
    } catch (LeptaServiceException e) {
      showNotification(e.getMessage());
    }
  }
}
