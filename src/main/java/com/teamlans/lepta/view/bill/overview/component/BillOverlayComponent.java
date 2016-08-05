package com.teamlans.lepta.view.bill.overview.component;

import com.teamlans.lepta.view.bill.overview.component.overlay.Overlay;
import com.teamlans.lepta.view.bill.overview.component.overlay.OverlayEvent;
import com.teamlans.lepta.view.bill.overview.component.overlay.OverlayEventListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

import java.util.ArrayList;
import java.util.List;

public class BillOverlayComponent extends HorizontalLayout implements Overlay<BillComponent> {

  private Button editButton;

  private Button deleteButton;

  private List<OverlayEventListener<BillComponent>> overlayEventListeners;

  public BillOverlayComponent(BillComponent billComponent) {
    overlayEventListeners = new ArrayList<>();
    editButton = new Button("", FontAwesome.EDIT);
    editButton.addClickListener(clickEvent -> notifyListeners(new OverlayEvent<>(billComponent, "EDIT")));
    addComponent(editButton);
    deleteButton = new Button("", FontAwesome.REMOVE);
    deleteButton.addClickListener(clickEvent -> notifyListeners(new OverlayEvent<>(billComponent, "DELETE")));
    addComponent(deleteButton);
  }

  @Override
  public void addOverlayListener(OverlayEventListener<BillComponent> listener) {
    overlayEventListeners.add(listener);
  }

  @Override
  public void notifyListeners(OverlayEvent<BillComponent> event) {
    for (OverlayEventListener<BillComponent> listener : overlayEventListeners) {
      listener.notify(event);
    }
  }


}
