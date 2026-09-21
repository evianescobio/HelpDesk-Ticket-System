/* 
 * This class represents a single ticket in the system. It contains all the
 * necessary information about a ticket, such as its ID, requester name,
 * description, priority, and status.
 */
package model;

import java.time.LocalDateTime;


public class Ticket {
    private final int ticketId;
    private final String requesterName;
    private String ticketDescription;
    private TicketPriority ticketPriority;
    private TicketStatus ticketStatus;
    private final LocalDateTime createdAt;
    
    public Ticket(int ticketId, String requesterName, String ticketDescription, TicketPriority ticketPriority, TicketStatus ticketStatus) {
        this.ticketId = ticketId;
        this.requesterName = requesterName;
        this.ticketDescription = ticketDescription;
        this.ticketPriority = ticketPriority;
        this.ticketStatus = ticketStatus;
        this.createdAt = LocalDateTime.now();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
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
        ticketDescription + " || Priority: " + ticketPriority + " || Status: " + ticketStatus + " || Created At: " + createdAt;
    }
    
}
