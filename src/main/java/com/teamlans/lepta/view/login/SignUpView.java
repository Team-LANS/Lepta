package com.teamlans.lepta.view.login;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

public class SignUpView extends VerticalLayout implements View {

  public SignUpView() {
    build();
  }

  private void build() {
    setSizeFull();

    VerticalLayout left = addFirstSignUpForm();
    addComponent(left);

    VerticalLayout right = addSecondSignUpForm();
    addComponent(right);
  }

  private VerticalLayout addFirstSignUpForm() {
    return null;
  }

  private VerticalLayout addSecondSignUpForm() {
    return null;
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent event) {
  }

}
