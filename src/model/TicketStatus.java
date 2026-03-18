/*
 * This enum represents the different stages in the lifecycle of a ticket.
 * A ticket is typically created with an OPEN status and then progresses
 * through different states such as IN_PROGRESS or RESOLVED.
 */
package model;

public enum TicketStatus {
        OPEN,
        IN_PROGRESS,
        RESOLVED,
        CLOSED,
}
