package Assignment2;

import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParkingLot {
    // Declaration
    private static ParkingSpot[] parkingSpots;
    private static int feeMonthly = 0;
    private static int feePerTen = 0;
    private static int totalIncome = 0;
    private static int numSpots = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Starting ...");
        System.out.println("Enter the number of parking spots:");
        numSpots = in.nextInt();
        System.out.println("Enter the monthly parking fee for residents:");
        feeMonthly = in.nextInt();
        System.out.println("Enter the parking fee per 10 minutes:");
        feePerTen = in.nextInt();

        // Initialize the parking spots array
        parkingSpots = new ParkingSpot[numSpots];
        for (int i = 0; i < numSpots; i++) {
            parkingSpots[i] = new ParkingSpot();
        }

        // check res.txt file existing
        File f = new File("src/Assignment2/res.txt");
        if (f.exists() && !f.isDirectory()) {
            System.out.println("The file res.txt exists.");
        } else {
            System.out.println("The file res.txt does not exist.");
        }
        System.out.println();

        // Procedure part
        while (true) {
            System.out.println("Parking System is working. Enter the command: ");
            String command = in.next();
            switch (command.toLowerCase()) {
                case "a":
                    String inputA = in.nextLine().trim();
                    assignResidents(inputA);
                    break;
                case "w":
                    String inputW = in.nextLine().trim();
                    wipeOutResidents(inputW);
                    break;
                case "e":
                    // String enterInput = in.nextLine().trim();
                    // enter(enterInput);
                    break;
                case "x":
                    // calculate();
                    break;
                case "s":
                    // statusPS();
                    break;
                case "i":
                    // income();
                    break;
                default:
                    System.out.println("Invalid input!");
            }
            System.out.println();
        }
    }

    // Method to count available parking spots
    private static int getAvailableSpotsCount() {
        int count = 0;
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    // Assign Parking Spot method
    private static void assignResidents(String input) {
        // Split the input string by spaces to get the individual parts
        String[] parts = input.split(" ");
        // Assuming the input format is: a carNumber phoneNumber year month day
        String carNumber = parts[0];
        String phoneNumber = parts[1];
        // Validate the car number to be a 4-digit number
        if (!carNumber.matches("\\d{4}")) {
            System.out.println("Error: Car number must be in 4 digits.");
            return;
        }
        int year = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[3]);
        int day = Integer.parseInt(parts[4]);
        // Construct the assignment date in the proper format
        LocalDate assignmentDate = LocalDate.of(year, month, day);
        String formattedDate = assignmentDate.format(DateTimeFormatter.ofPattern("yyyy MM dd"));
        boolean spotAssigned = false;
        for (int i = 0; i < parkingSpots.length && !spotAssigned; i++) {
            // Check if the spot is available
            if (parkingSpots[i].isAvailable()) {
                parkingSpots[i].assignToResident(carNumber, phoneNumber, formattedDate);
                System.out.println(carNumber + "차량에 주차공간(#" + (i + 1) + " 번)이 배정되었습니다!");
                System.out.println("Remaining parking spots: " + getAvailableSpotsCount());
                spotAssigned = true;
            }
        }
        if (!spotAssigned) {
            System.out.println("No available parking spots.");
        }

    // Method to wipe out resident parking assignments
    private static void wipeOutResidents(String input) {
        // Split the input string by spaces to get the individual parts
        String[] parts = input.split(" ");
        // Corrected indices based on the input format: w carNumber year month day
        String carNumber = parts[0];
        int year = Integer.parseInt(parts[1]);
        int month = Integer.parseInt(parts[2]);
        int day = Integer.parseInt(parts[3]);

        // Construct the withdrawal date as a LocalDate object
        LocalDate withdrawalDate = LocalDate.of(year, month, day);

        boolean foundCar = false;
        for (int i = 0; i < parkingSpots.length; i++) {
            // Check if the current spot has the car number
            if (parkingSpots[i].getAssignedCarNumber() != null
                    && parkingSpots[i].getAssignedCarNumber().equals(carNumber)) {
                // Compare LocalDate objects directly
                if (parkingSpots[i].getAssignmentDate() != null
                        && parkingSpots[i].getAssignmentDate().isEqual(withdrawalDate)) {
                    // Clear the assignment
                    parkingSpots[i].clearAssignment();
                    System.out.println("거주자 우선주차 차량 " + carNumber + " 가 주차공간(#" + (i + 1) + ") 배정을 철회하였습니다!");
                    foundCar = true;
                    break;
                }
            }
        }

        // If the car number was not found or the date did not match
        if (!foundCar) {
            System.out.println("Error: Car number " + carNumber + " with given date not found.");
        }
    }
}