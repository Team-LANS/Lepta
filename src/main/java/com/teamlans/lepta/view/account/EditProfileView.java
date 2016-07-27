package com.teamlans.lepta.view.account;

import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.service.user.UserService;
import com.teamlans.lepta.view.ProtectedVerticalView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * This view allows users to change their account details.
 */
@SpringView(name = EditProfileView.VIEW_NAME)
public final class EditProfileView extends ProtectedVerticalView {

  public static final String VIEW_NAME = "EditProfile";
  @Autowired
  private UserService userService;

  private final User user = new User(); // TODO: get user

  public EditProfileView() {
    build();
  }

  private void build() {
    // TODO: write to database!
    // TODO: add validators
    final Component title = buildTitle();
    addComponent(title);
    setComponentAlignment(title, Alignment.TOP_LEFT);

    final Component changers = buildChangers();
    addComponent(changers);
    setComponentAlignment(changers, Alignment.TOP_CENTER);
  }

  private Component buildTitle() {
    final Label title = new Label("Hello " + user.getName() + ".");
    title.setSizeUndefined();
    return title;
  }

  private Component buildChangers() {
    final Accordion accordion = new Accordion();
    accordion.setSizeUndefined();
    accordion.setWidth("70%");

    accordion.addTab(buildColorPicker(), "Select a new color");
    accordion.addTab(buildNameChanger(), "Change your username");
    accordion.addTab(buildPasswordForm(), "Change your password");

    return accordion;
  }

  private Component buildColorPicker() {
    // TODO: implement color picker
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

  private Component buildNameChanger() {
    final HorizontalLayout container = new HorizontalLayout();

    final TextField nameField = new TextField();
    nameField.setInputPrompt("Enter a new name");

    container.addComponent(nameField);
    container.addComponent(buildNameButton(nameField));

    return container;
  }

  private Button buildNameButton(TextField nameField) {
    final Button button = new Button("OK");
    button.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        String newName = nameField.getValue();
        if (!newName.isEmpty() && !newName.equals(user.getName())) {
          user.setName(newName);
          removeAllComponents();
          build();
        }
      }
    });
    return button;
  }

  private Component buildPasswordForm() {
    final VerticalLayout container = new VerticalLayout();

    final PasswordField old = new PasswordField("Old password:");
    container.addComponent(old);

    final PasswordField newPassword = new PasswordField("New password:");
    container.addComponent(newPassword);

    final PasswordField confirmed = new PasswordField("Confirm new password:");
    container.addComponent(confirmed);

    container.addComponent(buildPasswordButton(old, newPassword, confirmed));

    return container;
  }

  private Button buildPasswordButton(PasswordField old, PasswordField newPassword,
                                     PasswordField confirmed) {
    final Button button = new Button("OK");
    button.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        if (old.getValue().equals(user.getPassword())) {
          if (newPassword.getValue().equals(confirmed.getValue())) {
            user.setPassword(newPassword.getValue());
            removeAllComponents();
            build();
          } else {
            // TODO: "two different inputs"
          }
        } else {
          // TODO: "wrong password"
        }
      }
    });
    return button;
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // needed for View implementation
  }

}
