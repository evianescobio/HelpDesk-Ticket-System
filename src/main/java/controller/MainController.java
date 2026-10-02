package controller;


import javafx.scene.Scene;
import model.Ticket;
import model.TicketPriority;
import model.TicketStatus;
import service.SupportDeskService;
import ui.MainView;
import ui.components.CreateTicketView;
import ui.components.TicketSearchView;
import ui.components.TicketTableView;
import ui.components.TicketUpdateView;


public class MainController {

    private final MainView mainView;
    private final SupportDeskService supportDeskService;

    public MainController(MainView mainView, SupportDeskService supportDeskService) {
        this.mainView = mainView;
        this.supportDeskService = supportDeskService;

        configureCreateTicketView();
        configureTicketSearchView();
        configureTicketUpdateView();
        configureTicketTableView();
    }


    // === CONFIGURE CREATE TICKET VIEW ===
    private void configureCreateTicketView() {
        mainView.getCreateTicketView().getCreateTicketButton().setOnAction(event -> {

            String name = mainView.getCreateTicketView().getRequesterName();
            String description = mainView.getCreateTicketView().getDescription();

            if (name.isBlank()) {
                mainView.setMessage("Please enter your name.");
                return;
            }
            if (description.isBlank()) {
                mainView.setMessage("Please enter a problem description.");
                return;
            }

            Ticket ticket = supportDeskService.createTicket(name, description);
            mainView.setMessage("Ticket #" + ticket.getTicketId() + " created successfully.");
            mainView.getTicketTableView().displayTickets(supportDeskService.getAllTickets());

            mainView.getCreateTicketView().clearFields(); // After creating a ticket, the form is cleared.
        });
    }



    // === CONFIGURE TICKET SEARCH VIEW ===
    private void configureTicketSearchView() {
         mainView.getTicketSearchView().getSearchButton().setOnAction(event -> {
            String input = mainView.getTicketSearchView().getSearchInput();

            if (input.isBlank()) {
                mainView.setMessage("Please enter a ticket ID.");
                return;
            }

            // Try to parse the input as an integer. If successful, retrieve the ticket with the given ID.
            try {

                int ticketId = Integer.parseInt(input);
                Ticket ticket = supportDeskService.getTicketsByTicketId(ticketId);

                if (ticket == null) {
                    mainView.setMessage("Ticket #" + ticketId + " not found.");
                    return;
                }

                // Select the found ticket in the table view.
                mainView.getTicketTableView().selectTicket(ticket);
                mainView.setMessage("Ticket #" + ticketId + " was found.");
            }
            catch (NumberFormatException exception) {
                mainView.setMessage("Please enter a valid ticket ID.");
            }
        });
    }



    // === CONFIGURE TICKET UPDATE VIEW ===
    private void configureTicketUpdateView() {
        mainView.getTicketUpdateView().getUpdateTicketButton().setOnAction(event -> {
            Ticket selectedTicket = mainView.getTicketTableView().getSelectedTicket();

            if (selectedTicket == null) {
                mainView.setMessage("Please select a ticket");
                return;
            }

            // Update the selected ticket's priority and status.
            TicketPriority selectedPriority = mainView.getTicketUpdateView().getSelectedPriority();
            TicketStatus selectedStatus = mainView.getTicketUpdateView().getSelectedStatus();

            // Update the ticket in the repository.
            supportDeskService.updateTicketPriority(selectedTicket.getTicketId(), selectedPriority);
            supportDeskService.updateTicketStatus(selectedTicket.getTicketId(), selectedStatus);
            
            mainView.getTicketTableView().refresh();

            mainView.setMessage("Ticket #" + selectedTicket.getTicketId() + " has been successfully updated");
        });
    }



    // === CONFIGURE TICKET TABLE VIEW ===
    private void configureTicketTableView() {
         mainView.getTicketTableView().getTable()
            .getSelectionModel()
            .selectedItemProperty()
            .addListener((observable, oldTicket, newTicket) -> {
                if (newTicket != null) {
                    mainView.getTicketUpdateView().showTicketValues(newTicket);
                }
            });
    }


    
}
