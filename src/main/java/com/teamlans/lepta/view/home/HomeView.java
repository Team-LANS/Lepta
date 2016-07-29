package com.teamlans.lepta.view.home;

import com.teamlans.lepta.view.ProtectedVerticalView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


@SpringView(name = HomeView.VIEW_NAME)
public class HomeView extends ProtectedVerticalView {

  public static final String VIEW_NAME = "";

  public HomeView() {
    setSizeFull();
    setSpacing(true);
    init();
  }

  private void init() {
    addComponent(buildHeader());

    HorizontalLayout layout = new HorizontalLayout();
    layout.setSizeFull();
    addGeneralInfo(layout);
    addComponent(layout);
  }

  private Component buildHeader() {
    final HorizontalLayout header = new HorizontalLayout();
    header.setSizeFull();

    final Label title = new Label("Home");
    header.addComponent(title);
    header.setComponentAlignment(title, Alignment.TOP_LEFT);

    final HorizontalLayout container = new HorizontalLayout();
    addHeaderButtons(container);
    header.addComponent(container);
    header.setComponentAlignment(container, Alignment.TOP_RIGHT);

    return header;
  }

  private void addHeaderButtons(HorizontalLayout container) {
    final Button addNew = new Button("Add new");
    container.addComponent(addNew);

    final Button importNew = new Button("Import");
    container.addComponent(importNew);
  }

  private void addGeneralInfo(HorizontalLayout layout) {
    VerticalLayout container = new VerticalLayout();
    container.setSizeFull();
    container.setWidth("33%");

    layout.addComponent(container);
  }

  private void addNewInfo(HorizontalLayout layout) {

  }

  private void addAssignedInfo(HorizontalLayout layout) {
    
  }


  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    //View is constructed in init method
  }
}
