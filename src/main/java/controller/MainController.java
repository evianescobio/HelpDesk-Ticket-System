
package controller;

import model.Ticket;
import model.TicketPriority;
import model.TicketStatus;
import service.SupportDeskService;
import ui.*;
import ui.components.*;


public class MainController {

    private final MainView mainView;
    private final SupportDeskService supportDeskService;

    public MainController(MainView mainView, SupportDeskService supportDeskService) {
        this.mainView = mainView;
        this.supportDeskService = supportDeskService;

        configureNavigationHandler();
        configureCreateTicketView();
        configureTicketSearchView();
        configureTicketUpdateView();
        configureTicketTableView();
    }



    // === CONFIGURE NAVIGATION METHODS ===
    private void configureNavigationHandler() {
        mainView.getClientButton().setOnAction(event -> mainView.showClientView());
        mainView.getTechnicianButton().setOnAction(event -> {
            TicketTableView ticketTableView = mainView.getTechnicianView().getTicketTableView();
            ticketTableView.displayTickets(supportDeskService.getAllTickets());

            mainView.showTechnicianView();
        });
    }


    // === CONFIGURE CREATE TICKET VIEW ===
    private void configureCreateTicketView() {
        mainView.getClientView().getCreateTicketView().getCreateTicketButton().setOnAction(event -> {

            String name = mainView.getClientView().getCreateTicketView().getRequesterName();
            String description = mainView.getClientView().getCreateTicketView().getDescription();

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
            mainView.getTechnicianView().getTicketTableView().displayTickets(supportDeskService.getAllTickets());

            mainView.getClientView().getCreateTicketView().clearFields(); // After creating a ticket, the form is cleared.
        });
    }



    // === CONFIGURE TICKET SEARCH VIEW ===
    private void configureTicketSearchView() {
        mainView.getTechnicianView().getTicketSearchView().getSearchButton().setOnAction(event -> {
            String input = mainView.getTechnicianView().getTicketSearchView().getSearchInput();

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
                mainView.getTechnicianView().getTicketTableView().selectTicket(ticket);
                mainView.setMessage("Ticket #" + ticketId + " was found.");
            }
            catch (NumberFormatException exception) {
                mainView.setMessage("Please enter a valid ticket ID.");
            }
        });
    }



    // === CONFIGURE TICKET UPDATE VIEW ===
    private void configureTicketUpdateView() {
        mainView.getTechnicianView().getTicketUpdateView().getUpdateTicketButton().setOnAction(event -> {
            Ticket selectedTicket = mainView.getTechnicianView().getTicketTableView().getSelectedTicket();

            if (selectedTicket == null) {
                mainView.setMessage("Please select a ticket");
                return;
            }

            // Update the selected ticket's priority and status.
            TicketPriority selectedPriority = mainView.getTechnicianView().getTicketUpdateView().getSelectedPriority();
            TicketStatus selectedStatus = mainView.getTechnicianView().getTicketUpdateView().getSelectedStatus();

            // Update the ticket in the repository.
            supportDeskService.updateTicketPriority(selectedTicket.getTicketId(), selectedPriority);
            supportDeskService.updateTicketStatus(selectedTicket.getTicketId(), selectedStatus);
            
            mainView.getTechnicianView().getTicketTableView().refresh();

            mainView.setMessage("Ticket #" + selectedTicket.getTicketId() + " has been successfully updated");
        });
    }



    // === CONFIGURE TICKET TABLE VIEW ===
    private void configureTicketTableView() {
         mainView.getTechnicianView().getTicketTableView().getTable()
            .getSelectionModel()
            .selectedItemProperty()
            .addListener((observable, oldTicket, newTicket) -> {
                if (newTicket != null) {
                    mainView.getTechnicianView().getTicketUpdateView().showTicketValues(newTicket);
                }
            });
    }


    
}
