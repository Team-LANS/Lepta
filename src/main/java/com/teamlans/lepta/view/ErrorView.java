package com.teamlans.lepta.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

/**
 * Displayed when someting goes wrong. Shows a default error message or a custom one.
 */
@SpringView(name = ErrorView.VIEW_NAME)
public class ErrorView extends GridLayout implements View {

  public static final String VIEW_NAME = "Error";
  private final String message;

  public ErrorView() {
    this("Something went wrong!");
  }

  public ErrorView(String message) {
    this.message = message;
    build();
  }

  private void build() {
    setSizeFull();

    final Label title = new Label("Oops");
    title.setSizeUndefined();
    addComponent(title);
    setComponentAlignment(title, Alignment.TOP_LEFT);

    final Label text = new Label(message);
    text.setSizeUndefined();
    addComponent(text);
    setComponentAlignment(text, Alignment.TOP_CENTER);
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // needed for View implementation
  }
}
