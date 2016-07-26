package com.teamlans.lepta.view.account.components;

import com.teamlans.lepta.view.account.SignUpView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Visible for first time visitors (database is empty).
 */
public final class WelcomePage extends VerticalLayout {

  private final SignUpView parent;

  public WelcomePage(SignUpView parent) {
    this.parent = parent;
    build();
  }

  private void build() {
    setSizeFull();

    final VerticalLayout center = new VerticalLayout();
    addComponent(center);
    setComponentAlignment(center, Alignment.MIDDLE_CENTER);

    addContent(center);
  }

  private void addContent(VerticalLayout center) {
    final Component title = buildTitle();
    center.addComponent(title);
    center.setComponentAlignment(title, Alignment.MIDDLE_CENTER);

    final Component text = buildText();
    center.addComponent(text); // TODO: better position
    center.setComponentAlignment(text, Alignment.MIDDLE_CENTER);

    final Component button = buildButton();
    center.addComponent(button);
    center.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
  }

  private Component buildTitle() {
    final Label title = new Label("Welcome!");
    title.setSizeUndefined();
    title.setStyleName("welcome");
    return title;
  }

  private Component buildText() {
    final Label text = new Label("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam " +
        "nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam " +
        "voluptua.");
    text.setHeightUndefined();
    text.setWidth("90%");
    return text;
  }

  private Component buildButton() {
    final Button button = new Button("Start");
    button.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        parent.showInitialSignUp();
      }
    });
    return button;
  }

}
