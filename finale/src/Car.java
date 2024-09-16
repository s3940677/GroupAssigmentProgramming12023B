import java.time.LocalDate;

public class Car {
    private String carId;
    private String make;
    private String model;
    private int year;
    private int mileage;
    private String color;
    private double price;
    private String status;
    private String notes;
    private LocalDate saleDate;  // Add saleDate to track when the car was sold

    // Constructor
    public Car(String carId, String make, String model, int year, int mileage, String color, String status, double price, String notes) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.color = color;
        this.price = price;
        this.status = status;
        this.notes = notes;
        this.saleDate = null;
    }

    public static Car fromCsv(String csvLine) {
        String[] values = csvLine.split(",");
        
        if (values.length != 10) {
            throw new IllegalArgumentException("Invalid CSV format: " + csvLine);
        }
        
        try {
            String carId = values[0];              // Car ID
            String make = values[1];               // Make
            String model = values[2];              // Model
            int year = Integer.parseInt(values[3]);// Year
            int mileage = Integer.parseInt(values[4]);// Mileage
            String color = values[5];              // Color
            double price = Double.parseDouble(values[6]); // Price
            String status = values[7];             // Status (available/sold)
            String notes = values[8];              // Notes
            String saleDateStr = values[9];        // Sale Date (can be null)
            
            // Handle sale date parsing, allow null
            LocalDate saleDate = null;
            if (!saleDateStr.equals("null") && !saleDateStr.isEmpty()) {
                saleDate = LocalDate.parse(saleDateStr); // Parsing date only if it's not "null" or empty
            }
            
            Car car = new Car(carId, make, model, year, mileage, color, status, price, notes);
            car.setSaleDate(saleDate); // Set the sale date
            return car;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing number from CSV line: " + csvLine, e);
        }
    }
    
    

    public String toCsv() {
        // Use "null" for saleDate if it's not set
        String saleDateStr = (saleDate != null) ? saleDate.toString() : "null";
        return carId + "," + make + "," + model + "," + year + "," + mileage + "," + color + "," + price + "," + status + "," + notes + "," + saleDateStr;
    }

    // Getters for all attributes
    public String getCarId() { return carId; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public int getMileage() { return mileage; }
    public String getColor() { return color; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }
    public String getNotes() { return notes; }
    public LocalDate getSaleDate() { return saleDate; }

    // Setters for all attributes
    public void setCarId(String carId) { this.carId = carId; }
    public void setMake(String make) { this.make = make; }
    public void setModel(String model) { this.model = model; }
    public void setYear(int year) { this.year = year; }
    public void setMileage(int mileage) { this.mileage = mileage; }
    public void setColor(String color) { this.color = color; }
    public void setPrice(double price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }

    // toString method to display car details in a readable format
    @Override
    public String toString() {
        return "Car{" +
                "carId='" + carId + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                ", saleDate='" + (saleDate != null ? saleDate.toString() : "Not Sold") + '\'' +
                '}';
    }
}
