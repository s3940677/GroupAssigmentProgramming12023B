import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServiceService {
    private List<Service> services;
    private static final String SERVICES_FILE = "data/services.csv";

    public ServiceService() {
        services = FileHandler.loadServicesFromFile(SERVICES_FILE);  // Load services from the file
    }

    public void viewServices() {
        if (services.isEmpty()) {
            System.out.println("No services available.");
        } else {
            for (Service service : services) {
                System.out.println(service);  // Assuming Service has a proper toString() method
            }
        }
    }

    public void addService(Service service) {
        services.add(service);
        saveServicesToFile();
        System.out.println("Service added successfully!");
    }

    private void saveServicesToFile() {
        FileHandler.saveServicesToFile(SERVICES_FILE, services);
    }

    public List<Service> getServices() {
        return services;
    }

    public double calculateServiceRevenueByMechanic(String mechanicId) {
        double totalRevenue = 0;
        for (Service service : services) {
            if (service.getMechanicId().equals(mechanicId)) {
                totalRevenue += service.getServiceCost();
            }
        }
        return totalRevenue;
    }

    public List<Service> listServicesInPeriod(String startDate, String endDate) {
        List<Service> filteredServices = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Convert input strings to LocalDate objects
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        for (Service service : services) {
            // Get the service date (assuming it's stored as a LocalDate)
            LocalDate serviceDate = service.getServiceDate();

            // Check if the service date is between the start and end dates (inclusive)
            if ((serviceDate.isEqual(start) || serviceDate.isAfter(start)) && 
                (serviceDate.isEqual(end) || serviceDate.isBefore(end))) {
                filteredServices.add(service);
            }
        }

        return filteredServices;
    }

        

    public List<Service> listServicesInMonth(int month, int year) {
        List<Service> servicesInMonth = new ArrayList<>();
    
        for (Service service : services) {
            LocalDate serviceDate = service.getServiceDate(); // Assuming getServiceDate returns LocalDate
            if (serviceDate.getMonthValue() == month && serviceDate.getYear() == year) {
                servicesInMonth.add(service);
            }
        }
    
        return servicesInMonth;
    }

    public double calculateMechanicRevenue(String mechanicId, String startDate, String endDate) {
        double totalRevenue = 0.0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
    
        for (Service service : services) {
            LocalDate serviceDate = service.getServiceDate();
            if (service.getMechanicId().equals(mechanicId) &&
                (serviceDate.isEqual(start) || serviceDate.isAfter(start)) &&
                (serviceDate.isEqual(end) || serviceDate.isBefore(end))) {
                totalRevenue += service.getServiceCost();
            }
        }
        return totalRevenue;
    }
    
    
}
    
    
    
    
    

    




