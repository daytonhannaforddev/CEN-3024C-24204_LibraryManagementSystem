/*Dayton Hannaford,
CEN-3024C-24204
This program is designed as a Library Management System that accepts files with patrons and adds them to a list that can be printed out.
In addition, users can add patrons manually as well as remove them manually.*/

import javax.swing.*;

/*the jOptionPane was added to check if the java file was opening correctly. I wanted some sort of visual and found I can add a couple lines and display as message
What I found out also was that running the jar doesn't open a command line when you double click it. So while the popup ensured it was working, I found I can make a bat
and open a command line that way.*/

public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to the Library Management System!");
        ConsoleInterface console = new ConsoleInterface();
        console.loadPatronsFile(); // Start the program
        console.mainMenu();
    }
}
