package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;

import ui.components.*;

public class MainView {
    
    private final BorderPane root;

    private final CreateTicketView createTicketView;
    private final TicketSearchView ticketSearchView;
    private final TicketTableView ticketTableView;
    private final TicketUpdateView ticketUpdateView;

    private final Label messageLabel;

    public MainView() {
        createTicketView = new CreateTicketView();
        ticketSearchView = new TicketSearchView();
        ticketTableView = new TicketTableView();
        ticketUpdateView = new TicketUpdateView();

        messageLabel = new Label();

        // TITLE SECTION //
        Label titleLabel = new Label("Evana Service Desk");

        HBox header = new HBox(titleLabel);
        header.setPadding(new Insets(15));

        // TABLE SECTION //
        Label ticketTableLabel = new Label("Tickets");


        // MAIN CONTENT SECTION //
        VBox mainContent = new VBox(
            10,
            createTicketView.getRoot(),
            messageLabel,
            ticketSearchView.getRoot(),
            ticketTableLabel,
            ticketTableView.getTable(),
            ticketUpdateView.getRoot()
        );
        mainContent.setPadding(new Insets(20));

        VBox.setVgrow(ticketTableView.getTable(), Priority.ALWAYS);

        // BORDER LAYOUT //
        root = new BorderPane();
        root.setTop(header);
        root.setCenter(mainContent);

    }


    // === GETTERS ===
    // Getters for the root element.
    public BorderPane getRoot() {
        return root;
    }

    // Getters for the components.
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

    // Method to set the message label.
    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}
