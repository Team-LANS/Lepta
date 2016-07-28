package com.teamlans.lepta.view;

import com.teamlans.lepta.LeptaUi;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Should be used as a basis for all password protected views that extend VerticalLayout.
 */
public class ProtectedVerticalView extends VerticalLayout implements View {

  public LeptaUi getLeptaUi() {
    return ((LeptaUi)UI.getCurrent());
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // needed for view implementation
  }
}
