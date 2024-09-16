import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalesTransaction {
    private String transactionId;
    private LocalDate transactionDate;  // LocalDate type for transactionDate
    private String clientId;
    private String salespersonId;
    private String purchasedItems;
    private double discount;
    private double totalAmount;
    private String notes;

    // Constructor
    public SalesTransaction(String transactionId, String transactionDateStr, String clientId, String salespersonId, String purchasedItems, double discount, double totalAmount, String notes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.transactionId = transactionId;
        this.transactionDate = LocalDate.parse(transactionDateStr, formatter);  // Parse date string to LocalDate
        this.clientId = clientId;
        this.salespersonId = salespersonId;
        this.purchasedItems = purchasedItems;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.notes = notes;
    }

    // Method to return the transaction date as a LocalDate
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    // Corrected setter to accept a LocalDate or String and parse it
    public void setTransactionDate(String transactionDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.transactionDate = LocalDate.parse(transactionDateStr, formatter);  // Parse String to LocalDate
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(String salespersonId) {
        this.salespersonId = salespersonId;
    }

    public String getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(String purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Implement the fromCsv method to parse a CSV string and create a SalesTransaction object
    public static SalesTransaction fromCsv(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            throw new IllegalArgumentException("Empty or null CSV line.");
        }

        try {
            String[] fields = csvLine.split(",");
            if (fields.length != 8) {
                System.out.println("Invalid CSV format: " + csvLine);
                System.out.println("Number of fields: " + fields.length);  // Log the number of fields for debugging
                throw new IllegalArgumentException("Invalid CSV format for SalesTransaction.");
            }

            String transactionId = fields[0].trim();
            String transactionDate = fields[1].trim();
            String clientId = fields[2].trim();
            String salespersonId = fields[3].trim();
            String purchasedItems = fields[4].trim();
            double discount = Double.parseDouble(fields[5].trim());
            double totalAmount = Double.parseDouble(fields[6].trim());
            String notes = fields[7].trim();

            return new SalesTransaction(transactionId, transactionDate, clientId, salespersonId, purchasedItems, discount, totalAmount, notes);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing number in CSV line: " + csvLine, e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing CSV line: " + csvLine, e);
        }
    }

    // Convert the SalesTransaction object back to CSV format
    public String toCsv() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return transactionId + "," + transactionDate.format(formatter) + "," + clientId + "," + salespersonId + "," +
                purchasedItems + "," + discount + "," + totalAmount + "," + notes;
    }

    @Override
    public String toString() {
        return "SalesTransaction{" +
                "transactionId='" + transactionId + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", clientId='" + clientId + '\'' +
                ", salespersonId='" + salespersonId + '\'' +
                ", purchasedItems='" + purchasedItems + '\'' +
                ", discount=" + discount +
                ", totalAmount=" + totalAmount +
                ", notes='" + notes + '\'' +
                '}';
    }
}
