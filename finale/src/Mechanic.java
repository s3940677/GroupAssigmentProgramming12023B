import java.util.Arrays;
import java.util.List;

public class Mechanic extends User {

    public Mechanic(String userId, String fullName, String username, String password, String status) {
        super(userId, fullName, username, password, "Mechanic", status);
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList(
            "View Services",
            "Perform Service",
            "Calculate Revenue"
        );
    }

    @Override
    public void displayUserDetails() {
        super.displayUserDetails();
        System.out.println("Role: Mechanic");
    }
}


