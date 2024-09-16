import java.util.Arrays;
import java.util.List;

public class Client extends User {
    private double totalSpending;
    private String membershipLevel;

    // Constructor that matches the arguments used in FileHandler
    public Client(String userId, String fullName, String username, String password, String status, double totalSpending, String membershipLevel) {
        super(userId, fullName, username, password, "Client", status);  // Call the parent User constructor
        this.totalSpending = totalSpending;
        this.membershipLevel = membershipLevel;
    }
    public void updateMembershipLevel() {
        if (totalSpending > 250_000_000) {
            membershipLevel = "Platinum";
        } else if (totalSpending > 100_000_000) {
            membershipLevel = "Gold";
        } else if (totalSpending > 30_000_000) {
            membershipLevel = "Silver";
        } else {
            membershipLevel = "Basic";  // Default membership level
        }
    }

    public void addSpending(double amount) {
        totalSpending += amount;
        updateMembershipLevel();
    }

    // Getters and Setters
    public double getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(double totalSpending) {
        this.totalSpending = totalSpending;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    // Override to provide Client-specific permissions
    @Override
    public List<String> getPermissions() {
        return Arrays.asList(
            "View Cars", 
            "View Parts", 
            "View Service History", 
            "View Transactions"
        );
    }

    // Override to display Client-specific details
    @Override
    public void displayUserDetails() {
        super.displayUserDetails();  // Call the parent method to display common user details
        System.out.println("Total Spending: " + totalSpending);  // Display Client's total spending
        System.out.println("Membership Level: " + membershipLevel);  // Correct the variable name to membershipLevel
    }
}
