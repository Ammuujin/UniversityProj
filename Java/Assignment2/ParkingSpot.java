package Assignment2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParkingSpot {
    private boolean isOccupied;
    private String carNumber;
    private String phoneNumber;
    private LocalDate assignmentDate;
    private boolean isResident; // This field indicates if the spot is taken by a resident

    public ParkingSpot() {
        this.isOccupied = false;
        this.isResident = false;
    }

    public String getAssignedCarNumber() {
        return this.carNumber;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public LocalDate getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(LocalDate assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean isResidentOccupying() {
        return isOccupied && isResident;
    }

    public void assignToResident(String carNumber, String phoneNumber, String formattedDate) {
        assign(carNumber, phoneNumber, formattedDate, true);
    }

    public void assignToNonResident(String carNumber, String phoneNumber, String formattedDate) {
        assign(carNumber, phoneNumber, formattedDate, false);
    }

    // This method clears the assignment
    public void clearAssignment() {
        this.isOccupied = false;
        this.carNumber = null;
        this.phoneNumber = null;
        this.assignmentDate = null;
        this.isResident = false;
    }

    // Private helper method to avoid duplication of the assignment code
    private void assign(String carNumber, String phoneNumber, String formattedDate, boolean isResident) {
        this.carNumber = carNumber;
        this.phoneNumber = phoneNumber;
        try {
            this.assignmentDate = LocalDate.parse(formattedDate.trim(), DateTimeFormatter.ofPattern("yyyy MM dd"));
            this.isOccupied = true;
            this.isResident = isResident;
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format provided.");
        }
    }
}
