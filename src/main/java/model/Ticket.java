/* 
 * This class represents a single ticket in the system. It contains all the
 * necessary information about a ticket, such as its ID, requester name,
 * description, priority, and status.
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Ticket {
    private final int ticketId;
    private final String requesterName;
    private String ticketDescription;
    private TicketPriority ticketPriority;
    private TicketStatus ticketStatus;
    private final LocalDateTime dateCreated;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d-MMM-yyyy h:mm a");
    
    public Ticket(int ticketId, String requesterName, String ticketDescription, TicketPriority ticketPriority, TicketStatus ticketStatus) {
        this.ticketId = ticketId;
        this.requesterName = requesterName;
        this.ticketDescription = ticketDescription;
        this.ticketPriority = ticketPriority;
        this.ticketStatus = ticketStatus;
        this.dateCreated = LocalDateTime.now();
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public TicketPriority getTicketPriority() {
        return ticketPriority;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setPriority(TicketPriority ticketPriority) {
        this.ticketPriority = ticketPriority;
    }

    public void setStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @Override
    public String toString() {
        return ticketId + ": " + requesterName + " || " + 
        ticketDescription + " || Priority: " + ticketPriority + " || Status: " + ticketStatus + " || Date Created: " + dateCreated.format(DATE_FORMATTER);
    }
    
}
