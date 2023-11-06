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
                    String input = in.nextLine().trim();
                    assignPS(input);
                    break;
                case "w":
                    // withdrawPS();
                    break;
                case "e":
                    // extend();
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
            if (!spot.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    // Assign Parking Spot method
    private static void assignPS(String input) {
        // Split the input string by spaces to get the individual parts
        String[] parts = input.split(" ");
        // Assuming the input format is: a carNumber phoneNumber year month day
        String carNumber = parts[0];
        System.out.println("CarNumber: "+carNumber);
        String phoneNumber = parts[1];
        System.out.println("Phone: "+phoneNumber);
        // Validate the carNumber to be a 4-digit number
        if (!carNumber.matches("\\d{4}")) {
            System.out.println("Error: Car number must be in 4 digits.");
            return;
        }
        int year = Integer.parseInt(parts[2]);
        System.out.println("year: "+year);
        int month = Integer.parseInt(parts[3]);
        System.out.println("month: "+month);
        int day = Integer.parseInt(parts[4]);
        System.out.println("day: "+ day);
        // Construct the assignment date in the proper format
        LocalDate assignmentDate = LocalDate.of(year, month, day);
        String formattedDate = assignmentDate.format(DateTimeFormatter.ofPattern("yyyy MM dd"));
        boolean spotAssigned = false;
        for (int i = 0; i < parkingSpots.length && !spotAssigned; i++) {
            // Check if the carNumber is already assigned
            if (parkingSpots[i].getResidentCarNumber() != null && parkingSpots[i].getResidentCarNumber().equals(carNumber)) {
                System.out.println("Error: This car number is already assigned to a parking spot.");
                return;
            }
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
    }
}