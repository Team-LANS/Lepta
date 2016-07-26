package com.teamlans.lepta.view.account;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * This class allows users to change their account details.
 */
@SpringView(name = EditProfileView.VIEW_NAME)
public final class EditProfileView extends VerticalLayout implements View {

  public static final String VIEW_NAME = "EditProfile";
  private String name = "Test"; // TODO: get username

  public EditProfileView() {
    build();
  }

  private void build() {
    setSizeFull();

    addTitle(this);
    addAccordion(this);
  }

  private void addTitle(VerticalLayout container) {
    Label title = new Label("Hello " + name + ".");
    title.setSizeUndefined();
    container.addComponent(title);
    container.setComponentAlignment(title, Alignment.TOP_LEFT);
  }

  private void addAccordion(VerticalLayout container) {
    Accordion accordion = new Accordion();

    

    container.addComponent(accordion);
    container.setComponentAlignment(accordion, Alignment.TOP_CENTER);
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // needed for View implementation
  }
}
