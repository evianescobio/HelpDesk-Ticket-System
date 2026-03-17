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
