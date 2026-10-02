package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import repository.InMemoryTicketRepository;
import repository.TicketRepository;

import service.SupportDeskService;

import ui.MainView;
import controller.MainController;

public class EvanaApp extends Application {

    @Override
    public void start(Stage stage) {
        
        // === APPLICATION DEPENCIES ===
        TicketRepository ticketRepository = new InMemoryTicketRepository();
        SupportDeskService supportDeskService = new SupportDeskService(ticketRepository);

        // === UI MAIN VIEW ===
        MainView mainView = new MainView();


        // === MAIN CONTROLLER ===
        new MainController(mainView, supportDeskService);


        // === SCENE ===
        Scene scene = new Scene(mainView.getRoot(), 1000, 650);
        stage.setScene(scene);
        stage.setTitle("Evana Service Desk");
        stage.show();
    
    }

    public static void main(String[] args) {
        launch(args);
    }
}
