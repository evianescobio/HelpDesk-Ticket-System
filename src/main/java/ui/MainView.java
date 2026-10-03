package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.control.Button;

import ui.components.*;

public class MainView {
    
    private final BorderPane root;

    private final ClientView clientView;
    private final TechnicianView technicianView;

    private final Button clientButton;
    private final Button technicianButton;

    private final Label messageLabel;

    public MainView() {

        // For now, the program will have this design: Technician View and Client View.
        clientView = new ClientView();
        technicianView = new TechnicianView();

        messageLabel = new Label();

        // BUTTON ACCESS //
        clientButton = new Button("Client");
        technicianButton = new Button("Technician");

        // TITLE SECTION //
        Label titleLabel = new Label("Evana Service Desk");

        // NAVIGATION SECTION //
        HBox navigation = new HBox(10, clientButton, technicianButton);

        // HEADER SECTION //
        HBox header = new HBox(20, titleLabel, navigation);
        header.setPadding(new Insets(15));


        // BORDER LAYOUT //
        root = new BorderPane();
        root.setTop(header);
        root.setCenter(clientView.getRoot());
        root.setBottom(messageLabel);

        // Main View Layout //
        BorderPane.setMargin(messageLabel, new Insets(10, 20, 10, 20));

    }


    // METHODS TO DISPLAY BTH ACCESS VIEWS //
    public void showClientView() {
        root.setCenter(clientView.getRoot());
    }

    public void showTechnicianView() {
        root.setCenter(technicianView.getRoot());
    }


    // MAIN VIEW GETTERS //
    public ClientView getClientView() {
        return clientView;
    }

    public TechnicianView getTechnicianView() {
        return technicianView;
    }

    public Button getClientButton() {
        return clientButton;
    }

    public Button getTechnicianButton() {
        return technicianButton;
    }

    public BorderPane getRoot() {
        return root;
    }

    // Method to set the message label.
    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}
