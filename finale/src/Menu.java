import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private CarService carService;
    private AutoPartService partService;
    private SalesTransactionService salesTransactionService;
    private ServiceService serviceService;
    private ClientService clientService;

    private User user;
    
    private Scanner scanner = new Scanner(System.in); // Use a single scanner for input

    public Menu() {
        carService = new CarService(); // Initialize CarService
        partService = new AutoPartService(); // Initialize AutoPartService
        salesTransactionService = new SalesTransactionService(); // Initialize SalesTransactionService
        serviceService = new ServiceService();
        clientService = new ClientService(); // Initialize ServiceService
        
    }

    public void displayMenu(User user) {
        List<String> options = user.getPermissions(); // Get the permissions for the logged-in user

        while (true) {
            System.out.println("Welcome, " + user.getFullName() + "! You have the following options:");

            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            System.out.println("Please select an option (or type 0 to exit):");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }

            if (choice > 0 && choice <= options.size()) {
                String selectedOption = options.get(choice - 1);
                handleOperation(selectedOption, user); // Fix: Pass user, not scanner
            } else {
                System.out.println("Invalid option selected.");
            }
        }
    }

    private void handleOperation(String operation, User user) {
        String startDate = null; // Declare variables outside conditional blocks
        String endDate = null;
        switch (operation) {
            case "Add Car":
                addCarOperation(scanner); // Pass scanner
                break;
            case "View Cars":
                carService.viewCars();
                break;
            case "Sell Car":
                sellCarOperation(user, scanner); // Pass user and scanner
                break;
            case "Update Car":
                updateCarOperation(scanner); // Pass scanner
                break;
            case "Remove Car":
                removeCarOperation(scanner); // Pass scanner
                break;

            case "Add Part":
                addPartOperation(scanner); // Pass scanner
                break;
            // case "Sell Part":
            //     if (args.length == 2) {
            //         // Call with default client and salesperson IDs
            //         autoPartService.sellPart(args[0], Double.parseDouble(args[1]));
            //     } else if (args.length == 4) {
            //         // Call with provided client and salesperson IDs
            //         autoPartService.sellPart(args[0], Double.parseDouble(args[1]), args[2], args[3]);
            //     }
            //     break;
            
            case "View Parts":
                partService.viewParts();
                break;
            case "Update Part":
                updatePartOperation(scanner); // Pass scanner
                break;
            case "Remove Part":
                removePartOperation(scanner); // Pass scanner
                break;
            case "View Sales Transactions":
                if (user.getPermissions().contains("View Sales Transactions")) {
                    salesTransactionService.viewTransactions(); // Allow both Manager and Salesperson to view
                                                                // transactions
                } else {
                    System.out.println("You don't have permission to view sales transactions.");
                }
                break;
            case "Perform Service":
                performServiceOperation(scanner);
                break;
            case "View Services":
                serviceService.viewServices();
                break;

            case "List Transactions in a Month":
                System.out.println("Enter month (1-12): ");
                int monthForTransactions = scanner.nextInt(); // Declare monthForTransactions in this case
                System.out.println("Enter year: ");
                int yearForTransactions = scanner.nextInt(); // Declare yearForTransactions in this case
                List<SalesTransaction> transactionsInMonth = salesTransactionService
                        .listTransactionsInMonth(monthForTransactions, yearForTransactions);
                for (SalesTransaction transaction : transactionsInMonth) {
                    System.out.println(transaction);
                }
                break;

            case "Calculate Cars Sold in a Month":
                System.out.println("Enter month (1-12): ");
                int monthForCarsSold = scanner.nextInt(); // Declare monthForCarsSold in this case
                System.out.println("Enter year: ");
                int yearForCarsSold = scanner.nextInt(); // Declare yearForCarsSold in this case
                int carsSoldInMonth = carService.calculateCarsSoldInMonth(monthForCarsSold, yearForCarsSold);
                System.out.println("Cars sold: " + carsSoldInMonth);
                break;

                case "Calculate Revenue":
                if (user.getUserType().equals("Salesperson")) {
                    System.out.println("Enter start date (yyyy-MM-dd):");
                    String salesStartDate = scanner.next();
                    System.out.println("Enter end date (yyyy-MM-dd):");
                    String salesEndDate = scanner.next();
                    
                    double revenue = salesTransactionService.calculateSalesRevenue(salesStartDate, salesEndDate);
                    System.out.println("Total revenue for salesperson: " + revenue);
                } else if (user.getUserType().equals("Mechanic")) {
                    System.out.println("Enter mechanic ID:");
                    String mechanicId = scanner.next();
                    System.out.println("Enter start date (yyyy-MM-dd):");
                    String mechanicStartDate = scanner.next();
                    System.out.println("Enter end date (yyyy-MM-dd):");
                    String mechanicEndDate = scanner.next();
                    
                    double revenue = serviceService.calculateMechanicRevenue(mechanicId, mechanicStartDate, mechanicEndDate);
                    System.out.println("Total revenue for mechanic: " + revenue);
                } else {
                    System.out.println("You don't have permission to calculate revenue.");
                }
                break;
            

            case "List Services in a Month":
                System.out.println("Enter month (1-12): ");
                int serviceMonth = scanner.nextInt(); // Declare serviceMonth for this case
                System.out.println("Enter year: ");
                int serviceYear = scanner.nextInt(); // Declare serviceYear for this case
                List<Service> services = serviceService.listServicesInMonth(serviceMonth, serviceYear);
                System.out.println("Services in the selected month:");
                for (Service service : services) {
                    System.out.println(service);
                }
                break;

            case "List Parts Sold in a Month":
                System.out.println("Enter month (1-12): ");
                int partsMonth = scanner.nextInt(); // Declare partsMonth for this case
                System.out.println("Enter year: ");
                int partsYear = scanner.nextInt(); // Declare partsYear for this case
                List<AutoPart> partsSold = partService.listPartsSoldInMonth(partsMonth, partsYear);
                System.out.println("Parts sold in the selected month:");
                for (AutoPart part : partsSold) {
                    System.out.println(part);
                }
                break;

            case "List All Transactions in Period":
                System.out.println("Enter start date (yyyy-MM-dd): ");
                startDate = scanner.next();
                System.out.println("Enter end date (yyyy-MM-dd): ");
                endDate = scanner.next();
                List<SalesTransaction> allTransactions = salesTransactionService.listTransactionsInPeriod(startDate,
                        endDate);
                System.out.println("Transactions in the selected period:");
                for (SalesTransaction transaction : allTransactions) {
                    System.out.println(transaction);
                }
                break;

            default:
                System.out.println("Operation not supported.");
                break;
        }
    }

    // Method to handle adding a car
    private void addCarOperation(Scanner scanner) {
        System.out.println("Enter car details:");

        System.out.print("Car ID: ");
        String carId = scanner.next();

        System.out.print("Make: ");
        String make = scanner.next();

        System.out.print("Model: ");
        String model = scanner.next();

        System.out.print("Year: ");
        int year = scanner.nextInt();

        System.out.print("Mileage: ");
        int mileage = scanner.nextInt();

        System.out.print("Color: ");
        String color = scanner.next();

        System.out.print("Price: ");
        double price = scanner.nextDouble();

        System.out.print("Status (available/sold): ");
        String status = scanner.next();

        System.out.print("Notes: ");
        scanner.nextLine(); // Consume newline
        String notes = scanner.nextLine();

        // Create a new Car object with the correct argument order
        Car car = new Car(carId, make, model, year, mileage, color, status, price, notes);

        // Add the car using the CarService
        carService.addCar(car);
    }

    private void sellCarOperation(User user, Scanner scanner) {
        System.out.println("Enter Car ID to sell: ");
        String carId = scanner.next();

        // Ask for the sale date
        System.out.println("Enter Sale Date (yyyy-MM-dd): ");
        String saleDateInput = scanner.next();

        // Parse the sale date input to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate saleDate = LocalDate.parse(saleDateInput, formatter);

        // Sell the car using the CarService method
        Car soldCar = carService.sellCar(carId, saleDate); // Now passing both carId and saleDate

        if (soldCar != null) {
            System.out.println("Car sold successfully: " + soldCar);

            // Record the transaction
            double salePrice = soldCar.getPrice(); // Assuming you want to record the sale price
            SalesTransaction transaction = new SalesTransaction(
                salesTransactionService.getNextTransactionId(), // Get the next transaction ID
                saleDate.toString(), // Sale date
                "", // Client ID - replace with actual client ID if applicable
                user.getUserId(), // Salesperson ID - assuming 'user' is the salesperson
                carId, // Car ID
                0, // Discount - assuming no discount; adjust if needed
                salePrice, // Total amount
                "Car sale" // Description
            );

            // Add the transaction using the SalesTransactionService
            salesTransactionService.addTransaction(transaction);

            System.out.println("Transaction recorded successfully.");
        } else {
            System.out.println("Car with ID " + carId + " not found.");
        }
    }

    private void sellPartOperation(User user, Scanner scanner) {
        System.out.println("Enter Part ID to sell: ");
        String partId = scanner.next();
    
        // Ask for the sale date
        System.out.println("Enter Sale Date (yyyy-MM-dd): ");
        String saleDateInput = scanner.next();
    
        // Parse the sale date input to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate saleDate = LocalDate.parse(saleDateInput, formatter);
    
        // Ask for discount amount
        System.out.println("Enter Discount Amount: ");
        double discount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
    
        // Ask for Client ID and Salesperson ID
        System.out.println("Enter Client ID: ");
        String clientId = scanner.nextLine();
    
        System.out.println("Enter Salesperson ID: ");
        String salespersonId = scanner.nextLine();
    
        // Use the AutoPartService to sell the part
        boolean isSold = autoPartService.sellPart(partId, discount, saleDate, clientId, salespersonId);
    
        if (isSold) {
            System.out.println("Part sold successfully.");
        } else {
            System.out.println("Part with ID " + partId + " not found.");
        }
    }
    

    // Assuming a method to get the client by their ID
    private Client getClientById(String clientId) {
        // Replace with actual logic to retrieve a client from the system (from a list
        // or database)
        for (Client client : clients) { // Assuming you have a clients list
            if (client.getUserId().equals(clientId)) {
                return client;
            }
        }
        return null; // Return null if the client is not found
    }

    // Method to handle updating a car
    private void updateCarOperation(Scanner scanner) {
        System.out.print("Enter Car ID to update: ");
        String carId = scanner.next();

        System.out.println("Enter updated details:");
        System.out.print("Make: ");
        String make = scanner.next();

        System.out.print("Model: ");
        String model = scanner.next();

        System.out.print("Year: ");
        int year = scanner.nextInt();

        System.out.print("Mileage: ");
        int mileage = scanner.nextInt();

        System.out.print("Color: ");
        String color = scanner.next();

        System.out.print("Price: ");
        double price = scanner.nextDouble();

        System.out.print("Status (available/sold): ");
        String status = scanner.next();

        System.out.print("Notes: ");
        scanner.nextLine(); // Consume newline
        String notes = scanner.nextLine();

        // Create an updated Car object
        Car updatedCar = new Car(carId, make, model, year, mileage, color, status, price, notes);

        // Update the car using the CarService
        carService.updateCar(carId, updatedCar);
    }

    // Method to handle removing a car
    private void removeCarOperation(Scanner scanner) {
        System.out.print("Enter Car ID to remove: ");
        String carId = scanner.next();
        carService.deleteCar(carId); // Soft delete by marking the car as inactive
    }

    private void addPartOperation(Scanner scanner) {
        System.out.println("Enter part details:");

        System.out.print("Part ID: ");
        String partId = scanner.next();

        System.out.print("Part Name: ");
        String partName = scanner.next();

        System.out.print("Manufacturer: ");
        String manufacturer = scanner.next();

        System.out.print("Part Number: ");
        String partNumber = scanner.next();

        System.out.print("Cost: ");
        double cost = scanner.nextDouble();

        System.out.print("Condition (new/used/refurbished): ");
        String condition = scanner.next();

        System.out.print("Warranty: ");
        String warranty = scanner.next();

        System.out.print("Notes: ");
        scanner.nextLine(); // Consume newline
        String notes = scanner.nextLine();

        // Create a new AutoPart object with correct parameter order
        AutoPart part = new AutoPart(partId, partName, manufacturer, partNumber, cost, condition, warranty, notes);

        // Add the part using the AutoPartService
        partService.addPart(part);
    }

    private void updatePartOperation(Scanner scanner) {
        System.out.print("Enter Part ID to update: ");
        String partId = scanner.next();

        AutoPart existingPart = partService.getPartById(partId);
        if (existingPart == null) {
            System.out.println("Part not found.");
            return;
        }

        System.out.println("Enter updated details:");

        System.out.print("Part Name: ");
        String partName = scanner.next();

        System.out.print("Manufacturer: ");
        String manufacturer = scanner.next();

        System.out.print("Part Number: ");
        String partNumber = scanner.next();

        System.out.print("Condition (new/used/refurbished): ");
        String condition = scanner.next();

        System.out.print("Cost: ");
        double cost = scanner.nextDouble();

        System.out.print("Warranty: ");
        String warranty = scanner.next();

        System.out.print("Notes: ");
        scanner.nextLine(); // Consume newline
        String notes = scanner.nextLine();

        AutoPart updatedPart = new AutoPart(partId, partName, manufacturer, partNumber, cost, condition, warranty,
                notes);

        partService.updatePart(partId, updatedPart); // Call the updatePart method
    }

    private void removePartOperation(Scanner scanner) {
        System.out.print("Enter Part ID to remove: ");
        String partId = scanner.next();
        partService.removePart(partId);
    }

    private void viewSalespersonTransactions(Salesperson salesperson) {
        salesTransactionService.viewTransactionsBySalesperson(salesperson.getUserId());
    }

    private void performServiceOperation(Scanner scanner) {
        System.out.println("Enter service details:");

        System.out.print("Service ID: ");
        String serviceId = scanner.next();

        System.out.print("Service Date (YYYY-MM-DD): ");
        String serviceDate = scanner.next();

        System.out.print("Client ID: ");
        String clientId = scanner.next();

        System.out.print("Mechanic ID: ");
        String mechanicId = scanner.next();

        System.out.print("Service Type: ");
        String serviceType = scanner.next();

        System.out.print("Replaced Parts (semicolon-separated): ");
        String replacedParts = scanner.next();

        double serviceCost = 0.0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Service Cost: ");
            try {
                serviceCost = scanner.nextDouble(); // This is where the exception is thrown if input is invalid
                validInput = true; // If no exception, input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for service cost.");
                scanner.next(); // Clear the invalid input
            }
        }

        System.out.print("Notes: ");
        scanner.nextLine(); // Consume newline
        String notes = scanner.nextLine();

        // Assuming Service constructor and ServiceService implementation
        Service service = new Service(serviceId, serviceDate, clientId, mechanicId, serviceType,
                Arrays.asList(replacedParts.split(";")), serviceCost, notes);
        serviceService.addService(service); // Add the service to the system
        System.out.println("Service added successfully.");
    }

}
