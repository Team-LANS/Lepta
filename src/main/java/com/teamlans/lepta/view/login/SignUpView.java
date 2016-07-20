package com.teamlans.lepta.view.login;

import com.teamlans.lepta.view.component.forms.SignUpForm;
import com.vaadin.ui.*;

public class SignUpView extends VerticalLayout {

  public SignUpView() {
    build();
  }

  private void build() {
    setSizeFull();

    VerticalLayout left = new VerticalLayout();
    addComponent(left);
    setComponentAlignment(left, Alignment.MIDDLE_LEFT);

    SignUpForm signUpForm = new SignUpForm();
    left.addComponent(signUpForm);
    left.setComponentAlignment(signUpForm, Alignment.MIDDLE_LEFT);
  }

}
