package hw3;

import java.time.Duration;
import java.time.LocalDateTime;

public class Car {
    private String id;
    private String phoneNo;
    private int spotNo;
    private LocalDateTime enterTime;
    private LocalDateTime exitTime;

    // Constructor to initialize a new Car object
    public Car(String id, String phoneNo, int spotNo) {
        this.id = id;
        this.phoneNo = phoneNo;
        this.spotNo = spotNo;
    }

    public double calcParkingFee(double feePerHour) {
        // Check if enterTime and exitTime are set
        if (enterTime == null || exitTime == null) {
            throw new IllegalStateException("Enter and exit time must be set before calculating the fee.");
        }

        // Calculate the duration in hours
        long hours = Duration.between(enterTime, exitTime).toHours();
        return hours * feePerHour;
    }

    // Calculates the parking duration in minutes
    public long getParkingDurationInMinutes() {
        if (enterTime == null || exitTime == null) {
            throw new IllegalStateException("Enter and exit time must be set before calculating the duration.");
        }
        return Duration.between(enterTime, exitTime).toMinutes();
    }

    public void setEnterTime(LocalDateTime enterTime) {
        this.enterTime = enterTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    // Getter for spotNo
    public int getSpotNo() {
        return this.spotNo;
    }

    // Getter for CarId
    public String getId() {
        return this.id;
    }

    // Other getters and setters as needed
    // ...
}
