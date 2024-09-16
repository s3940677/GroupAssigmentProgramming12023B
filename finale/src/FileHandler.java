import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    // Load a list of Users from a file in CSV format using UserFactory
    public static List<User> loadUsersFromFile(String filePath) {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipHeader = true;

            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false; // Skip the header row
                    continue;
                }

                // Use the UserFactory to create the correct User subclass based on userType
                User user = UserFactory.createUserFromCsv(line);
                if (user != null) {
                    users.add(user);  // Add the user only if it's not null
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
        return users;
    }

    public static List<Car> loadCarsFromFile(String filePath) {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipHeader = true;

            while ((line = reader.readLine()) != null) {
                // Skip the header row
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                // Skip empty lines or lines that contain only whitespace
                if (line.trim().isEmpty()) {
                    continue;  // Skip this iteration and move to the next line
                }

                // Convert CSV line to Car object
                Car car = Car.fromCsv(line);
                cars.add(car);  // Add the car to the list
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
        return cars;
    }

    // Save a list of Cars to a file in CSV format
    public static void saveCarsToFile(String filePath, List<Car> cars) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write the header (optional)
            writer.println("carId,make,model,year,mileage,color,price,status,notes");
    
            // Write each car's details as CSV
            for (Car car : cars) {
                writer.println(car.toCsv());  // Use the toCsv() method
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }
    

    public static List<AutoPart> loadPartsFromFile(String filePath) {
        List<AutoPart> parts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipHeader = true;
            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;  // Skip the header
                }

                // Skip empty lines or lines that contain only whitespace
                if (line.trim().isEmpty()) {
                    continue;  // Skip this iteration and move to the next line
                }

                try {
                    AutoPart part = AutoPart.fromCsv(line);  // Convert CSV line to AutoPart object
                    parts.add(part);
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid CSV line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
        return parts;
    }

    
    public static void savePartsToFile(String filePath, List<AutoPart> parts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write the header
            writer.println("partId,partName,manufacturer,partNumber,cost,condition,warranty,notes");

            // Write each part's details as a CSV line
            for (AutoPart part : parts) {
                writer.println(part.getPartId() + "," + part.getPartName() + "," + part.getManufacturer() + "," +
                               part.getPartNumber() + "," + part.getCost() + "," + part.getCondition() + "," +
                               part.getWarranty() + "," + part.getNotes());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }
    


    public static void saveTransactionsToFile(String filePath, List<SalesTransaction> transactions) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write the header if needed
            writer.println("transactionId,transactionDate,clientId,salespersonId,purchasedItems,discount,totalAmount,notes");

            for (SalesTransaction transaction : transactions) {
                writer.println(transaction.toCsv());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }

    // Load sales transactions from file
    public static List<SalesTransaction> loadTransactionsFromFile(String filePath) {
        List<SalesTransaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipHeader = true;
            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;  // Skip the header row
                }
    
                // Skip lines that are empty or contain only whitespace
                if (line.trim().isEmpty()) {
                    continue;  // Skip empty lines
                }
    
                // Parse the CSV line into a SalesTransaction object
                try {
                    SalesTransaction transaction = SalesTransaction.fromCsv(line);
                    transactions.add(transaction);
                } catch (IllegalArgumentException e) {
                    System.out.println("An error occurred while parsing line: " + line);
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
        return transactions;
    }
    
    

    public static List<Service> loadServicesFromFile(String filePath) {
        List<Service> services = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipHeader = true;
            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                // Skip empty or whitespace-only lines
                if (line.trim().isEmpty()) {
                    continue;
                }
                services.add(Service.fromCsv(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
        return services;
    }
    
    
    // Save services to CSV file
    public static void saveServicesToFile(String filePath, List<Service> services) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("serviceId,serviceDate,clientId,mechanicId,serviceType,replacedParts,serviceCost,notes");
            for (Service service : services) {
                writer.println(service.toCsv());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }

public static List<Client> loadClientsFromFile(String filePath) {
    List<Client> clients = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        boolean skipHeader = true;
        while ((line = reader.readLine()) != null) {
            if (skipHeader) {
                skipHeader = false;  // Skip the header row
                continue;
            }
            String[] values = line.split(",");
            if (values.length < 7) continue;  // Ensure that the line has all necessary values

            // Parse the values from the CSV file
            String userId = values[0];  // Client ID should start with "cl"
            if (!userId.startsWith("cl")) {
                System.out.println("Invalid Client ID format: " + userId);
                continue;  // Skip invalid IDs
            }

            String fullName = values[1];
            String username = values[2];
            String password = values[3];
            String status = values[4];
            double totalSpending = Double.parseDouble(values[5]);
            String membershipLevel = values[6];

            // Create a new Client object and add it to the list
            Client client = new Client(userId, fullName, username, password, status, totalSpending, membershipLevel);
            clients.add(client);
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + filePath);
        e.printStackTrace();
    }
    return clients;
}


public static void saveClientsToFile(String filePath, List<Client> clients) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
        writer.println("userId,fullName,username,password,status,totalSpending,membershipLevel");

        for (Client client : clients) {
            writer.println(client.getUserId() + "," + client.getFullName() + "," +
                           client.getUsername() + "," + client.getPassword() + "," +
                           client.getStatus() + "," + client.getTotalSpending() + "," + 
                           client.getMembershipLevel());
        }
    } catch (IOException e) {
        System.out.println("Error writing to file: " + filePath);
        e.printStackTrace();
    }
}



    
}








