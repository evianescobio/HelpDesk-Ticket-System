package app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

import model.Ticket;
import repository.InMemoryTicketRepository;
import repository.TicketRepository;
import service.SupportDeskService;

public class EvAnaApp extends Application {

    @Override
    public void start(Stage stage) {
        TicketRepository ticketRepository = new InMemoryTicketRepository();
        SupportDeskService supportDeskService = new SupportDeskService(ticketRepository);

        // Title of the application.
        Label titleLabel = new Label("EvAna Help Desk");
        

        // === TICKET FORM ===

        // Name of the requester. Create a text field for the requester's name.
        Label nameLabel = new Label("Requester Name");
        Label messageLabel = new Label();
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Your Name");

        // Description of the problem. Create a text area for the problem description.
        Label descriptionLabel = new Label("Problem Description");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPrefRowCount(4);
        descriptionArea.setPromptText("Describe the problem you are experiencing");

        // === TICKET LIST ===
        Label ticketsLabel = new Label("Tickets");
        ListView<Ticket> ticketListView = new ListView<>();
        
        // === CREATE TICKET BUTTON ===
        Button createTicketButton = new Button("Create Ticket");
        createTicketButton.setOnAction(event -> {
            
            // Get the values from the form fields.
            String name = nameField.getText().trim();
            String description = descriptionArea.getText().trim();

            if (name.isBlank()) {
                messageLabel.setText("Please enter your name.");
                return;
            }

            if (description.isBlank()) {
                messageLabel.setText("Please enter a problem description.");
            }
            
            Ticket ticket = supportDeskService.createTicket(name, description);
            messageLabel.setText("Ticket # " + ticket.getTicketId() + " created successfully.");
            ticketListView.getItems().setAll(supportDeskService.getAllTickets());
        });

        
        
        // === ROOT LAYOUT ===
        VBox root = new VBox(
            10,
            titleLabel,
            nameLabel,
            nameField,
            descriptionLabel,
            descriptionArea,
            createTicketButton,
            messageLabel,
            ticketsLabel,
            ticketListView
        );
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 600, 400);
        
        stage.setScene(scene);
        stage.setTitle("EvAna Help Desk");
        stage.show();
    
    }

    public static void main(String[] args) {
        launch(args);
    }
}
