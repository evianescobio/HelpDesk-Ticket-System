package app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Ticket;
import model.TicketPriority;
import model.TicketStatus;

import repository.InMemoryTicketRepository;
import repository.TicketRepository;

import service.SupportDeskService;

import ui.components.CreateTicketView;
import ui.components.TicketSearchView;
import ui.components.TicketTableView;
import ui.components.TicketUpdateView;

public class EvAnaApp extends Application {

    @Override
    public void start(Stage stage) {
        
        // === APPLICATION DEPENCIES ===
        TicketRepository ticketRepository = new InMemoryTicketRepository();
        SupportDeskService supportDeskService = new SupportDeskService(ticketRepository);

        // === UI COMPONENTS ===
        CreateTicketView createTicketView = new CreateTicketView();
        TicketSearchView ticketSearchView = new TicketSearchView();
        TicketTableView ticketTableView = new TicketTableView();
        TicketUpdateView ticketUpdateView = new TicketUpdateView();



        // === UI ELEMENTS ===
        Label titleLabel = new Label("EvAna Help Desk");
        Label messageLabel = new Label();
        Label ticketTableLabel = new Label("Tickets");


        // ============================================================================
        // CREATE TICKET
        // ============================================================================
        
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
            messageLabel.setText("Ticket #" + ticket.getTicketId() + " created successfully.");
            ticketTableView.displayTickets(supportDeskService.getAllTickets());

            createTicketView.clearFields(); // After creating a ticket, the form is cleared.

        });


        // ============================================================================
        // SEARCH TICKET
        // ============================================================================

        ticketSearchView.getSearchButton().setOnAction(event -> {
            String input = ticketSearchView.getSearchInput();

            if (input.isBlank()) {
                messageLabel.setText("Please enter a ticket ID.");
                return;
            }

            // Try to parse the input as an integer. If successful, retrieve the ticket with the given ID.
            try {

                int ticketId = Integer.parseInt(input);
                Ticket ticket = supportDeskService.getTicketsByTicketId(ticketId);

                if (ticket == null) {
                    messageLabel.setText("Ticket #" + ticketId + " not found.");
                    return;
                }

                // Select the found ticket in the table view.
                ticketTableView.selectTicket(ticket);
                messageLabel.setText("Ticket #" + ticketId + " was found.");
            }
            catch (NumberFormatException exception) {
                messageLabel.setText("Please enter a valid ticket ID.");
            }
        });



        // ============================================================================
        // UPDATE TICKET
        // ============================================================================
        
        ticketUpdateView.getUpdateTicketButton().setOnAction(event -> {
            Ticket selectedTicket = ticketTableView.getSelectedTicket();

            if (selectedTicket == null) {
                messageLabel.setText("Please select a ticket");
                return;
            }

            // Update the selected ticket's priority and status.
            TicketPriority selectedPriority = ticketUpdateView.getSelectedPriority();
            TicketStatus selectedStatus = ticketUpdateView.getSelectedStatus();

            // Update the ticket in the repository.
            supportDeskService.updateTicketPriority(selectedTicket.getTicketId(), selectedPriority);
            supportDeskService.updateTicketStatus(selectedTicket.getTicketId(), selectedStatus);
            
            ticketTableView.refresh();

            messageLabel.setText("Ticket #" + selectedTicket.getTicketId() + " has been successfully updated");
        });


        // ============================================================================
        // TABLE SELECTION
        // ============================================================================
        
        ticketTableView.getTable()
            .getSelectionModel()
            .selectedItemProperty()
            .addListener((observable, oldTicket, newTicket) -> {
                if (newTicket != null) {
                    ticketUpdateView.showTicketValues(newTicket);
                }
            });

        // === HORIZONTAL LAYOUT ===  
        HBox header = new HBox(titleLabel);
        header.setPadding(new Insets(15));
        
        
        // === VERTICAL LAYOUT ===
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

        // Makes the ticket table fill the available space.
        VBox.setVgrow(ticketTableView.getTable(), Priority.ALWAYS);
        

        // === BORDER LAYOUT ===
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(mainContent);

        // === SCENE ===
        Scene scene = new Scene(root, 1000, 650);
        stage.setScene(scene);
        stage.setTitle("EvAna Help Desk");
        stage.show();
    
    }

    public static void main(String[] args) {
        launch(args);
    }
}
