import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoPart {
    private String partId;
    private String partName;
    private String manufacturer;
    private String partNumber;
    private double cost;
    private String condition;
    private String warranty;
    private String notes;

    // Constructor with parameters in the correct order
    public AutoPart(String partId, String partName, String manufacturer, String partNumber, double cost, String condition, String warranty, String notes) {
        this.partId = partId;
        this.partName = partName;
        this.manufacturer = manufacturer;
        this.partNumber = partNumber;
        this.cost = cost;
        this.condition = condition;
        this.warranty = warranty;
        this.notes = notes;
    }

    // Getters and setters for each field
    public String getPartId() { return partId; }
    public void setPartId(String partId) { this.partId = partId; }

    public String getPartName() { return partName; }
    public void setPartName(String partName) { this.partName = partName; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public String getPartNumber() { return partNumber; }
    public void setPartNumber(String partNumber) { this.partNumber = partNumber; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public String getWarranty() { return warranty; }
    public void setWarranty(String warranty) { this.warranty = warranty; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    // CSV parsing method
   public static AutoPart fromCsv(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            throw new IllegalArgumentException("Empty or null CSV line");
        }

        // Regex pattern to correctly parse CSV values, including quoted strings with commas
        Pattern pattern = Pattern.compile("(?<=^|,)(\"[^\"]*\"|[^,]*)");
        Matcher matcher = pattern.matcher(csvLine);
        String[] values = new String[8];
        int index = 0;

        while (matcher.find()) {
            String value = matcher.group(1);
            if (value.startsWith("\"") && value.endsWith("\"")) {
                value = value.substring(1, value.length() - 1); // Remove surrounding quotes
            }
            values[index++] = value;
        }

        if (index != 8) {
            throw new IllegalArgumentException("Invalid CSV format: " + csvLine);
        }

        String partId = values[0];
        String partName = values[1];
        String manufacturer = values[2];
        String partNumber = values[3];
        double cost = Double.parseDouble(values[4]);
        String condition = values[5];
        String warranty = values[6];
        String notes = values[7];

        return new AutoPart(partId, partName, manufacturer, partNumber, cost, condition, warranty, notes);
    }

    // Method to convert an AutoPart object to a CSV line
    public String toCsv() {
        return partId + "," + partName + "," + manufacturer + "," + partNumber + "," + cost + "," + condition + "," + warranty + "," + notes;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "AutoPart{" +
                "partId='" + partId + '\'' +
                ", partName='" + partName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", condition='" + condition + '\'' +
                ", warranty='" + warranty + '\'' +
                ", cost=" + cost +
                ", notes='" + notes + '\'' +
                '}';
    }
}
