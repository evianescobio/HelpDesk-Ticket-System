/*
 * This class provides an in-memory implementation of the TicketRepository interface.
 * It uses a HashMap to allow fast lookup of tickets by their ID, and a PriorityQueue
 * to manage tickets based on their priority.
 * 
 * This approach is efficient for testing and small-scale applications, since all
 * data is stored in memory. 
 */

package repository;

import model.Ticket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.List;

public class InMemoryTicketRepository implements TicketRepository {

    private HashMap<Integer, Ticket> ticketMap = new HashMap<>();
    private PriorityQueue<Ticket> priority = new PriorityQueue<>(Comparator.comparing(Ticket::getTicketPriority).reversed());

    public void saveTicket(Ticket ticket) {
        ticketMap.put(ticket.getTicketId(), ticket);
        priority.add(ticket);
    }

    public Ticket findTicketById(int ticketId) {
        return ticketMap.get(ticketId);
    }

    public Ticket getNextTicket() {
        return priority.poll();
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(ticketMap.values());
    }

    public void updateTicket(Ticket ticket) {
        priority.remove(ticketMap.get(ticket.getTicketId()));
        ticketMap.put(ticket.getTicketId(), ticket);
        priority.add(ticket);
    }

    public void deleteTicket(int ticketId) {
        Ticket ticket = ticketMap.get(ticketId);

        if (ticket != null) {
            priority.remove(ticket);
            ticketMap.remove(ticketId);
        }
    }

}
