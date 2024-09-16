import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalesTransactionService {
    private List<SalesTransaction> transactions;
    private static final String TRANSACTIONS_FILE = "data/transactions.csv";  // Adjust file path as needed
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Ensure consistent date formatting

    public SalesTransactionService() {
        transactions = FileHandler.loadTransactionsFromFile(TRANSACTIONS_FILE);  // Load transactions from file
    }

    // Define getNextTransactionId() to generate the next transaction ID
    public String getNextTransactionId() {
        return "t" + (transactions.size() + 1);  // Assuming transactions are sequential
    }

    public void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No sales transactions available.");
        } else {
            System.out.println("Sales Transactions:");
            for (SalesTransaction transaction : transactions) {
                System.out.println(transaction);  // Assuming SalesTransaction has a proper toString() method
            }
        }
    }

    public void createSalesTransaction(String transactionId, Client client, String salespersonId, Car car, double discount) {
        double totalAmount = car.getPrice() - discount;
        String purchasedItems = car.getCarId();  // Purchased car's ID
        String transactionDate = getCurrentDate();  // Get the current date

        // Create a new SalesTransaction object
        SalesTransaction transaction = new SalesTransaction(
            transactionId, transactionDate, client.getUserId(), salespersonId, purchasedItems, discount, totalAmount, "Car purchase"
        );

        // Add transaction to the list and save to the file
        transactions.add(transaction);
        saveTransactionsToFile();

        System.out.println("Car sold successfully! Transaction ID: " + transactionId);
    }

    public void addTransaction(SalesTransaction transaction) {
        transactions.add(transaction);
        saveTransactionsToFile();
        System.out.println("Transaction added: " + transaction.getTransactionId());
    }

    private void saveTransactionsToFile() {
        FileHandler.saveTransactionsToFile(TRANSACTIONS_FILE, transactions);
    }

    private String getCurrentDate() {
        return LocalDate.now().format(formatter);  // Use formatter to get current date as string
    }

    public double calculateSalesRevenue(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        double totalRevenue = 0.0;

        for (SalesTransaction transaction : transactions) {
            LocalDate transactionDate = transaction.getTransactionDate();
            if ((transactionDate.isEqual(start) || transactionDate.isAfter(start)) &&
                (transactionDate.isEqual(end) || transactionDate.isBefore(end))) {
                totalRevenue += transaction.getTotalAmount();
            }
        }
        return totalRevenue;
    }

    public List<SalesTransaction> listTransactionsInPeriod(String startDate, String endDate) {
        List<SalesTransaction> filteredTransactions = new ArrayList<>();
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        for (SalesTransaction transaction : transactions) {
            LocalDate transactionDate = transaction.getTransactionDate();

            if ((transactionDate.isEqual(start) || transactionDate.isAfter(start)) && 
                (transactionDate.isEqual(end) || transactionDate.isBefore(end))) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }

    public List<SalesTransaction> listTransactionsInMonth(int month, int year) {
        List<SalesTransaction> transactionsInMonth = new ArrayList<>();
        
        for (SalesTransaction transaction : transactions) {
            LocalDate transactionDate = transaction.getTransactionDate();
            if (transactionDate.getMonthValue() == month && transactionDate.getYear() == year) {
                transactionsInMonth.add(transaction);
            }
        }

        return transactionsInMonth;
    }

    public double calculateSalespersonRevenue(String salespersonId, String startDate, String endDate) {
        double totalRevenue = 0.0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
    
        for (SalesTransaction transaction : transactions) {
            LocalDate transactionDate = transaction.getTransactionDate();
            if (transaction.getSalespersonId().equals(salespersonId) &&
                (transactionDate.isEqual(start) || transactionDate.isAfter(start)) &&
                (transactionDate.isEqual(end) || transactionDate.isBefore(end))) {
                totalRevenue += transaction.getTotalAmount() - transaction.getDiscount();  // Adjust total by discount
            }
        }
        return totalRevenue;
    }
    
    
    
    
}
