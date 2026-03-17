package repository;

import model.Ticket;
import java.util.List;

public interface TicketRepository {

    void saveTicket(Ticket ticket);

    Ticket findTicketById(int ticketId);

    List<Ticket> getAllTickets();

    void updateTicket(Ticket ticket);

    void deleteTicket(int ticketId);

}
