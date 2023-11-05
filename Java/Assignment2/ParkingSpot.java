package Assignment2;
//ParkingSpot.java
class ParkingSpot {
    private boolean isOccupied;
    private String carPlate; // or another identifier for a car
    private boolean isResident;
    private long arrivalTime; // Store time of arrival for calculating parking time

    public ParkingSpot() {
        this.isOccupied = false;
        this.carPlate = null;
        this.isResident = false;
        this.arrivalTime = 0;
    }

    // Methods to handle arrival, departure, and status checks
}
