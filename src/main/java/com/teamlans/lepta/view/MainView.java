package com.teamlans.lepta.view;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.themes.ValoTheme;
import javafx.scene.layout.Pane;

/**
 * @author Hans-Joerg Schroedl
 */
public class MainView extends Panel{

  private final VerticalLayout root;

  public MainView() {
    setSizeFull();

    root = new VerticalLayout();
    root.setSizeFull();
    root.setMargin(true);
    setContent(root);
    Responsive.makeResponsive(root);

    root.addComponent(buildHeader());
  }

  private Component buildHeader() {
    HorizontalLayout header = new Header();
    header.addStyleName("viewheader");
    header.setSpacing(true);
    return header;
  }



}
