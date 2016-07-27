package com.teamlans.lepta.view.bill.edit.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class ButtonBarLayout extends HorizontalLayout{

  private Button cancelButton;

  private Button okayButton;

  public ButtonBarLayout(){
    cancelButton = new Button("Cancel");
    addComponent(cancelButton);
    okayButton = new Button("Okay");
    addComponent(okayButton);
    setComponentAlignment(okayButton, Alignment.MIDDLE_RIGHT);
  }

  public void addCancelListener(Button.ClickListener cancelListener){
    cancelButton.addClickListener(cancelListener);
  }

  public void addOkayListener(Button.ClickListener createListener){
    okayButton.addClickListener(createListener);
  }



}
