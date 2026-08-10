/**
 * This class is responsible for printing the menu to the console.
 * It prints the main menu, and the respective submenus for both the client and technician.
 * 
 */

package ui;

public class MenuPrinter {

    public static void printMainMenu() {
        System.out.println("========== MAIN MENU ==========");
        System.out.println("1. Log in as a client.");
        System.out.println("2. Log in as technician.");
        System.out.println("0. Exit.");
    }

    
    public static void printClientMenu() {
        System.out.println("========== CLIENT MENU ==========");
        System.out.println("1. Create a new ticket.");
        System.out.println("2. View my tickets.");
        System.out.println("0. Exit.");
    }


    public static void printTechnicianMenu() {
        System.out.println("========== TECHNICIAN MENU ==========");
        System.out.println("1. View all tickets.");
        System.out.println("2. View pending tickets.");
        System.out.println("3. Search ticket by ID.");
        System.out.println("4. Update ticket status.");
        System.out.println("5. Update ticket priority.");
        System.out.println("6. Resolve ticket.");
        System.out.println("0. Exit.");
    }




    
}
