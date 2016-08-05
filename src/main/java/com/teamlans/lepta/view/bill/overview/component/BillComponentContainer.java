package com.teamlans.lepta.view.bill.overview.component;

import com.vaadin.ui.HorizontalLayout;

public class BillComponentContainer extends HorizontalLayout implements BillChangeListener {


  @Override
  public void notify(BillChangeEvent event) {
    if(event.getAction() == BillChangeAction.DELETE){
      removeComponent(event.getBillComponent());
    }
  }
}
