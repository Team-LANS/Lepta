package com.teamlans.lepta.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * @author Hans-Joerg Schroedl
 */
@SpringView(name = OtherView.VIEW_NAME) public class OtherView extends VerticalLayout implements View {

  public static final String VIEW_NAME = "OTHER_VIEW";

  @PostConstruct void init() {
    setMargin(false);
    setSpacing(true);
    addComponent(new Label("This is another view"));
  }

  @Override public void enter(ViewChangeListener.ViewChangeEvent event) {
    // the view is constructed in the init() method()
  }
}
