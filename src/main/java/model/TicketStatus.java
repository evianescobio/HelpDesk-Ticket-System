/*
 * This enum represents the different stages in the lifecycle of a ticket.
 * A ticket is typically created with an OPEN status and then progresses
 * through different states such as IN_PROGRESS or RESOLVED.
 */
package model;

public enum TicketStatus {
        OPEN(1),
        IN_PROGRESS(2),
        RESOLVED(3),
        CLOSED(4);

        // Using enum so ticket status has a fixed group of values.
        private final int status;

        TicketStatus(int status) {
                this.status = status;
        }
        
        public int getStatus() {
                return status;
        }
}
