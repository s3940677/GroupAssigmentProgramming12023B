import java.util.Arrays;
import java.util.List;

public class Salesperson extends User {

    public Salesperson(String userId, String fullName, String username, String password, String status) {
        super(userId, fullName, username, password, "Salesperson", status);
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList(
            "View Cars",
            "Sell Car",
            "Sell Part",
            "View Sales Transactions",
            "Calculate Revenue"  
        );
    }

    @Override
    public void displayUserDetails() {
        super.displayUserDetails();
        System.out.println("Role: Salesperson");
    }
}




