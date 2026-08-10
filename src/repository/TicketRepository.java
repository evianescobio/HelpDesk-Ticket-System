/*
 * This interface defines the basic operations for managing tickets in the system.
 * It acts as a contract that any ticket storage implementation must follow,
 * regardless of how the data is actually stored (in memory, database, etc.).
 * 
 * By using this interface, the service layer remains independent from the
 * underlying storage mechanism, making the system easier to extend and maintain.
 */
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
