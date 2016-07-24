package com.teamlans.lepta.view.account.components;

import com.teamlans.lepta.view.account.SignUpView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Visible for first time visitors (database is empty).
 */
public class WelcomePage extends VerticalLayout {

  private final SignUpView parent;

  public WelcomePage(SignUpView parent) {
    this.parent = parent;
    build();
  }

  private void build() {
    setSizeFull();

    VerticalLayout center = new VerticalLayout();
    addComponent(center);
    setComponentAlignment(center, Alignment.MIDDLE_CENTER);
    buildText(center);

    Button button = new Button("Start");
    button.addClickListener(new Button.ClickListener() {
      @Override
      public void buttonClick(Button.ClickEvent clickEvent) {
        parent.showInitialSignUp();
      }
    });
    center.addComponent(button);
    center.setComponentAlignment(button, Alignment.BOTTOM_CENTER);
  }

  private void buildText(VerticalLayout center) {
    Label title = new Label("Welcome!");
    title.setWidth(null);
    title.setStyleName("welcome");
    center.addComponent(title);
    center.setComponentAlignment(title, Alignment.MIDDLE_CENTER);

    Label text = new Label("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam " +
        "nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam " +
        "voluptua.");
    center.addComponent(text); // TODO: better position
    text.setWidth("90%");
    center.setComponentAlignment(text, Alignment.MIDDLE_CENTER);
  }

}
