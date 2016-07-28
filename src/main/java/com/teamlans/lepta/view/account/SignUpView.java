package com.teamlans.lepta.view.account;

import com.teamlans.lepta.LeptaUi;
import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.Credentials;
import com.teamlans.lepta.service.user.UserService;
import com.teamlans.lepta.view.LeptaNotification;
import com.teamlans.lepta.view.MainView;
import com.teamlans.lepta.view.account.components.InitialSignUp;
import com.teamlans.lepta.view.account.components.PartnerSignUp;
import com.teamlans.lepta.view.account.components.WelcomePage;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * SignUpView is shown when the database is still empty. Two user accounts are created in three
 * steps.
 */
@Component
public final class SignUpView extends HorizontalLayout implements View {

  private static final Logger logger = LoggerFactory.getLogger(SignUpView.class);

  @Autowired
  private ApplicationContext context;

  @Autowired
  private UserService service;

  private Credentials initial;
  private Credentials partner;

  public SignUpView() {
    setSizeFull();
    showWelcomePage();
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // needed for View implementation
  }

  public void setInitialAccount(Credentials initial) {
    this.initial = initial;
  }

  public void setPartnerAccount(Credentials partner) {
    this.partner = partner;
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
    addComponent(new PartnerSignUp(this, initial.getName()));

  }

  public void finishAndGoHome() { // ;-)
    try {
      User user = service.createAccounts(initial, partner);
      ((LeptaUi) getUI()).setLoggedInUser(user); // not tested!
      getUI().setContent(context.getBean(MainView.class));
    } catch (LeptaServiceException e) {
      logger.error("Error",e);
      LeptaNotification.showError(e.getMessage());
    }
  }
}
