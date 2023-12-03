package Assignment3;

public class ParkingHandler {
    private ParkedCarCatalog parkedCarCatalog;
    private ParkedRCarCatalog parkedRCarCatalog;
    private UnparkedCarCatalog unparkedCarCatalog;

    public ParkingHandler(int capacity) {
        this.parkedCarCatalog = new ParkedCarCatalog(capacity);
        this.parkedRCarCatalog = new ParkedRCarCatalog(capacity);
        this.unparkedCarCatalog = new UnparkedCarCatalog(capacity);
    }

    // Handles the entry of a non-resident car
    public boolean handleCarEnter(Car car) {
        return parkedCarCatalog.addCar(car);
    }

    // Handles the entry of a resident car
    public boolean handleResidentCarEnter(RCar car) {
        return parkedRCarCatalog.addCar(car);
    }

    // Handles the exit of a car
    public boolean handleCarExit(String carId) {
        Car car = parkedCarCatalog.findCar(carId);
        if (car != null) {
            parkedCarCatalog.deleteCar(carId);
            unparkedCarCatalog.addCar(car); // Adds to unparked catalog for income calculation
            return true;
        }
        
        RCar rCar = parkedRCarCatalog.findCar(carId);
        if (rCar != null) {
            parkedRCarCatalog.deleteCar(carId);
            // Assuming RCar also extends Car or they are of the same type
            return true;
        }
        return false; // Car was not found in either catalog
    }

    // Retrieves a list of all parked non-resident cars
    public Car[] getParkedCarList() {
        return parkedCarCatalog.getCarList();
    }

    // Retrieves a list of all parked resident cars
    public RCar[] getParkedRCarList() {
        return parkedRCarCatalog.getCarList();
    }
}
