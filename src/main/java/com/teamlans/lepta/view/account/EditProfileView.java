package com.teamlans.lepta.view.account;

import com.teamlans.lepta.entities.User;
import com.teamlans.lepta.entities.enums.Color;
import com.teamlans.lepta.service.exceptions.LeptaServiceException;
import com.teamlans.lepta.service.user.PasswordEncryptionService;
import com.teamlans.lepta.service.user.UserService;
import com.teamlans.lepta.view.LeptaNotification;
import com.teamlans.lepta.view.ProtectedVerticalView;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
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
import org.springframework.context.annotation.Scope;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * EditProfileView allows users to change their account details.
 */
@SpringView(name = EditProfileView.VIEW_NAME)
@Scope("request")
public final class EditProfileView extends ProtectedVerticalView {

  public static final String VIEW_NAME = "EditProfile";

  private UserService service;
  private PasswordEncryptionService encryptionService;

  private static Logger logger = LoggerFactory.getLogger(EditProfileView.class);

  private final User user;

  @Autowired
  public EditProfileView(UserService service, PasswordEncryptionService encryptionService) {
    this.service = service; // because of weird bug, issue #32
    this.encryptionService = encryptionService;
    user = getLeptaUi().getLoggedInUser();

    build();
  }

  private void build() {
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
      boolean taken = service.isTaken(color);
      if (!taken) {
        button.addClickListener(clickEvent -> {
          user.setColor(color);
          service.updateUser(user);
          LeptaNotification.show("Success"); // feedback; will be removed when css is added

          //refresh page
          removeAllComponents();
          build();
        });
      }
    } catch (LeptaServiceException e) {
      logger.error("Error", e);
      LeptaNotification.showError(e.getMessage());
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
    button.addClickListener(clickEvent -> {
      String newName = nameField.getValue();
      if (!newName.isEmpty() && !newName.equals(user.getName())) {
        user.setName(newName);
        service.updateUser(user);

        // refresh
        // TODO: add event bus
        removeAllComponents();
        build();
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
    button.addClickListener(clickEvent -> {
      try {
        if (encryptionService.isValid(oldField.getValue(), user)) {
          String newPassword = newField.getValue();
          checkPassword(oldField, newField, confirmationField, newPassword);
        } else {
          LeptaNotification.showWarning("Wrong password");
        }
      } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
        logger.error("Error", e);
        LeptaNotification.showError("Something went wrong");
      }
    });
    return button;
  }

  private void checkPassword(PasswordField oldField, PasswordField newField, PasswordField confirmationField, String newPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
    if (!newPassword.isEmpty() && newPassword.equals(confirmationField.getValue())) {
      user.setPassword(encryptionService.getEncryptedPassword(newPassword, user.getSalt()));
      service.updateUser(user);

      oldField.clear();
      newField.clear();
      confirmationField.clear();
      LeptaNotification.show("Change successful");
    } else {
      LeptaNotification.showWarning("Two different new passwords");
    }
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // needed for View implementation
  }

}
