package com.teamlans.lepta.view.account.components;

import com.ejt.vaadin.loginform.LoginForm;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.Credentials;
import com.teamlans.lepta.view.account.SignUpView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * PartnerSignUp displays a users name and offers a SignUpForm for their partner.
 */
public class PartnerSignUp extends HorizontalLayout {

  final private SignUpView parent;

  public PartnerSignUp(SignUpView parent, String name) {
    this.parent = parent;
    buildInfo(name);
    buildSignUpForm();
  }

  private void buildSignUpForm() {
    VerticalLayout container = new VerticalLayout();
    container.setSizeFull();
    addComponent(container);

    VerticalLayout right = new VerticalLayout();
    right.setSizeUndefined();
    container.addComponent(right);
    container.setComponentAlignment(right, Alignment.MIDDLE_RIGHT);

    Label title = new Label("Add your partner:");
    right.addComponent(title);
    right.setComponentAlignment(title, Alignment.MIDDLE_RIGHT);
    SignUpForm rightSignUpForm = createPartnerForm();
    rightSignUpForm.setSizeUndefined();

    Button cancelButton = rightSignUpForm.getCancelButton();
    cancelButton.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        parent.showInitialSignUp();
      }
    });

    right.addComponent(rightSignUpForm);
    right.setComponentAlignment(rightSignUpForm, Alignment.MIDDLE_RIGHT);
  }

  private void buildInfo(String name) {
    VerticalLayout container = new VerticalLayout();
    addComponent(container);
    setHeight("100%");
    setWidth("400");

    VerticalLayout center = new VerticalLayout();
    container.addComponent(center);
    container.setComponentAlignment(center, Alignment.MIDDLE_LEFT);

    Label title = new Label("Your account:");
    center.addComponent(title);

    Label username = new Label(name);
    center.addComponent(username);
  }

  private SignUpForm createPartnerForm() {
    SignUpForm signUpForm = new SignUpForm();
    signUpForm.addLoginListener(new LoginForm.LoginListener() {
      @Override
      public void onLogin(LoginForm.LoginEvent event) {
        try {
          parent.setPartnerAccount(new Credentials(event.getUserName(), event.getPassword()));
          parent.finishAndGoHome();
        } catch (LeptaServiceException e) {
          parent.showNotification(e.getMessage());
        }
      }
    });
    return signUpForm;
  }

}
