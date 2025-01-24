/*Dayton Hannaford,
CEN-3024C-24204

This class is designed as the Library Management Systems brain that handles the methods for all things patrons. This includes
all capabilities around adding/removing patrons manually as well as printing them to a list in the console */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.io.*;
import java.util.ArrayList;

public class LibraryManagementSystemBrain {
    private List<Patron> patrons;
    private ConstraintsAndErrors constraints;

    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";

    public LibraryManagementSystemBrain() {
        patrons = new ArrayList<>();
        constraints = new ConstraintsAndErrors();
    }

    // Add a Patron manually
    public boolean addPatron(Patron patron) {
        if (constraints.validateUniqueID(patron.getId(), patrons)) {
            patrons.add(patron);
            return true;
        }
        System.out.println(RED + "ERROR! Patron ID is not unique." + RESET);
        return false;
    }

    // Remove a Patron manually
    public boolean removePatron(int id) {
        for (Patron patron : patrons) {
            if (patron.getId() == id) {
                patrons.remove(patron);
                return true;
            }
        }
    constraints.handleInvalidIDSearch(id, patrons); {
        return false;
        }
    }

    // List all Patrons
    public void listPatrons() {
        if (patrons.isEmpty()) {
        } else {
            for (Patron patron : patrons) {
                System.out.println(patron.toString());
            }
        }
    }

    // Imports Patrons from file with different error checks that pull from ConstraintsAndErrors to check if things are in order
    public boolean importPatron(String filePath) {
        File file = new File(filePath);
        int lineNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) !=null) {
                lineNumber++;

                if (!constraints.isValidateFileStructure(line)) {
                    System.out.println(RED + "ERROR! structure is not valid on line: " + lineNumber +"!" + RESET);
                    return false;
                }

                String[] fields = line.split("-");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                String address = fields[2];
                double fineAmount = Double.parseDouble(fields[3]);

                if (!constraints.validateUniqueID(id, patrons)) {
                    System.out.println(RED + "ERROR! Patron ID is not unique on line: " + lineNumber + RESET);
                }
                Patron patron = new Patron(id, name, address, fineAmount);
                patrons.add(patron);
            }
        } catch (IOException e) {
            System.out.println(RED + "ERROR READING FILE: " + e.getMessage() + RESET);
            return false;
        } catch (NumberFormatException e) {
            System.out.println(RED + "ERROR PARSING FILE AT LINE: " + lineNumber + RESET);
            return false;
        }
        return true;
    }
}