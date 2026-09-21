/*
 * This class provides an in-memory implementation of the TicketRepository interface.
 * It uses a HashMap to allow fast lookup of tickets by their IDs.
 * 
 * This approach is efficient for testing and small-scale applications, since all
 * data is stored in memory. 
 */

package repository;

import model.Ticket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTicketRepository implements TicketRepository {

    private final HashMap<Integer, Ticket> ticketMap = new HashMap<>();

    @Override
    public void saveTicket(Ticket ticket) {
        ticketMap.put(ticket.getTicketId(), ticket);
    }

    @Override
    public Ticket findTicketById(int ticketId) {
        return ticketMap.get(ticketId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return new ArrayList<>(ticketMap.values());
    }

    @Override
    public void updateTicket(Ticket ticket) {
        ticketMap.put(ticket.getTicketId(), ticket);
    }

    @Override
    public void deleteTicket(int ticketId) {
        Ticket ticket = ticketMap.get(ticketId);

        if (ticket != null) {
            ticketMap.remove(ticketId);
        }
    }

}
