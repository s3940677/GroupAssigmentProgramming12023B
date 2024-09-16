public class UserFactory {

    public static User createUserFromCsv(String csvLine) {
        // Ensure the line is not empty or just whitespace
        if (csvLine == null || csvLine.trim().isEmpty()) {
            return null;  // Return null for empty lines
        }

        String[] values = csvLine.split(",");

        // Check if the CSV line contains the correct number of fields (expecting 6 fields)
        if (values.length < 6) {
            throw new IllegalArgumentException("Invalid CSV format: " + csvLine);
        }

        String userType = values[4]; // Extract userType from the CSV line

        switch (userType) {
            case "Manager":
                return new Manager(values[0], values[1], values[2], values[3], values[5]);
            case "Salesperson":
                return new Salesperson(values[0], values[1], values[2], values[3], values[5]);
            case "Mechanic":
                return new Mechanic(values[0], values[1], values[2], values[3], values[5]);
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }
}




