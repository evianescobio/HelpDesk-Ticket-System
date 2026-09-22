package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class EvAnaApp extends Application {

    @Override
    public void start(Stage stage) {

        Label titleLabel = new Label("EvAna");
        
        StackPane root = new StackPane(titleLabel);
        
        Scene scene = new Scene(root, 600, 400);
        
        stage.setScene(scene);
        stage.setTitle("EvAna Application");
        stage.show();
    
    }

    public static void main(String[] args) {
        launch(args);
    }
}
