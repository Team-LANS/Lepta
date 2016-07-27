package com.teamlans.lepta.view.home;

import com.teamlans.lepta.view.home.component.NavigationBar;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

@SpringView(name = HomeView.VIEW_NAME)
public class HomeView extends VerticalLayout
    implements View {

  public static final String VIEW_NAME = "";

  @PostConstruct
  void init() {
    setMargin(true);
    setSpacing(true);
    addComponent(new Label("This is a default view"));
    addComponent(new NavigationBar());
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    //View is constructed in init method
  }
}
