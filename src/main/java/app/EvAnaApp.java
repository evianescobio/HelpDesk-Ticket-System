package app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Priority;

import model.Ticket;
import model.TicketPriority;
import model.TicketStatus;
import repository.InMemoryTicketRepository;
import repository.TicketRepository;
import service.SupportDeskService;
import ui.components.TicketTableView;
import ui.components.CreateTicketView;

public class EvAnaApp extends Application {

    @Override
    public void start(Stage stage) {
        
        CreateTicketView createTicketView = new CreateTicketView();
        TicketRepository ticketRepository = new InMemoryTicketRepository();
        SupportDeskService supportDeskService = new SupportDeskService(ticketRepository);

        // Title of the application.
        Label titleLabel = new Label("EvAna Help Desk");

        // === APPLICATION MESSAGES ===
        Label messageLabel = new Label();


        // === TICKET TABLE PROPERTIES ===
        Label ticketTableLabel = new Label("Tickets");
        TicketTableView ticketTableView = new TicketTableView();

        // == TICKET UPDATE PROPERTIES ==
        Label priorityLabel = new Label("Priority");

        ComboBox<TicketPriority> priorityComboBox = new ComboBox<>();
        priorityComboBox.getItems().addAll(TicketPriority.values());
        priorityComboBox.setValue(TicketPriority.REGULAR);

        Label statusLabel = new Label("Status");
        ComboBox<TicketStatus> statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll(TicketStatus.values());
        statusComboBox.setValue(TicketStatus.OPEN);

        // Event-handler to manage the priority and status of the selected tickets.
        Button updateTicketButton = new Button("Update Ticket");
        updateTicketButton.setOnAction(event -> {
            Ticket selectedTicket = ticketTableView.getSelectedTicket();

            if (selectedTicket == null) {
                messageLabel.setText("Please select a ticket");
                return;
            }

            TicketPriority selectedPriority = priorityComboBox.getValue();
            TicketStatus selectedStatus = statusComboBox.getValue();

            supportDeskService.updateTicketPriority(selectedTicket.getTicketId(), selectedPriority);
            supportDeskService.updateTicketStatus(selectedTicket.getTicketId(), selectedStatus);
            
            ticketTableView.refresh();

            messageLabel.setText("Ticket #" + selectedTicket.getTicketId() + " has been successfully updated");
        });


        
        // === CREATE TICKET BUTTON ===
        createTicketView.getCreateTicketButton().setOnAction(event -> {

            String name = createTicketView.getRequesterName();
            String description = createTicketView.getDescription();

            if (name.isBlank()) {
                messageLabel.setText("Please enter your name.");
                return;
            }

            if (description.isBlank()) {
                messageLabel.setText("Please enter a problem description.");
                return;
            }
            
            Ticket ticket = supportDeskService.createTicket(name, description);
            messageLabel.setText("Ticket # " + ticket.getTicketId() + " created successfully.");
            ticketTableView.displayTickets(supportDeskService.getAllTickets());
            
            createTicketView.clearFields();
        });



        // == SEARCH FEATURE ==
        Label searchLabel = new Label("Search Tickets by ID");
        TextField searchField = new TextField();
        searchField.setPromptText("Enter Ticket ID");

        Button searchButton = new Button("Search");

        searchButton.setOnAction(event -> {
            String input = searchField.getText().trim();

            if(input.isBlank()) {
                messageLabel.setText("Please enter a ticket ID.");
                return;
            }

            try {
                int ticketId = Integer.parseInt(input);
                Ticket ticket = supportDeskService.getTicketsByTicketId(ticketId);

                if (ticket == null) {
                    messageLabel.setText("Ticket #" + ticketId + " was not found.");
                    return;
                }

                ticketTableView.selectTicket(ticket);
                messageLabel.setText("Ticket #" + ticketId + " was found.");
            }
            catch (NumberFormatException exception) {
                messageLabel.setText("Please enter a valid ticked ID.");
            }
        });



        // === HORIZONTAL LAYOUT ===
        HBox searchBar = new HBox(
            10,
            searchLabel,
            searchField,
            searchButton
        );
        // Makes the search bar fill the available space.
        HBox.setHgrow(searchField, Priority.ALWAYS);
        searchField.setMaxWidth(Double.MAX_VALUE);

        HBox updateBar = new HBox(
            10,
            priorityLabel,
            priorityComboBox,
            statusLabel,
            statusComboBox,
            updateTicketButton
        );

        HBox header = new HBox(titleLabel);
        header.setPadding(new Insets(15));
        
        
        // === VERTICAL LAYOUT ===
        VBox mainContent = new VBox(
            10,
            createTicketView.getRoot(),
            messageLabel,
            searchBar,
            ticketTableLabel,
            ticketTableView.getTable(),
            updateBar
        );

        // Makes the ticket table fill the available space.
        VBox.setVgrow(ticketTableView.getTable(), Priority.ALWAYS);

        
        
        // === SET PADDING AND SCENE ===
        mainContent.setPadding(new Insets(20));

        // === BORDER LAYOUT ===
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(mainContent);

        // === SCENE ===
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("EvAna Help Desk");
        stage.show();
    
    }

    public static void main(String[] args) {
        launch(args);
    }
}
