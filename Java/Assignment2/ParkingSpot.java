package Assignment2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class ParkingSpot {
    private boolean isOccupied;
    private String residentCarNumber;
    private String residentPhoneNumber;
    private LocalDate assignmentDate;
    public ParkingSpot() {
        // When a parking spot is created, it's initially not occupied
        this.isOccupied = false;
    }
    public boolean isAvailable() {
        return !isOccupied;
    }
    public String getResidentCarNumber() {
        return residentCarNumber;
    }
    public void assignToResident(String carNumber, String phoneNumber, String formattedDate) {
        this.residentCarNumber = carNumber;
        this.residentPhoneNumber = phoneNumber;
        try {
            this.assignmentDate = LocalDate.parse(formattedDate.trim(), DateTimeFormatter.ofPattern("yyyy MM dd"));
            this.isOccupied = true;
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format provided.");
        }
    }
}
