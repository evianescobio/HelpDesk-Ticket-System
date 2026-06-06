/*
 * This enum defines the different priority levels that a ticket can have.
 * It is used to represent how urgent a request is, which later helps determine
 * the order in which tickets are processed.
 */

package model;

public enum TicketPriority {
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    CRITICAL(4);

    private final int priority;

    TicketPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
