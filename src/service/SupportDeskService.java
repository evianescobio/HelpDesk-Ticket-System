/* This class is responsible for handling all the client requests. There are two types of access: Client access and Technician access.
    They will handle different requests. Clients can only create tickets, check ticket status, and get tickets by client name. 
    Technicians can get  all tickets, get next ticket, update ticket status, update ticket priority, and resolve ticket.
*/

package service;

import model.Ticket;
import model.TicketStatus;
import model.TicketPriority;
import repository.InMemoryTicketRepository;
import repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

public class SupportDeskService {

    private TicketRepository ticketRepository;
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
        TicketPriority priority = TicketPriority.MEDIUM;

        Ticket ticket = new Ticket(id, name, description, priority, status);
        ticketRepository.saveTicket(ticket);

        return ticket;
    }

    // Get ticket status
    public TicketStatus checkTicketStatus(int ticketId) {
        Ticket ticket = ticketRepository.findTicketById(ticketId);
        if (ticket != null) {
            return ticket.getTicketStatus();
        }
        return null;
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

    // Get next ticket (from priority queue).
    public Ticket getNextTicket() {
        if (ticketRepository.getAllTickets().isEmpty()) {
            return null;
        }
        return ticketRepository.getNextTicket();
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