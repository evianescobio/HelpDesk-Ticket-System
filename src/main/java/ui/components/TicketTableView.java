package ui.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Ticket;
import model.TicketStatus;
import model.TicketPriority;

public class TicketTableView {

    private final TableView<Ticket> table;

    public TicketTableView() {
        table = new TableView<>();

        configureTable();
    }

    private void configureTable() {
        // Formatting for the Date Created Column.
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d-MMM-yyyy h:mm a");

        // === TICKET TABLE PROPERTIES ===
        TableColumn<Ticket, Integer> idColumn = new TableColumn<>("ID"); // ID COLUMN.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ticketId"));

        TableColumn<Ticket, String> requesterColumn = new TableColumn<>("Requester"); // REQUESTER COLUMN.
        requesterColumn.setCellValueFactory(new PropertyValueFactory<>("requesterName"));

        TableColumn<Ticket, String> descriptionColumn = new TableColumn<>("Description"); // DESCRIPTION COLUMN.
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("ticketDescription"));

        TableColumn<Ticket, TicketPriority> priorityColumn = new TableColumn<>("Priority"); // PRIORITY COLUMN.
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("ticketPriority"));

        TableColumn<Ticket, TicketStatus> statusColumn = new TableColumn<>("Status"); // STATUS COLUMN.
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("ticketStatus"));

        TableColumn<Ticket, LocalDateTime> dateCreatedColumn = new TableColumn<>("Date Created"); // DATE COLUMN.
        dateCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        // Formatting for the Date Created Column.
        dateCreatedColumn.setCellFactory(column -> new javafx.scene.control.TableCell<>() { 
            @Override
            protected void updateItem(LocalDateTime dateTime, boolean empty) {
                super.updateItem(dateTime, empty);

                if (empty || dateTime == null) {
                    setText(null);
                }
                else {
                    setText(dateTime.format(dateFormatter));
                }
            }
        });

        table.getColumns().addAll(
            idColumn, 
            requesterColumn, 
            descriptionColumn, 
            priorityColumn, 
            statusColumn, 
            dateCreatedColumn
        );

        table.setColumnResizePolicy(
            TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN
        );
    }

    public TableView<Ticket> getTable() {
        return table;
    }

    public void displayTickets(List<Ticket> tickets) {
        table.getItems().setAll(tickets);
    }

    public Ticket getSelectedTicket() {
        return table.getSelectionModel().getSelectedItem();
    }

    public void refresh() {
        table.refresh();
    }

    public void selectTicket(Ticket ticket) {
        table.getSelectionModel().select(ticket);
        table.scrollTo(ticket);
    }
    
}
