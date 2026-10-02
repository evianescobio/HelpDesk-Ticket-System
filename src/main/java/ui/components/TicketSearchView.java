package ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


public class TicketSearchView {

    private final HBox root;
    private final TextField searchField;
    private final Button searchButton;

    public TicketSearchView() {
        Label searchLabel = new Label("Search Tickets by ID");
        searchField = new TextField();
        searchField.setPromptText("Enter Ticket ID");

        searchButton = new Button("Search");

        root = new HBox(
            10, 
            searchLabel,
            searchField,
            searchButton
        );

        HBox.setHgrow(searchField, Priority.ALWAYS);
        searchField.setMaxWidth(Double.MAX_VALUE);
    }
    
    public HBox getRoot() {
        return root;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public String getSearchInput() {
        return searchField.getText().trim();
    }

    public void clearSearch() {
        searchField.clear();
    }
}
