package com.teamlans.lepta.view.bill.overview.component.overlay;

import com.vaadin.ui.Component;

public class OverlayEvent<T extends Component> {

  private T source;
  private String action;

  public OverlayEvent(T source, String action) {
    this.source = source;
    this.action = action;
  }

  public T getSource() {
    return source;
  }

  public String getAction() {
    return action;
  }
}
