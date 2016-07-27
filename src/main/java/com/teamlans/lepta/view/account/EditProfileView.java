package com.teamlans.lepta.view.account;

import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.entities.enums.Color;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * EditProfileView allows users to change their account details.
 */
@SpringView(name = EditProfileView.VIEW_NAME)
public final class EditProfileView extends ProtectedVerticalView {

  public static final String VIEW_NAME = "EditProfile";

  private UserService userService;

  private final User user;

  @Autowired
  public EditProfileView(UserService userService) {
    this.userService = userService; // because of weird bug, issue #32
    user = getLoggedInUser();
    System.out.println(user);

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
    HorizontalLayout container = new HorizontalLayout();

    container.addComponent(buildColorButton(Color.DARK_BLUE));
    container.addComponent(buildColorButton(Color.LIGHT_BLUE));
    container.addComponent(buildColorButton(Color.GREEN));
    container.addComponent(buildColorButton(Color.YELLOW));
    container.addComponent(buildColorButton(Color.ORANGE));
    container.addComponent(buildColorButton(Color.RED));

    return container;
  }

  private Button buildColorButton(Color color) {
    Button button = new Button(color.toString().replaceAll("_", " "));
    try {
      boolean taken = userService.isTaken(color);
      if (!taken) {
        button.addClickListener(new Button.ClickListener() {
          @Override
          public void buttonClick(Button.ClickEvent clickEvent) {
            user.setColor(color);
            userService.updateUser(user);
            Notification.show("Success"); // feedback; will be removed when css is added

            //refresh
            removeAllComponents();
            addComponent(new EditProfileView(userService));
          }
        });
      }
    } catch (LeptaServiceException e) {
      Notification.show(e.getMessage());
    }
    return button;
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
          userService.updateUser(user);

          // refresh page
          removeAllComponents();
          addComponent(new EditProfileView(userService));
        }
      }
    });
    return button;
  }

  private Component buildPasswordForm() {
    final VerticalLayout container = new VerticalLayout();

    final PasswordField oldField = new PasswordField("Old password:");
    container.addComponent(oldField);

    final PasswordField newField = new PasswordField("New password:");
    container.addComponent(newField);

    final PasswordField confirmationField = new PasswordField("Confirm new password:");
    container.addComponent(confirmationField);

    container.addComponent(buildPasswordButton(oldField, newField, confirmationField));

    return container;
  }

  private Button buildPasswordButton(PasswordField oldField, PasswordField newField,
                                     PasswordField confirmationField) {
    final Button button = new Button("OK");
    button.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        if (oldField.getValue().equals(user.getPassword())) {
          String newPassword = newField.getValue();
          if (!newPassword.isEmpty() && newPassword.equals(confirmationField.getValue())) {
            user.setPassword(newPassword);
            userService.updateUser(user);

            oldField.clear();
            newField.clear();
            confirmationField.clear();
            Notification.show("Change successful");
          } else {
            Notification.show("Two different new passwords");
          }
        } else {
          Notification.show("Wrong password");
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
