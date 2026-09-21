/*
 * This class contains the core logic of the help desk system.
 * It acts as the middle layer between the user interface and the repository,
 * handling all operations related to ticket creation, retrieval, and updates.
 * 
 * The service separates client and technician responsibilities, ensuring that
 * each type of user can only perform the appropriate actions. It also controls
 * how tickets are created and modified, keeping the system behavior consistent.
 */

package service;

import model.Ticket;
import model.TicketStatus;
import model.TicketPriority;
import repository.TicketRepository;
import java.util.Comparator;

import java.util.ArrayList;
import java.util.List;

public class SupportDeskService {

    private final TicketRepository ticketRepository;
    private int nextTicketId = 1;

    // Constructor
    public SupportDeskService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    /* ------------ Client Methods ------------ */

    // Create a new ticket
    public Ticket createTicket(String name, String description) {
        int id = nextTicketId++;

        TicketStatus status = TicketStatus.OPEN;
        TicketPriority priority = TicketPriority.REGULAR;

        Ticket ticket = new Ticket(id, name, description, priority, status);
        ticketRepository.saveTicket(ticket);

        return ticket;
    }

    // Get tickets by client name
    public List<Ticket> getTicketsByClient(String name) {
        List<Ticket> allTickets = ticketRepository.getAllTickets();
        List<Ticket> result = new ArrayList<>();

        for (Ticket ticket : allTickets) {
            if (ticket.getRequesterName().equals(name)) {
                result.add(ticket);
            }
        }
        return result;
    }




    /* ------------ Technician Methods ------------ */

    // Get all tickets.
    public List<Ticket> getAllTickets() {
        return ticketRepository.getAllTickets();
    }

    // Get tickets that are pending for technician.
    public List<Ticket> getPendingTicketsForTechnician() {
        List<Ticket> tickets = ticketRepository.getAllTickets();
        List<Ticket> result = new ArrayList<>();

        for (Ticket ticket : tickets) {
            if (ticket.getTicketStatus() == TicketStatus.OPEN || ticket.getTicketStatus() == TicketStatus.IN_PROGRESS) {
                result.add(ticket);
            }
        }

        result.sort(
            Comparator
                .comparingInt((Ticket ticket) -> ticket.getTicketPriority().getLevel())
                .reversed()
                .thenComparing(Ticket::getCreatedAt)
        );

        return result;
    }

    // Get tickets by ticket ID
    public Ticket getTicketsByTicketId(int ticketId) {
        return ticketRepository.findTicketById(ticketId);
    }

    // Update ticket status.
    public void updateTicketStatus(int ticketId, TicketStatus status) {
        Ticket ticket = ticketRepository.findTicketById(ticketId);

        if (ticket != null) {
            ticket.setStatus(status);
            ticketRepository.updateTicket(ticket);
        }
    }

    // Update ticket priority.
    public void updateTicketPriority(int ticketId, TicketPriority priority) {
        Ticket ticket = ticketRepository.findTicketById(ticketId);

        if (ticket != null) {
            ticket.setPriority(priority);
            ticketRepository.updateTicket(ticket);
        }
    }

    // Resolve ticket.
    public void resolveTicket(int ticketId) {
        Ticket ticket = ticketRepository.findTicketById(ticketId);

        if (ticket != null) {
            ticket.setStatus(TicketStatus.RESOLVED);
            ticketRepository.updateTicket(ticket);
        }
    }
} 