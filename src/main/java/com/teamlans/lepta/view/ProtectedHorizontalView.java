package com.teamlans.lepta.view;

import com.teamlans.lepta.LeptaUi;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

/**
 * Should be used as a basis for all password protected views that extend HorizontalLayout.
 */
public class ProtectedHorizontalView extends HorizontalLayout implements View {

  public LeptaUi getLeptaUi() {
    return (LeptaUi) UI.getCurrent();
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // needed for view implementation
  }
}
