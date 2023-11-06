package Assignment2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParkingSpot {
    private boolean isOccupied;
    private String carNumber;
    private LocalDate assignmentDate;
    private LocalTime assignmentTime;
    private boolean isResident; // This field indicates if the spot is taken by a resident
    private String phoneNumber;
    private int parkingSpotNumber;
    private String residentName;

    public ParkingSpot() {
        this.isOccupied = false;
        this.isResident = false;
    }

    public void markAsOccupied() {
        this.isOccupied = true;
    }

    public boolean isReservedFor(String carNumber) {
        return this.isResident && this.carNumber.equals(carNumber);
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

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
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

    public int getParkingSpotNumber() {
        return this.parkingSpotNumber;
    }

    public String getContactNumber() {
        return this.phoneNumber;
    }

    public String getResidentName() {
        // This method would need to retrieve the resident's name.
        // How this is done depends on where the resident information is stored.
        return this.residentName;
    }

    public String getAssignmentDateFormatted() {
        if (this.assignmentDate != null) {
            return this.assignmentDate.format(DateTimeFormatter.ofPattern("yyyy MM dd"));
        }
        return "";
    }

    public String getAssignmentTimeFormatted() {
        if (this.assignmentTime != null) {
            return this.assignmentTime.format(DateTimeFormatter.ofPattern("HH mm"));
        }
        return "";
    }

    // For assigning to residents
    public void assignToResident(String carNumber) {
        this.carNumber = carNumber;
        this.isOccupied = true;
        this.isResident = true;
    }

    // For assigning to non-residents
    public void assignToNonResident(String carNumber, String phoneNumber, LocalDate assignmentDate,
            LocalTime assignmentTime) {
        this.carNumber = carNumber;
        this.phoneNumber = phoneNumber;
        this.assignmentDate = assignmentDate;
        this.assignmentTime = assignmentTime;
        this.isOccupied = true;
        this.isResident = false;
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

    public LocalTime getAssignmentTime() {
        return assignmentTime;
    }

    public void setAssignmentTime(LocalTime assignmentTime) {
        this.assignmentTime = assignmentTime;
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
