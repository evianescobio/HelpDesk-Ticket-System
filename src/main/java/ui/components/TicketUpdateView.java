package ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import model.Ticket;
import model.TicketPriority;
import model.TicketStatus;

public class TicketUpdateView {

    private final HBox root;

    private final ComboBox<TicketPriority> priorityComboBox;
    private final ComboBox<TicketStatus> statusComboBox;
    private final Button updateTicketButton;

    public TicketUpdateView() {

        Label priorityLabel = new Label("Priority");

        priorityComboBox = new ComboBox<>();
        priorityComboBox.getItems().addAll(TicketPriority.values());
        priorityComboBox.setValue(TicketPriority.REGULAR);

        Label statusLabel = new Label("Status");

        statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll(TicketStatus.values());
        statusComboBox.setValue(TicketStatus.OPEN);

        updateTicketButton = new Button("Update Ticket");

        root = new HBox(
            10,
            priorityLabel,
            priorityComboBox,
            statusLabel,
            statusComboBox,
            updateTicketButton
        );
    }

    public HBox getRoot() {
        return root;
    }

    public Button getUpdateTicketButton() {
        return updateTicketButton;
    }

    public TicketPriority getSelectedPriority() {
        return priorityComboBox.getValue();
    }

    public TicketStatus getSelectedStatus() {
        return statusComboBox.getValue();
    }

    public void showTicketValues(Ticket ticket) {

        if (ticket == null) {
            return;
        }

        priorityComboBox.setValue(ticket.getTicketPriority());
        statusComboBox.setValue(ticket.getTicketStatus());
    }
}
