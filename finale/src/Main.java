import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.display();
      
        Scanner scanner = new Scanner(System.in);  // Initialize scanner for input
        
        try {
            // Load users from the CSV file using FileHandler and UserFactory
            List<User> users = FileHandler.loadUsersFromFile("data/users.csv");

            // Initialize login system with loaded users
            LoginSystem loginSystem = new LoginSystem(users);

            // Display welcome message
            System.out.println("Welcome to AUTO136 Car Dealership System!");

            // Allow up to 3 attempts for the user to log in
            User loggedInUser = null;
            int attempts = 3;
            while (attempts > 0) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                // Attempt to login
                loggedInUser = loginSystem.login(username, password);
                if (loggedInUser != null) {
                    break;  // Login successful, exit the loop
                } else {
                    attempts--;
                    System.out.println("Invalid login credentials. You have " + attempts + " attempts remaining.");
                }
            }

            if (loggedInUser != null) {
                System.out.println("Login successful! Welcome, " + loggedInUser.getFullName());

                // Display menu based on user role
                Menu menu = new Menu();
                menu.displayMenu(loggedInUser);
            } else {
                System.out.println("Too many failed login attempts. Exiting.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the scanner to avoid resource leaks
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}

