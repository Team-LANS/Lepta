package com.teamlans.lepta.view.bill.overview.component.overlay;

import com.vaadin.ui.Component;

public interface OverlayEventListener<T extends Component> {

  void notify(OverlayEvent<T> event);
}
