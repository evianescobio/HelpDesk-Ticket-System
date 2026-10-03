package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import ui.components.CreateTicketView;


public class ClientView {

    private final VBox root;
    private final CreateTicketView createTicketView;

    public ClientView() {
        
        Label titleLabel = new Label("Ticket Service Desk");
        createTicketView = new CreateTicketView();

        root = new VBox(
            15,
            titleLabel,
            createTicketView.getRoot()
        );

        root.setPadding(new Insets(20));
    }

    public VBox getRoot() {
        return root;
    }

    public CreateTicketView getCreateTicketView() {
        return createTicketView;
    }
}
