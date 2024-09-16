import java.util.List;
import java.util.Scanner;

public class MenuSystem {

    // Display the menu based on user permissions
    public void displayMenu(User user) {
        System.out.println("Welcome, " + user.getFullName() + "! You have the following permissions:");

        List<String> permissions = user.getPermissions();
        for (int i = 0; i < permissions.size(); i++) {
            System.out.println((i + 1) + ". " + permissions.get(i));
        }

        System.out.println("Select an operation by entering the corresponding number:");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        // Handle the selected operation based on user permissions
        handleOperation(choice, permissions);
    }

    // Handle operations based on user selection
    private void handleOperation(int choice, List<String> permissions) {
        if (choice > 0 && choice <= permissions.size()) {
            System.out.println("Performing: " + permissions.get(choice - 1));
            // Perform the actual operation here based on the permission
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}
