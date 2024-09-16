public class Membership {
    private String membershipType;  // Silver, Gold, Platinum
    private double discountRate;

    public Membership(String membershipType, double discountRate) {
        this.membershipType = membershipType;
        this.discountRate = discountRate;
    }

    // Getters
    public String getMembershipType() {
        return membershipType;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    @Override
    public String toString() {
        return membershipType + " Membership (" + (discountRate * 100) + "% discount)";
    }

    // Static method to determine membership based on total spending
    public static Membership getMembershipBySpending(double totalSpending) {
        if (totalSpending >= 250_000_000) {
            return new Membership("Platinum", 0.15);  // 15% discount
        } else if (totalSpending >= 100_000_000) {
            return new Membership("Gold", 0.10);      // 10% discount
        } else if (totalSpending >= 30_000_000) {
            return new Membership("Silver", 0.05);    // 5% discount
        } else {
            return new Membership("None", 0.0);       // No discount
        }
    }
}


