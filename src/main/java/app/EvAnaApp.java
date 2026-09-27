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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;

import model.Ticket;
import model.TicketPriority;
import model.TicketStatus;
import repository.InMemoryTicketRepository;
import repository.TicketRepository;
import service.SupportDeskService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EvAnaApp extends Application {

    @Override
    public void start(Stage stage) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d-MMM-yyyy h:mm a");
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


        // === TICKET TABLE PROPERTIES ===
        Label ticketsLabel = new Label("Tickets");
        TableView<Ticket> ticketTable = new TableView<>();

        TableColumn<Ticket, Integer> idColumn = new TableColumn<>("ID"); // ID COLUMN.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ticketId"));

        TableColumn<Ticket, String> requesterColumn = new TableColumn<>("Requester"); // REQUESTER COLUMN.
        requesterColumn.setCellValueFactory(new PropertyValueFactory<>("requesterName"));

        TableColumn<Ticket, String> descriptionColumn = new TableColumn<>("Description"); // DESCRIPTION COLUMN.
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("ticketDescription"));

        TableColumn<Ticket, TicketPriority> priorityColumn = new TableColumn<>("Priority"); // PRIORITY COLUMN.
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("ticketPriority"));

        TableColumn<Ticket, TicketStatus> statusColumn = new TableColumn<>("Status"); // STATUS COLUMN.
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("ticketStatus"));

        TableColumn<Ticket, LocalDateTime> dateCreatedColumn = new TableColumn<>("Date Created"); // DATE COLUMN.
        dateCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        // Formatting for the Date Created Column.
        dateCreatedColumn.setCellFactory(column -> new javafx.scene.control.TableCell<>() { 
            @Override
            protected void updateItem(LocalDateTime dateTime, boolean empty) {
                super.updateItem(dateTime, empty);

                if (empty || dateTime == null) {
                    setText(null);
                }
                else {
                    setText(dateTime.format(dateFormatter));
                }
            }
        });

        // Dropdowns to set up Priority and Status.
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
            Ticket selectedTicket = ticketTable.getSelectionModel().getSelectedItem();

            if (selectedTicket == null) {
                messageLabel.setText("Please select a ticket");
                return;
            }

            TicketPriority selectedPriority = priorityComboBox.getValue();
            TicketStatus selectedStatus = statusComboBox.getValue();

            supportDeskService.updateTicketPriority(selectedTicket.getTicketId(), selectedPriority);
            supportDeskService.updateTicketStatus(selectedTicket.getTicketId(), selectedStatus);

            ticketTable.refresh();

            messageLabel.setText("Ticket #" + selectedTicket.getTicketId() + " has been successfully updated");
        });


        // Adds all the columns to the table.
        ticketTable.getColumns().addAll(
            idColumn,
            requesterColumn,
            descriptionColumn,
            priorityColumn,
            statusColumn,
            dateCreatedColumn
        );

        ticketTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);


        
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
            ticketTable.getItems().setAll(supportDeskService.getAllTickets());
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
            ticketTable,
            priorityLabel,
            priorityComboBox,
            statusLabel,
            statusComboBox,
            updateTicketButton
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
