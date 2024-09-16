import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AutoPartService implements ICrudOperations<AutoPart> {

    private List<AutoPart> parts = new ArrayList<>(); // A list to store AutoParts
    private static final String PARTS_FILE = "data/parts.csv"; // Correct file path

    public AutoPartService() {
        this.parts = FileHandler.loadPartsFromFile(PARTS_FILE); // Load parts from the correct file when initializing
    }

    public void addPart(AutoPart part) {
        parts.add(part); // Add the new part to the list
        FileHandler.savePartsToFile(PARTS_FILE, parts); // Save the list to the file using FileHandler
        System.out.println("Part added: " + part.getPartId());
    }

    @Override
    public AutoPart read(String id) {
        for (AutoPart part : parts) {
            if (part.getPartId().equals(id)) {
                return part;
            }
        }
        System.out.println("Part with ID " + id + " not found.");
        return null;
    }

    public void updatePart(String partId, AutoPart updatedPart) {
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).getPartId().equals(partId)) {
                parts.set(i, updatedPart); // Update the existing part
                FileHandler.savePartsToFile(PARTS_FILE, parts); // Save the updated list to the file
                System.out.println("Part updated: " + updatedPart.getPartId());
                return;
            }
        }
        System.out.println("Part with ID " + partId + " not found.");
    }

    @Override
    public void delete(String id) {
        for (AutoPart part : parts) {
            if (part.getPartId().equals(id)) {
                parts.remove(part); // Remove the part from the list
                FileHandler.savePartsToFile(PARTS_FILE, parts); // Save the updated list to the file
                System.out.println("Part removed: " + id);
                return;
            }
        }
        System.out.println("Part with ID " + id + " not found.");
    }

    public void viewParts() {
        if (parts.isEmpty()) {
            System.out.println("No parts available.");
        } else {
            System.out.println("List of Parts:");
            for (AutoPart part : parts) {
                System.out.println(part); // Assuming AutoPart has a proper toString() method
            }
        }
    }

    @Override
    public AutoPart getPartById(String partId) {
        return read(partId); // Reusing the read method
    }

    public List<AutoPart> listPartsSoldInPeriod(String startDate, String endDate) {
        List<AutoPart> filteredParts = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        for (AutoPart part : parts) {
            if (part.getSaleDate() != null) { // Ensure saleDate is not null
                LocalDate saleDate = part.getSaleDate();
                if ((saleDate.isEqual(start) || saleDate.isAfter(start)) &&
                        (saleDate.isEqual(end) || saleDate.isBefore(end))) {
                    filteredParts.add(part);
                }
            }
        }

        return filteredParts;
    }

    public List<AutoPart> listPartsSoldInMonth(int month, int year) {
        List<AutoPart> partsSoldInMonth = new ArrayList<>();
        for (AutoPart part : parts) {
            if (part.getSaleDate() != null) { // Ensure saleDate is not null
                LocalDate saleDate = part.getSaleDate();
                if (saleDate.getMonthValue() == month && saleDate.getYear() == year) {
                    partsSoldInMonth.add(part);
                }
            }
        }
        return partsSoldInMonth;
    }

    public void sellPart(String partId, double discount, String clientId, String salespersonId) {
        AutoPart part = getPartById(partId);
        if (part != null) {
            double totalAmount = part.getPrice() - discount;
            // Create a sales transaction for the part
            SalesTransaction transaction = new SalesTransaction(
                getNextTransactionId(),  // Generate a new transaction ID
                LocalDate.now().toString(), // Current date
                clientId, // Client ID
                salespersonId, // Salesperson ID
                partId, // Purchased item
                discount,
                totalAmount,
                "Part sale"
            );
            
            // Save the transaction
            salesTransactionService.addTransaction(transaction);
            
            // Update the part status or remove it if necessary
            delete(partId);
            
            System.out.println("Part sold successfully!");
        } else {
            System.out.println("Part with ID " + partId + " not found.");
        }
    }
    
    private String getNextTransactionId() {
        // Implement logic to generate the next transaction ID
        return "t" + (salesTransactionService.getTransactions().size() + 1); // Example logic
    }
}
