/*Dayton Hannaford,
CEN-3024C-24204
This class is designed as the validation system of the Library Management System. It handles all of the sanity checks to ensure proper functionality
and keeps things running smoothly by catching common errors if something is not meant to be.*/

import java.util.List;

 class ConstraintsAndErrors {

    public boolean validateFine(double fineAmount){
        if (fineAmount > 250){
            return false;
        }
        return true;
    }

    // validates whether the file being used to insert patrons has the right structure.
     public boolean isValidateFileStructure(String line) {
        String[] fields = line.split ("-");
        if (fields.length != 4){
            return false;
        }
        try {
            int id = Integer.parseInt(fields[0].trim());
            String name = fields[1].trim();
            String address = fields[2].trim();
            double fineAmount = Double.parseDouble(fields[3].trim());
            return id > 0 && name != null && address != null && fineAmount >= 0 && fineAmount <= 250;
        } catch (NumberFormatException e) {
            return false;
        }
     }

     // checks if ID is unique
     public boolean validateUniqueID(int id, List<Patron> patrons) {
        for (Patron patron : patrons) {
            if (patron.getId() == id) {
                return false;
            }
        }
        return true;
     }

     // Checks if ID you enter exists
     public boolean handleInvalidIDSearch(int id, List<Patron> patrons) {
        for (Patron patron : patrons) {
            if (patron.getId() == id) {
                return true;
            }
        }
        return false;
     }
 }
