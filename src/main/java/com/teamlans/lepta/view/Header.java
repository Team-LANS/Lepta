package com.teamlans.lepta.view;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Hans-Joerg Schroedl
 */
public class Header extends HorizontalLayout {

  public Header() {
    this.setSpacing(true);
    this.addStyleName("viewheader");  // Application specific style.


    Label titleLabel = new Label("Dashboard");
    titleLabel.setId("ID");
    titleLabel.setSizeUndefined();
    titleLabel.addStyleName(ValoTheme.LABEL_H1);
    titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
    this.addComponent(titleLabel);

    Label otherLabel = new Label("Here be user account");
    otherLabel.setId("USER_ACCOUNT");
    otherLabel.setSizeUndefined();
    this.addComponent(otherLabel);



  }
}

