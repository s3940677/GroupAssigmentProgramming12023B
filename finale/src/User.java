import java.util.List;

public abstract class User implements CsvConvertible<User> {
    protected String userId;
    protected String fullName;
    protected String username;
    protected String password;
    protected String userType;
    protected String status;

    // Full constructor for user
    public User(String userId, String fullName, String username, String password, String userType, String status) {
        this.userId = userId;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.status = status;
    }

    public void displayUserDetails() {
        System.out.println("User ID: " + userId);
        System.out.println("Full Name: " + fullName);
        System.out.println("Username: " + username);
        System.out.println("Status: " + status);
    }

    public abstract List<String> getPermissions();

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Convert object to CSV string
    @Override
    public String toCsv(User user) {
        return userId + "," + fullName + "," + username + "," + password + "," + userType + "," + status;
    }

    // Abstract method to handle CSV creation dynamically in subclasses
    @Override
    public abstract User fromCsv(String csvLine);

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
