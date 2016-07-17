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
@SpringView(name = ScopedView.VIEW_NAME) public class ScopedView extends VerticalLayout implements View {

  public static final String VIEW_NAME = "SCOPED_VIEW";

  @PostConstruct void init() {
    setMargin(true);
    setSpacing(true);
    addComponent(new Label("This is a view scoped view"));
  }

  @Override public void enter(ViewChangeListener.ViewChangeEvent event) {
    // the view is constructed in the init() method()
  }
}
