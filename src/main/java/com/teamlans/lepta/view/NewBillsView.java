package com.teamlans.lepta.view;

import com.teamlans.lepta.view.bills.create.ManualCreateBillViewImpl;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

@SpringView(name = NewBillsView.VIEW_NAME) public class NewBillsView extends VerticalLayout
    implements View {

  public static final String VIEW_NAME = "NEW_BILLS";

  @PostConstruct void init() {
    setMargin(false);
    setSpacing(true);
    VerticalLayout layout = buildRootLayout();
    addComponent(layout);
  }

  private VerticalLayout buildRootLayout() {
    VerticalLayout layout = new VerticalLayout();
    layout.addComponent(buildAddBillButton());
    return layout;
  }

  private Button buildAddBillButton() {
    Button addButton = new Button("Add");
    addButton.addClickListener(
        event -> getUI().getNavigator().navigateTo(ManualCreateBillViewImpl.VIEW_NAME));
    return addButton;
  }

  @Override public void enter(ViewChangeListener.ViewChangeEvent event) {
    // the view is constructed in the init() method()
  }
}
