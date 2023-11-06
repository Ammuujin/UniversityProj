package Assignment2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ParkingLot {
    // Declaration
    private static ParkingSpot[] parkingSpots;
    private static int feeMonthly = 0;
    private static int feePerTen = 0;
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
                    String enterInput = in.nextLine().trim();
                    enter(enterInput);
                    break;
                case "x":
                    String leaveInput = in.nextLine().trim();
                    leave(leaveInput);
                    break;
                case "s":
                    statusPS();
                    break;
                case "i":
                    String incomeInput = in.nextLine().trim();
                    income(incomeInput);
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

    // Method to check if a car number belongs to a resident
    private static boolean isResidentCarNumber(String carNumber) {
        try {
            File file = new File("src/Assignment2/res.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // If the car number is the second word in the line.
                String[] lineParts = line.split(" ");
                if (lineParts.length > 1 && lineParts[1].equals(carNumber)) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: The resident file was not found.");
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return false;
    }

    private static void enter(String enterInput) {
        String[] parts = enterInput.split(" ");
        // Assuming the first part is 'e' for enter command, followed by car number and
        // optional phone number.
        String carNumber = parts[1];
        String phoneNumber = parts.length == 7 ? null : parts[2]; // phone number is present only for non-residents.

        // Parse the date and time from the input.
        int year = Integer.parseInt(parts[parts.length - 5]);
        int month = Integer.parseInt(parts[parts.length - 4]);
        int day = Integer.parseInt(parts[parts.length - 3]);
        int hour = Integer.parseInt(parts[parts.length - 2]);
        int minute = Integer.parseInt(parts[parts.length - 1]);

        // Create date and time objects.
        LocalDate date = LocalDate.of(year, month, day);
        LocalTime time = LocalTime.of(hour, minute);

        // Determine if it's a resident or non-resident based on the presence of a phone
        // number.
        boolean isResident = phoneNumber == null;

        // Iterate over parking spots to find an available one or a reserved spot for a
        // resident vehicle.
        for (int i = 0; i < parkingSpots.length; i++) {
            ParkingSpot spot = parkingSpots[i];

            if (isResident && spot.isReservedFor(carNumber)) {
                // For resident, if the spot is reserved for this car, mark it as occupied.
                spot.markAsOccupied();
                System.out.println("거주자 우선 주차차량 " + carNumber + "가 입차하였습니다!");
                return;
            } else if (!isResident && spot.isAvailable()) {
                // For non-resident, assign the first available spot.
                spot.assignToNonResident(carNumber, phoneNumber, date, time);
                spot.markAsOccupied(); // Mark the spot as occupied.
                System.out.println("일반 차량 " + carNumber + "이 입차하였습니다! 주차공간 " + (i + 1) + "번이 배정되었습니다!");
                return;
            }
        }

        System.out.println("Error: No available parking spots.");
    }

    // Method for a car leaving the parking lot
    private static void leave(String leaveInput) {
        // Parse the input string
        String[] parts = leaveInput.split(" ");
        String carNumber = parts[0];
        int year = Integer.parseInt(parts[1]);
        int month = Integer.parseInt(parts[2]);
        int day = Integer.parseInt(parts[3]);
        int hour = Integer.parseInt(parts[4]);
        int minute = Integer.parseInt(parts[5]);

        // Construct the leaving date and time
        LocalDate leaveDate = LocalDate.of(year, month, day);
        LocalTime leaveTime = LocalTime.of(hour, minute);

        // Loop through the parking spots to find the car
        for (ParkingSpot spot : parkingSpots) {
            // Check if the current spot has the car and is occupied
            if (spot.getAssignedCarNumber() != null && spot.getAssignedCarNumber().equals(carNumber)
                    && spot.isOccupied()) {
                // Calculate parking duration manually in minutes
                long minutesParked = Duration.between(
                        LocalDateTime.of(spot.getAssignmentDate(), spot.getAssignmentTime()),
                        LocalDateTime.of(leaveDate, leaveTime)).toMinutes();

                // If the car is not a resident, calculate the fee
                int fee = 0;
                if (!spot.isResidentOccupying()) { // Changed method call to isResidentOccupying
                    fee = (int) ((minutesParked / 10) * feePerTen);
                }

                // Output the car number, parking time and fee
                System.out.println("일반 차량 " + carNumber + "이 출차하였습니다!");
                System.out.println("주차시간: " + minutesParked + "분");
                System.out.println("주차요금: " + fee + "원");

                // Free the parking spot
                spot.clearAssignment();
                break;
            }
        }
    }

    private static void statusPS() {
        ArrayList<String> residentInfo = new ArrayList<>();
        ArrayList<String> nonResidentInfo = new ArrayList<>();

        // Collect data for all parked cars
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isOccupied()) {
                String info = spot.getParkingSpotNumber() + " " + spot.getAssignedCarNumber() + " "
                        + spot.getContactNumber();
                if (spot.isResidentOccupying()) {
                    // Assuming you have a method to get resident's name and the assignment date
                    info += " " + spot.getResidentName() + " " + spot.getAssignmentDateFormatted();
                    residentInfo.add(info);
                } else {
                    // For non-residents, include the time they entered
                    info += " " + spot.getAssignmentDateFormatted() + " " + spot.getAssignmentTimeFormatted();
                    nonResidentInfo.add(info);
                }
            }
        }

        // Sort the lists based on the car number
        Comparator<String> byCarNumber = Comparator.comparing(s -> s.split(" ")[1]);
        residentInfo.sort(byCarNumber);
        nonResidentInfo.sort(byCarNumber);

        // Print out the resident information
        System.out.println("거주자 우선주차 차량:");
        for (String info : residentInfo) {
            System.out.println(info);
        }

        // Print out the non-resident information
        System.out.println("일반 차량:");
        for (String info : nonResidentInfo) {
            System.out.println(info);
        }
    }

    // Method to calculate income for a specific month and year
    private static void income(String incomeInput) {
        String[] parts = incomeInput.split(" ");
        int year = Integer.parseInt(parts[1]);
        int month = Integer.parseInt(parts[2]);

        // Variables to hold income from residents and non-residents
        int incomeFromResidents = 0;
        int incomeFromNonResidents = 0;

        // Lists to keep track of resident car income details
        List<String> residentCarDetails = new ArrayList<>();

        // Iterate over all parking spots to calculate income
        for (ParkingSpot spot : parkingSpots) {
            // Check if the spot has an assigned car and if the assignment date matches the
            // input month and year
            if (spot.getAssignedCarNumber() != null && spot.getAssignmentDate() != null) {
                LocalDate assignmentDate = spot.getAssignmentDate();
                if (assignmentDate.getYear() == year && assignmentDate.getMonthValue() == month) {
                    if (spot.isResidentOccupying()) {
                        // Add monthly fee for residents
                        incomeFromResidents += feeMonthly;
                        residentCarDetails.add(spot.getAssignedCarNumber() + ": " + feeMonthly + "원");
                    } else {
                        // Calculate the income from non-residents based on parked duration
                        long minutesParked = Duration.between(
                                LocalDateTime.of(assignmentDate, spot.getAssignmentTime()),
                                LocalDateTime.now() // Assuming current time for the sake of this example
                        ).toMinutes();
                        int fee = (int) ((minutesParked / 10) * feePerTen);
                        incomeFromNonResidents += fee;
                    }
                }
            }
        }

        // Output the total income
        System.out
                .println("총수입 " + year + "년 " + month + "월 원: " + (incomeFromResidents + incomeFromNonResidents) + "원");
        System.out.println("거주자 우선주차 차량: " + incomeFromResidents + " 원");
        for (String detail : residentCarDetails) {
            System.out.println(detail);
        }
        System.out.println("일반 차량: " + incomeFromNonResidents + " 원");
    }
}