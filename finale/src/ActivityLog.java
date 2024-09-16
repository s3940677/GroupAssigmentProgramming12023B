import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityLog {
    private String userId;
    private String action;
    private String timestamp;

    // Constructor
    public ActivityLog(String userId, String action) {
        this.userId = userId;
        this.action = action;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    // Save the activity log to a file
    public static void logActivity(String filePath, ActivityLog log) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(log.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "User: " + userId + ", Action: " + action + ", Timestamp: " + timestamp;
    }
}
