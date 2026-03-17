/* This class represents a Ticket object. It creates its parameters 
in the constructor and has getters and setters for each of its parameters. */

package model;

public class Ticket {
    private int ticketId;
    private String requesterName;
    private String ticketDescription;
    private TicketPriority ticketPriority;
    private TicketStatus ticketStatus;
    
    public Ticket(int ticketId, String requesterName, String ticketDescription, TicketPriority ticketPriority, TicketStatus ticketStatus) {
        this.ticketId = ticketId;
        this.requesterName = requesterName;
        this.ticketDescription = ticketDescription;
        this.ticketPriority = ticketPriority;
        this.ticketStatus = ticketStatus;
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

    public void setPriority(TicketPriority ticketPriority) {
        this.ticketPriority = ticketPriority;
    }

    public void setStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @Override
    public String toString() {
        return ticketId + ": " + requesterName + " || " + ticketDescription + " || Priority: " + ticketPriority + " || Status: " + ticketStatus;
    }
    
}
