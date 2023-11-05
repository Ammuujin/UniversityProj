package Assignment2;

class ParkingSpot {
    private boolean isOccupied;
    private String carPlate; // or another identifier for a car
    private boolean isResident;
    private long arrivalTime; // Store time of arrival for calculating parking time

    // Constructors, getters, and setters

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupySpot(String carPlate, boolean isResident, long arrivalTime) {
        this.isOccupied = true;
        this.carPlate = carPlate;
        this.isResident = isResident;
        this.arrivalTime = arrivalTime;
    }

    public void freeSpot() {
        this.isOccupied = false;
        this.carPlate = null;
        this.arrivalTime = 0;
        this.isResident = false;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public boolean isResident() {
        return isResident;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public long getParkingDuration() {
        return System.currentTimeMillis() - arrivalTime;
    }

    // Consider adding a method to calculate the fee, depending on the duration and whether the car belongs to a resident.
    public double calculateFee(long feePerTen, long feeMonthly) {
        if (this.isResident) {
            return feeMonthly;
        } else {
            long durationInMinutes = getParkingDuration() / (1000 * 60);
            return (double) ((durationInMinutes / 10) + 1) * feePerTen;
        }
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "isOccupied=" + isOccupied +
                ", carPlate='" + carPlate + '\'' +
                ", isResident=" + isResident +
                ", arrivalTime=" + arrivalTime +
                '}';
    }

    // Add any other necessary methods
}
