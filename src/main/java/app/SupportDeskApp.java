/**
 * And ladies and gentlemen
 * Here is where all the mess comes together, and starts to work.
 * This is the main class of the application.
 * It will bring all the other classes together and start the application.
 */

package app;

import repository.TicketRepository;
import repository.InMemoryTicketRepository;
import service.SupportDeskService;
import ui.MenuPrinter;
import utils.InputValidator;

import java.util.Scanner;



public class SupportDeskApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TicketRepository ticketRepository = new InMemoryTicketRepository();
        SupportDeskService supportDeskService = new SupportDeskService(ticketRepository);
        

        boolean running = true;

        while (running) {
            MenuPrinter.printMainMenu();
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();

            if (!InputValidator.isInteger(input)) {
                System.out.print("Invalid input. Please enter a number: ");
                continue;
            }

            int choice = Integer.parseInt(input.trim());

            if (!InputValidator.isWithinRange(choice, 0, 2)) {
                System.out.println("Invalid option. Please choose 0, 1, or 2.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleClientMenu(scanner, supportDeskService);
                    break;

                case 2:
                    System.out.println("Technician menu coming soon.");
                    break;

                case 0:
                    running = false;
                    System.out.println("Exiting application.");
                    break;
            }
        }

        scanner.close();
    }


    private static void handleClientMenu(Scanner scanner, SupportDeskService supportDeskService) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        if (!InputValidator.isNotBlank(name)) {
            System.out.println("Invalid input. Please enter a name.");
            return;
        }

        boolean inClientMenu = true;

        while (inClientMenu) {
            MenuPrinter.printClientMenu();
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();

            if (!InputValidator.isInteger(input)) {
                System.out.print("Invalid input. Please enter a number: ");
                continue;
            }

            int choice = Integer.parseInt(input.trim());

            if (!InputValidator.isWithinRange(choice, 0, 2)) {
                System.out.println("Invalid option. Please choose 0, 1, or 2.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter a description of the issue: ");
                    String description = scanner.nextLine().trim();
                    if (!InputValidator.isNotBlank(description)) {
                        System.out.println("Invalid input. Please enter a description.");
                        continue;
                    }

                    System.out.println("Creating a new ticket....");
                    supportDeskService.createTicket(name, description);
                    break;
                
                case 2:
                    var tickets = supportDeskService.getTicketsByClient(name);
                    if (tickets.isEmpty()) {
                        System.out.println("No currently tickets available.");
                        break;
                    }

                    for (var ticket : tickets) {
                        System.out.println(ticket.toString());
                    }
                    break;
                
                case 0:
                    inClientMenu = false;
                    System.out.println("Exiting application.");
                    break;
            }
        }
    }

}
