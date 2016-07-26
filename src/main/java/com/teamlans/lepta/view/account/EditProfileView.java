package com.teamlans.lepta.view.account;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
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
    setSizeFull();

    addTitle(this);

    Component changers = buildChangers();
    addComponent(changers);
    setComponentAlignment(changers, Alignment.TOP_CENTER);
  }

  private void addTitle(VerticalLayout container) {
    Label title = new Label("Hello " + name + ".");
    title.setSizeUndefined();
    container.addComponent(title);
    container.setComponentAlignment(title, Alignment.TOP_LEFT);
  }

  private Component buildChangers() {
    VerticalLayout container = new VerticalLayout();
    container.setSizeUndefined();

    nameChanger = new Panel("Change your username");
    nameChanger.setContent(buildNameForm());
    container.addComponent(nameChanger);

    passwordChanger = new Panel("Change your password");
    passwordChanger.setContent(buildPasswordForm());
    container.addComponent(passwordChanger);

    colorChanger = new Panel("Select a new color");
    colorChanger.setContent(buildColorPicker());
    container.addComponent(colorChanger);

    return container;
  }

  private Component buildNameForm() {
    HorizontalLayout container = new HorizontalLayout();
    TextField nameField = new TextField("Choose a new name:");
    container.addComponent(nameField);

    Button nameSetter = new Button("Change");
    nameSetter.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
      }
    });
    container.addComponent(nameSetter);
    container.setComponentAlignment(nameSetter, Alignment.BOTTOM_RIGHT);
    return container;
  }

  private Component buildPasswordForm() {
    HorizontalLayout container = new HorizontalLayout();
    TextField passwordField = new TextField();
    container.addComponent(passwordField);

    Button passwordSetter = new Button("Change");
    passwordSetter.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
      }
    });
    container.addComponent(passwordSetter);
    return container;
  }

  private Component buildColorPicker() {
    return null;
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // needed for View implementation
  }

}
