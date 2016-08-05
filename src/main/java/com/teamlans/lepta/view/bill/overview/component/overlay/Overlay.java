package com.teamlans.lepta.view.bill.overview.component.overlay;

public interface Overlay {

  void addOverlayListener(OverlayEventListener listener);

  void notifyListeners(OverlayEvent event);
}
