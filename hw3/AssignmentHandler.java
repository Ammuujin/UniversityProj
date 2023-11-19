package hw3;

public class AssignmentHandler {
    private AssignedCatalog assignedCatalog; // This catalog keeps track of assigned cars
    private ParkingSpotManager parkingSpotManager; // This manages the parking spots

    // Constructor might take other parameters as needed, e.g., capacity
    public AssignmentHandler(int capacity, ParkingSpotManager parkingSpotManager) {
        this.assignedCatalog = new AssignedCatalog(capacity);
        this.parkingSpotManager = parkingSpotManager; // Use the provided ParkingSpotManager
    }

    // Handles the assignment of a parking spot to a resident car
    public boolean handleAssignment(RCar car, int spotNumber) {
        if (parkingSpotManager.assignResidentSpot(spotNumber)) {
            car.setSpotNo(spotNumber); // Assume RCar has a setSpotNo method
            return assignedCatalog.addCar(car);
        }
        return false;
    }

    // Searches for a car that has been assigned a parking spot
    public RCar searchAssignedCar(String carId) {
        return assignedCatalog.searchCar(carId);
    }

    // Gets a list of all cars that have been assigned a parking spot
    public RCar[] getRCarList() {
        return assignedCatalog.getCarList();
    }
}
