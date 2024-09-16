import java.util.List;

public class LoginSystem {
    private List<User> users;

    public LoginSystem(List<User> users) {
        this.users = users;
    }

    // Authenticate user by username and password
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }
}


