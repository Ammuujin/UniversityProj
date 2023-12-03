package Assignment3;

import java.time.LocalDateTime;

public class RCar {
    private String id;
    private int spotNo;
    private LocalDateTime cancelDate; // This will store the cancellation date if applicable

    // Constructor to initialize a new RCar object
    public RCar(String id, int spotNo) {
        this.id = id;
        this.spotNo = spotNo;
    }

    // Method to calculate parking fee
    public double calcParkingFee(LocalDateTime enterTime, LocalDateTime exitTime) {
        // Check if enterTime and exitTime are valid
        if (enterTime == null || exitTime == null || exitTime.isBefore(enterTime)) {
            throw new IllegalArgumentException("Enter time must be before exit time.");
        }
        long hours = java.time.Duration.between(enterTime, exitTime).toHours();
        double feePerHour = 2.50; // Assuming a fee of $2.50 per hour
        return hours * feePerHour;
    }

    // Getter for spotNo
    public int getSpotNo() {
        return this.spotNo;
    }

    // Setter for cancelDate
    public int setSpotNo(int spotNo){
        return this.spotNo=spotNo;
    }

    //Getter for getId
    public String getId(){
        return id;
    }

    // Setter for cancelDate
    public void setCancelDate(LocalDateTime cancelDate) {
        this.cancelDate = cancelDate;
    }
}//End of the RCar.java