import java.util.Arrays;
import java.util.List;

public class Manager extends User {

    public Manager(String userId, String fullName, String username, String password, String status) {
        super(userId, fullName, username, password, "Manager", status);
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList(
            "View Cars",
            "View Parts",
            "Add Car",
            "Sell Car",
            "Sell Part",
            "Remove Car",
            "Add Part",
            "Remove Part",
            "View Sales Transactions",  
            "View Services",
            "Calculate Cars Sold in a Month",
            "Calculate Revenue",
            "List Transactions in a Month",
            "List Services in a Month"
            
        );
    }

    @Override
    public void displayUserDetails() {
        super.displayUserDetails();
        System.out.println("Role: Manager");
    }
}


