package com.teamlans.lepta.view.bill.overview.component.overlay;

import com.vaadin.ui.Component;

public interface Overlay<T extends Component> {

  void addOverlayListener(OverlayEventListener<T> listener);

  void notifyListeners(OverlayEvent<T> event);
}
