package com.teamlans.lepta.view.account;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * This class allows users to change their account details.
 */
@SpringView(name = EditProfileView.VIEW_NAME)
public final class EditProfileView extends VerticalLayout implements View {

  public static final String VIEW_NAME = "EditProfile";
  private String name = "Test"; // TODO: get username
  private Panel nameChanger;
  private Panel passwordChanger;
  private Panel colorChanger;

  public EditProfileView() {
    build();
  }

  private void build() {

    Component title = buildTitle();
    addComponent(title);
    setComponentAlignment(title, Alignment.TOP_LEFT);

    Component changers = buildChangers();
    addComponent(changers);
    setComponentAlignment(changers, Alignment.TOP_CENTER);
  }

  private Component buildTitle() {
    Label title = new Label("Hello " + name + ".");
    title.setSizeUndefined();
    return title;
  }

  private Component buildChangers() {
    VerticalLayout container = new VerticalLayout();
    container.setSizeUndefined();

    nameChanger = new Panel("Change your username");
    nameChanger.setContent(buildNameForm());
    container.addComponent(nameChanger);

    colorChanger = new Panel("Select a new color");
    colorChanger.setContent(buildColorPicker());
    container.addComponent(colorChanger);

    passwordChanger = new Panel("Change your password");
    passwordChanger.setContent(buildPasswordForm());
    container.addComponent(passwordChanger);

    return container;
  }

  private Component buildNameForm() {
    TextField nameField = new TextField();
    nameField.setInputPrompt("Enter a new name");
    return nameField;
  }

  private Component buildColorPicker() {
    HorizontalLayout container = new HorizontalLayout();

    Button darkBlue = new Button();
    container.addComponent(darkBlue);

    Button lightBlue = new Button();
    container.addComponent(lightBlue);

    Button green = new Button();
    container.addComponent(green);

    Button yellow = new Button();
    container.addComponent(yellow);

    Button orange = new Button();
    container.addComponent(orange);

    Button red = new Button();
    container.addComponent(red);

    return container;
  }

  private Component buildPasswordForm() {
    VerticalLayout container = new VerticalLayout();

    PasswordField oldPassword = new PasswordField("Old password:");
    container.addComponent(oldPassword);

    PasswordField newPassword = new PasswordField("New password:");
    container.addComponent(newPassword);

    PasswordField confirmPassword = new PasswordField("Confirm new password:");
    container.addComponent(confirmPassword);

    return container;
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // needed for View implementation
  }

}
