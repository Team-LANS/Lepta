package com.teamlans.lepta.view.bill.overview.component;

import com.teamlans.lepta.view.bill.overview.component.overlay.Overlay;
import com.teamlans.lepta.view.bill.overview.component.overlay.OverlayAction;
import com.teamlans.lepta.view.bill.overview.component.overlay.OverlayEvent;
import com.teamlans.lepta.view.bill.overview.component.overlay.OverlayEventListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

import java.util.ArrayList;
import java.util.List;

public class BillOverlayComponent extends HorizontalLayout implements Overlay {

  private Button editButton;

  private Button deleteButton;

  private List<OverlayEventListener> overlayEventListeners;

  public BillOverlayComponent() {
    overlayEventListeners = new ArrayList<>();
    editButton = new Button();
    editButton.addClickListener(clickEvent -> notifyListeners(new OverlayEvent(OverlayAction.EDIT)));
    addComponent(editButton);
    deleteButton = new Button();
    deleteButton.addClickListener(clickEvent -> notifyListeners(new OverlayEvent(OverlayAction.DELETE)));
    addComponent(deleteButton);
  }

  @Override
  public void addOverlayListener(OverlayEventListener listener) {
    overlayEventListeners.add(listener);
  }

  @Override
  public void notifyListeners(OverlayEvent event) {
    for (OverlayEventListener listener : overlayEventListeners) {
      listener.notify(event);
    }
  }


}
