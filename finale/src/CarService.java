import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CarService {
    private List<Car> cars;

    public CarService() {
       
        cars = FileHandler.loadCarsFromFile("data/cars.csv");
    }

   
    public void addCar(Car car) {
        cars.add(car); 
        System.out.println("Car added successfully: " + car.getCarId());
        saveCarsToFile();  
    }

    
    public void viewCars() {
        if (cars.isEmpty()) {
            System.out.println("No cars available.");
        } else {
            for (Car car : cars) {
                System.out.println(car);  
            }
        }
    }

    
    public void updateCar(String carId, Car updatedCar) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getCarId().equals(carId)) {
                cars.set(i, updatedCar); 
                System.out.println("Car updated successfully: " + carId);
                saveCarsToFile();  
                return;
            }
        }
        System.out.println("Car with ID " + carId + " not found.");
    }

    public void deleteCar(String carId) {
        for (Car car : cars) {
            if (car.getCarId().equals(carId)) {
                car.setStatus("inactive"); 
                System.out.println("Car marked as inactive: " + carId);
                saveCarsToFile(); 
                return;
            }
        }
        System.out.println("Car with ID " + carId + " not found.");
    }

    public Car sellCar(String carId, LocalDate saleDate) {
        Car car = getCarById(carId);  
        if (car != null && car.getStatus().equals("available")) {
            car.setStatus("sold");
            car.setSaleDate(saleDate);  
            saveCarsToFile();  
        }
        return null;
    }
    
    private void saveCarsToFile() {
        FileHandler.saveCarsToFile("data/cars.csv", cars);  
    }
    

    public int calculateCarsSoldInMonth(int month, int year) {
        int carsSold = 0;

        for (Car car : cars) {
            LocalDate saleDate = car.getSaleDate();
            if (saleDate != null && saleDate.getMonthValue() == month && saleDate.getYear() == year) {
                carsSold++;
            }
        }
        return carsSold;
    }

     public List<Car> listCarsSoldInPeriod(String startDate, String endDate) {
        List<Car> soldCars = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        for (Car car : cars) {
         
            if ("sold".equals(car.getStatus()) && car.getSaleDate() != null) {
                LocalDate saleDate = car.getSaleDate();

                if ((saleDate.isEqual(start) || saleDate.isAfter(start)) && 
                    (saleDate.isEqual(end) || saleDate.isBefore(end))) {
                    soldCars.add(car);
                }
            }
        }

        return soldCars;
    }


    

    public Car getCarById(String carId) {
        for (Car car : cars) {
            if (car.getCarId().equals(carId)) {
                return car;
            }
        }
        return null;
    }
    
    
}


