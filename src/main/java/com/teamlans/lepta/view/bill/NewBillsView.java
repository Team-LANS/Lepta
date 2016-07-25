package com.teamlans.lepta.view.bill;

import com.teamlans.lepta.entities.Bill;
import com.teamlans.lepta.entities.Item;
import com.teamlans.lepta.service.bill.BillService;
import com.teamlans.lepta.view.bill.create.ManualCreateBillView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import javax.annotation.PostConstruct;

@SpringView(name = NewBillsView.VIEW_NAME)
public class NewBillsView extends VerticalLayout
        implements View {

    public static final String VIEW_NAME = "Bills";

    @Autowired
    private BillService billService;

    @PostConstruct
    void init() {
        setMargin(false);
        setSpacing(true);
        addComponent(buildBillsTable());
        addComponent(buildAddBillButton());
    }

    private Table buildBillsTable() {
        Table table = new Table();
        table.addContainerProperty("Name", String.class, null);
        table.addContainerProperty("Total Price", Double.class, null);
        List<Bill> bills = billService.listBills();
        for (Bill bill : bills) {
            // Found on stackoverflow: http://stackoverflow.com/questions/23110853/java8-sum-values-from-specific-field-of-the-objects-in-a-list
            Double totalPrice = bill.getItems().stream().mapToDouble(Item::getPrice).sum();
            table.addItem(new Object[]{bill.getName(), totalPrice}, table.getId());
        }
        return table;
    }


    private Button buildAddBillButton() {
        Button addButton = new Button("Add");
        addButton.addClickListener(
                event -> getUI().getNavigator().navigateTo(ManualCreateBillView.VIEW_NAME));
        return addButton;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}
