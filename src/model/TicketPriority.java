/*
 * This enum defines the different priority levels that a ticket can have.
 * It is used to represent how urgent a request is, which later helps determine
 * the order in which tickets are processed.
 */

package model;

public enum TicketPriority {
    REGULAR(1),
    HIGH(2);

    // Using enum so ticket priority has a fixed group of values.
    private final int level;

    TicketPriority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
