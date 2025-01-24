/*Dayton Hannaford,
CEN-3024C-24204
This class is designed as the visual component of the Library Management System. It handles the menu functionality that's displayed in the console
for the user to interact with.*/

import java.util.Scanner;

public class ConsoleInterface {
    private LibraryManagementSystemBrain libraryManagementSystemBrain;
    private Scanner scanner;
// Used for different colors. Helps break up what's going on.
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";

    public ConsoleInterface() {
        libraryManagementSystemBrain = new LibraryManagementSystemBrain();
        scanner = new Scanner(System.in);
    }


    void loadPatronsFile() {
        System.out.println(CYAN + "\n** Welcome to the Library Management System! **" + RESET);
        System.out.println(YELLOW + "** Please enter file location to load Patrons from a file or enter 'no' to skip this process and head to the main menu **" + RESET);

        String choice = scanner.nextLine().toLowerCase();

        if (choice.equals("no")) {
            System.out.println(YELLOW + "\nFile importing skipped." + RESET);
        } else {
            if (libraryManagementSystemBrain.importPatron(choice)) {
                System.out.println(GREEN + "\nSUCCESS! File imported successfully." + RESET);
                libraryManagementSystemBrain.listPatrons();
            } else {
                System.out.println(RED + "\nERROR! Failed to import file. Please check the file and try again." + RESET);
            }
        }
    }



    // Main Menu logic, cycles through options depending on which choice is used via input.
    public void mainMenu() {
        boolean quit = false;

        while (!quit) {
            System.out.println(CYAN + "\n** Library Management System **" + RESET);
            System.out.println("\n** Please select an option below **");
            System.out.println("Option 1: Add New Patron");
            System.out.println("Option 2: Remove Patron");
            System.out.println("Option 3: List All Patrons");
            System.out.println("Option 4: Import Patrons from File");
            System.out.println("Option 5: Exit Program");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n");
                System.out.println(RED + "ERROR! Please select a valid option." + RESET);
                continue;
            }

            switch (choice) {
                case 1:
                    addPatronUI();
                    break;
                case 2:
                    removePatronUI();
                    break;
                case 3:
                    listPatronsUI();
                    break;
                case 4:
                    importPatronsUI();
                    break;
                case 5:
                    quit = true;
                    System.out.println("\n");
                    System.out.println("Exiting Library Management System.");
                    break;
                default:
                    System.out.println("\n");
                    System.out.println(RED + "ERROR! Please select a valid option." + RESET);
            }
        }
    }

    private void addPatronUI() {
        System.out.println("\n");
        System.out.println("Enter New Patron Information: ");
        try {
            int id;

            while (true) {
                System.out.print("New Patron ID: ");
                String idInput = scanner.nextLine();

                if (idInput.matches("\\d{7}")) {
                    id = Integer.parseInt(idInput);
                    break;
                } else {
                    System.out.println(RED + "ERROR! ID must be 7 digits."+ RESET);
                }
            }

            System.out.print("New Patron Name: ");
            String name = scanner.nextLine();

            System.out.print("New Patron Address: ");
            String address = scanner.nextLine();

            System.out.print("New Patron Fine Amount (Numerics only): ");
            double fineAmount = Double.parseDouble(scanner.nextLine());

            Patron patron = new Patron(id, name, address, fineAmount);
            if (libraryManagementSystemBrain.addPatron(patron)) {
                System.out.println("\n");
                System.out.println(GREEN + "SUCCESS! Patron added successfully!" + RESET);
                libraryManagementSystemBrain.listPatrons();
            } else {
                System.out.println("\n");
                System.out.println(RED + "ERROR! Patron could not be added. Check ID or Fine amount." + RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println("\n");
            System.out.println(RED + "ERROR! Invalid input. Please try again." + RESET);
        }
    }


    private void removePatronUI() {
        System.out.println("\n");
        System.out.print("Enter Patron ID to Remove: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (libraryManagementSystemBrain.removePatron(id)) {
                System.out.println("\n");
                System.out.println(GREEN + "SUCCESS! Patron removed successfully!" + RESET);
                libraryManagementSystemBrain.listPatrons();
            } else {
                System.out.println("\n");
                System.out.println(RED + "ERROR! Patron not found. Check the ID and try again." + RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println("\n");
            System.out.println(RED + "\nERROR! Invalid ID format. Please enter a number." + RESET);
        }
    }


    private void listPatronsUI() {
        System.out.println("\n");
        System.out.println("List of Current Patrons:");
        libraryManagementSystemBrain.listPatrons();
    }



    private void importPatronsUI() {
        System.out.println("\n");
        System.out.print("Enter File Path to important Patrons from file: ");
        String filePath = scanner.nextLine();
        if (libraryManagementSystemBrain.importPatron(filePath)) {
            System.out.println(GREEN + "SUCCESS! Patrons imported successfully!" + RESET);
            libraryManagementSystemBrain.listPatrons();
        } else {
            System.out.println(RED + "ERROR! Failed to import patrons. Resolve the above errors and try again." + RESET);
        }
    }
}
