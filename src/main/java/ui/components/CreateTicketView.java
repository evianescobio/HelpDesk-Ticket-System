package ui.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class CreateTicketView {

    private final VBox root;

    private final TextField nameField;
    private final TextArea descriptionArea;
    private final Button createTicketButton;

    public CreateTicketView() {
        
        Label nameLabel = new Label("Requester Name");
        nameField = new TextField();
        nameField.setPromptText("Enter your name");

        Label descriptionLabel = new Label("Description");
        descriptionArea = new TextArea();
        descriptionArea.setPrefRowCount(3);
        descriptionArea.setPromptText("Describe the problem you are experiencing");

        createTicketButton = new Button("Create Ticket");
        

        root = new VBox(
            10,
            nameLabel,
            nameField,
            descriptionLabel,
            descriptionArea,
            createTicketButton
        );

        root.setPadding(new Insets(10));

    }

    public VBox getRoot() {
        return root;
    }

    public Button getCreateTicketButton() {
        return createTicketButton;
    }

    public String getRequesterName() {
        return nameField.getText().trim();
    }

    public String getDescription() {
        return descriptionArea.getText().trim();
    }

    public void clearFields() {
        nameField.clear();
        descriptionArea.clear();
    }
}