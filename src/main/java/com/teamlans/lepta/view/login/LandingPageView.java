package com.teamlans.lepta.view.login;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class LandingPageView extends VerticalLayout implements View {

  public LandingPageView() {
    build();
  }

  private void build() {
    setSizeFull();

    VerticalLayout center = new VerticalLayout();
    addComponent(center);
    setComponentAlignment(center, Alignment.MIDDLE_CENTER);

    Label title = new Label("Welcome!");
    center.addComponent(title);
    title.setWidth(null);
    center.setComponentAlignment(title, Alignment.MIDDLE_CENTER);

    Label text = new Label("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam " +
        "nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam " +
        "voluptua.");
    center.addComponent(text); // TODO: better position
    text.setWidth("90%");
    center.setComponentAlignment(text, Alignment.MIDDLE_CENTER);

    Button button = new Button("Start");
    center.addComponent(button);
    center.setComponentAlignment(button, Alignment.BOTTOM_CENTER);
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent event) {
  }

}
