package com.teamlans.lepta.view.account;

import com.ejt.vaadin.loginform.LoginForm;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.Credentials;
import com.teamlans.lepta.service.user.UserService;
import com.teamlans.lepta.view.account.components.SignUpForm;
import com.teamlans.lepta.view.home.HomeView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = SignUpView.VIEW_NAME)
public class SignUpView extends HorizontalLayout implements View {

  public static final String VIEW_NAME = "SignUp";
  @Autowired
  private UserService userService;
  private Credentials account0;
  private Credentials account1;

  public SignUpView() {
    setSizeFull();
    createSignUpForm();
  }

  private void createSignUpForm() {
    VerticalLayout container = new VerticalLayout();
    addComponent(container);

    Label title = new Label("Create your account:");
    container.addComponent(title);
    SignUpForm leftSignUpForm = new SignUpForm();
    leftSignUpForm.addLoginListener(new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        try {
          account0 = new Credentials(event.getUserName(), event.getPassword());
          continueSignUp();
        } catch (LeptaServiceException e) {
          showNotification(e.getMessage());
        }
      }
    });

    container.addComponent(leftSignUpForm);
    container.setComponentAlignment(leftSignUpForm, Alignment.MIDDLE_LEFT);

    Button cancelButton = leftSignUpForm.getCancelButton();
    cancelButton.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        removeAllComponents();
        addComponent(new LandingView());
      }
    });
  }

  private void continueSignUp() {
    removeAllComponents();
    Component info = createInfo();
    info.setSizeFull();
    addComponent(info);
    setComponentAlignment(info, Alignment.MIDDLE_LEFT);

    VerticalLayout rightContainer = new VerticalLayout();
    rightContainer.setSizeFull();
    addComponent(rightContainer);

    VerticalLayout right = new VerticalLayout();
    right.setSizeUndefined();
    rightContainer.addComponent(right);
    rightContainer.setComponentAlignment(right, Alignment.MIDDLE_RIGHT);

    Label title = new Label("Add your partner:");
    right.addComponent(title);
    right.setComponentAlignment(title, Alignment.MIDDLE_RIGHT);
    SignUpForm rightSignUpForm = createPartnerForm();
    rightSignUpForm.setSizeUndefined();

    Button cancelButton = rightSignUpForm.getCancelButton();
    cancelButton.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        removeAllComponents();
        createSignUpForm();
      }
    });

    right.addComponent(rightSignUpForm);
    right.setComponentAlignment(rightSignUpForm, Alignment.MIDDLE_RIGHT);
  }

  private Component createInfo() {
    VerticalLayout layout = new VerticalLayout();
    setHeight("100%");
    setWidth("400");

    VerticalLayout center = new VerticalLayout();
    layout.addComponent(center);
    layout.setComponentAlignment(center, Alignment.MIDDLE_LEFT);

    Label title = new Label("Your account:");
    center.addComponent(title);

    Label username = new Label(account0.getName());
    center.addComponent(username);

    return layout;
  }

  private SignUpForm createPartnerForm() {
    SignUpForm signUpForm = new SignUpForm();
    signUpForm.addLoginListener(new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        try {
          account1 = new Credentials(event.getUserName(), event.getPassword());
          userService.createAccounts(account0, account1);
          getUI().getNavigator().navigateTo(HomeView.VIEW_NAME);
        } catch (LeptaServiceException e) {
          showNotification(e.getMessage());
        }
      }
    });
    return signUpForm;
  }

  private void showNotification(String description) {
    Notification notification = new Notification("", description);
    notification.setPosition(Position.BOTTOM_CENTER);
    notification.setStyleName(ValoTheme.NOTIFICATION_ERROR + " " + ValoTheme.NOTIFICATION_CLOSABLE);
    notification.setDelayMsec(5000);
    notification.show(Page.getCurrent());
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

  }
}
