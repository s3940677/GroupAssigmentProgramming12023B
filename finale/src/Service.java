import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Service {
    private String serviceId;
    private String serviceDate;
    private String clientId;
    private String mechanicId;
    private String serviceType;
    private List<String> replacedParts; // IDs of replaced parts
    private double serviceCost;
    private String notes;

    public Service(String serviceId, String serviceDate, String clientId, String mechanicId, String serviceType, List<String> replacedParts, double serviceCost, String notes) {
        this.serviceId = serviceId;
        this.serviceDate = serviceDate;
        this.clientId = clientId;
        this.mechanicId = mechanicId;
        this.serviceType = serviceType;
        this.replacedParts = replacedParts;
        this.serviceCost = serviceCost;
        this.notes = notes;
    }

    public static Service fromCsv(String csvLine) {
        String[] values = csvLine.split(",");
        if (values.length != 8) {
            throw new IllegalArgumentException("Invalid CSV format: " + csvLine);
        }
    
        String serviceId = values[0];
        String serviceDate = values[1];
        String clientId = values[2];
        String mechanicId = values[3];
        String serviceType = values[4];
        List<String> replacedParts = Arrays.asList(values[5].split(";"));
        
        // Correctly parse service cost to double
        double serviceCost = Double.parseDouble(values[6]);  // Ensure this parses correctly
        
        String notes = values[7];
    
        return new Service(serviceId, serviceDate, clientId, mechanicId, serviceType, replacedParts, serviceCost, notes);
    }
    
    

    // CSV formatting method
    public String toCsv() {
        return serviceId + "," + serviceDate + "," + clientId + "," + mechanicId + "," + serviceType + "," +
               String.join(";", replacedParts) + "," + serviceCost + "," + notes;
    }

    // Getters and Setters
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }


    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public LocalDate getServiceDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(serviceDate, formatter);  // Convert stored String to LocalDate
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(String mechanicId) {
        this.mechanicId = mechanicId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<String> getReplacedParts() {  // Fixed type to List<String>
        return replacedParts;
    }

    public void setReplacedParts(List<String> replacedParts) {  // Fixed type to List<String>
        this.replacedParts = replacedParts;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceDate='" + serviceDate + '\'' +
                ", clientId='" + clientId + '\'' +
                ", mechanicId='" + mechanicId + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", replacedParts='" + String.join(";", replacedParts) + '\'' +  // Join list into a string
                ", serviceCost=" + serviceCost +
                ", notes='" + notes + '\'' +
                '}';
    }
}
