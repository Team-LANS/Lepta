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
@SpringView(name = HomeView.VIEW_NAME) public class HomeView extends VerticalLayout
    implements View {

  public static final String VIEW_NAME = "";

  @PostConstruct void init() {
    setMargin(true);
    setSpacing(true);
    addComponent(new Label("This is a default view"));
  }

  @Override public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    //View is constructed in init method
  }
}