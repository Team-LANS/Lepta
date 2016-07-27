package com.teamlans.lepta.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;

/**
 * Should be used as a basis for all password protected views that extend HorizontalLayout.
 */
public class ProtectedHorizontalView extends HorizontalLayout implements View {
  @Override
  public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    // needed for view implementation
  }
}
