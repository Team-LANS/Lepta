package com.teamlans.lepta.view;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;

/**
 * @author Hans-Joerg Schroedl
 */
@DesignRoot public class MainView extends VerticalLayout {
  HorizontalLayout header;
  Label testLabel;

  public MainView() {
    Design.read(getClass().getResourceAsStream("main.html"), this);
    header.addComponent(new Header());
    // Show some (example) data
    testLabel.setValue("Babalelab");
  }
}
