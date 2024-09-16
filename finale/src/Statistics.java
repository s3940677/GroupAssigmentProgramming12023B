import java.util.List;

public class Statistics {

    // Calculate total revenue within a period
    public double calculateRevenue(List<SalesTransaction> transactions) {
        double totalRevenue = 0;
        for (SalesTransaction transaction : transactions) {
            totalRevenue += transaction.getTotalAmount();
        }
        return totalRevenue;
    }

    // Count the number of cars sold in a period
    public int countCarsSold(List<SalesTransaction> transactions) {
        int carsSold = 0;
        for (SalesTransaction transaction : transactions) {
            carsSold += transaction.getPurchasedItems().stream()
                .filter(item -> item.startsWith("c")).count(); // Assuming car IDs start with 'c'
        }
        return carsSold;
    }
}
