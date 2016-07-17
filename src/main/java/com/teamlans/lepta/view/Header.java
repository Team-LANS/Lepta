package com.teamlans.lepta.view;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Hans-Joerg Schroedl
 */
class Header extends HorizontalLayout {

  private static final String TITLE_ID = "lepta-title";

  private static final String EDIT_ID = "header-edit";

  Header() {
    Label titleLabel = buildTitleLabel();
    this.addComponent(titleLabel);
    this.addStyleName("viewheader");
    this.setSpacing(true);

    Component edit = buildEditButton();
    HorizontalLayout tools = new HorizontalLayout(edit);
    tools.setSpacing(true);
    tools.addStyleName("toolbar");
    this.addComponent(tools);

  }

  private Label buildTitleLabel() {
    Label titleLabel = new Label("Lepta");
    titleLabel.setId(TITLE_ID);
    titleLabel.setSizeUndefined();
    titleLabel.addStyleName(ValoTheme.LABEL_H1);
    titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
    return titleLabel;
  }

  private Component buildEditButton() {
    Button result = new Button();
    result.setId(EDIT_ID);
    result.setIcon(FontAwesome.EDIT);
    result.addStyleName("icon-edit");
    result.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
    result.setDescription("Edit Dashboard");
    result.addClickListener((Button.ClickListener) event -> System.out.println("Blabla"));
    return result;
  }



}

