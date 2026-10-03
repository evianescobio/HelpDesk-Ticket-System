package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;

import ui.components.*;

public class TechnicianView {
    
    private final VBox root;

    private final CreateTicketView createTicketView;
    private final TicketSearchView ticketSearchView;
    private final TicketTableView ticketTableView;
    private final TicketUpdateView ticketUpdateView;

    public TechnicianView() {

        Label titleLabel = new Label("Technician Workspace");
        Label ticketsLabel = new Label("Tickets");

        createTicketView = new CreateTicketView();
        ticketSearchView = new TicketSearchView();
        ticketTableView = new TicketTableView();
        ticketUpdateView = new TicketUpdateView();

        root = new VBox(
            15,
            titleLabel,
            ticketSearchView.getRoot(),
            ticketsLabel,
            ticketTableView.getTable(),
            createTicketView.getRoot(),
            ticketUpdateView.getRoot()
        );
        root.setPadding(new Insets(20));

        VBox.setVgrow(ticketTableView.getTable(), Priority.ALWAYS);
    }

    public VBox getRoot() {
        return root;
    }

    public CreateTicketView getCreateTicketView() {
        return createTicketView;
    }

    public TicketSearchView getTicketSearchView() {
        return ticketSearchView;
    }

    public TicketTableView getTicketTableView() {
        return ticketTableView;
    }

    public TicketUpdateView getTicketUpdateView() {
        return ticketUpdateView;
    }

}
