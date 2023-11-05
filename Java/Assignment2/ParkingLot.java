package Assignment2;
//ParkingLot.java
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ParkingLot {
    private static ArrayList<Resident> residents = new ArrayList<>();
    private static ParkingSpot[] parkingSpots;
    private static int feeMonthly = 0;
    private static int feePerTen = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Starting ...");
        System.out.println("Enter the number of parking spots:");
        int numSpots = in.nextInt();
        System.out.println("Enter the monthly parking fee for residents:");
        feeMonthly = in.nextInt();
        System.out.println("Enter the parking fee per 10 minutes:");
        feePerTen = in.nextInt();
        
        // Initialize the parking spots array
        parkingSpots = new ParkingSpot[numSpots];
        for(int i = 0; i < numSpots; i++){
            parkingSpots[i] = new ParkingSpot();
        }

        //Read the resident data
        try {
            readResidentData();
        } catch (FileNotFoundException e){
            System.out.println("res.txt file not found.");
            return;
        }
        while (true) {
            System.out.println("Enter a command:");
            String command = in.next();
            switch (command.toLowerCase()) {
                case "a":
                    // Call method to assign a parking spot
                    assignParkingSpot(in);
                    break;
                case "w":
                    // Call method to withdraw a parking assignment
                    withdrawParking(in);
                    break;
                case "e":
                    // Call method to extend parking time
                    extendParking(in);
                    break;
                case "x":
                    // Call method to calculate parking fee
                    calculateFee(in);
                    break;
                case "s":
                    // Call method to show parking spots status
                    showParkingStatus();
                    break;
                case "i":
                    // Call method to show income
                    showIncome(in);
                    break;
                case "q":
                    // Command to quit the program
                    System.out.println("Exiting program.");
                    in.close();
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }
    private static void readResidentData() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("res.txt"));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] data = line.split(" ");
            if (data.length == 2) {
                residents.add(new Resident(data[1], data[0]));
            }
        }
        fileScanner.close();
    }
    // Placeholder methods for each command:
    private static void assignParkingSpot(Scanner in) {
        // Implement this method to handle parking spot assignment
    }

    private static void withdrawParking(Scanner in) {
        // Implement this method to handle parking withdrawal
    }

    private static void extendParking(Scanner in) {
        // Implement this method to handle parking extension
    }

    private static void calculateFee(Scanner in) {
        // Implement this method to handle fee calculation
    }

    private static void showParkingStatus() {
        // Implement this method to show parking spots status
    }

    private static void showIncome(Scanner in) {
        // Implement this method to show income from parking fees
    }
    // Inside ParkingLot.java

    // This method needs to be implemented to find a free parking spot
    private static ParkingSpot findFreeParkingSpot() {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isOccupied()) {
                return spot;
            }
        }
        return null; // If no spots are free
    }

    // This method needs to be implemented to convert input data to Date
    private static LocalDateTime convertToDate(int year, int month, int day) {
        return LocalDateTime.of(year, month, day, 0, 0);
    }

    // This method needs to be implemented to find a resident by phone number
    private static Resident findResidentByPhoneNumber(String phoneNumber) {
        for (Resident resident : residents) {
            if (resident.getPhoneNumber().equals(phoneNumber)) {
                return resident;
            }
        }
        return null; // If resident is not found
    }

    // Implementation of assignParkingSpot
    private static void assignParkingSpot(in) {
        String carPlate = in.next();
        String phoneNumber = in.next();
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();

        Resident resident = findResidentByPhoneNumber(phoneNumber);
        ParkingSpot spot = findFreeParkingSpot();

        if (spot != null && resident != null) {
            spot.occupySpot(carPlate, true, convertToDate(year, month, day).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            System.out.printf("%s차량에 주차공간(#%d 번)이 배정되었습니다!%n", carPlate, Arrays.asList(parkingSpots).indexOf(spot) + 1);
        } else {
            System.out.println("주차 공간이 없거나 거주자 정보를 찾을 수 없습니다.");
        }
    }

    // Implementation of withdrawParking
    private static void withdrawParking(in) {
        String carPlate = in.next();
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();

        // Search for the car in the parking spots
        for (int i = 0; i < parkingSpots.length; i++) {
            if (parkingSpots[i].isOccupied() && parkingSpots[i].getCarPlate().equals(carPlate)) {
                // Found the car, now withdraw
                System.out.printf("거주자 우선주차 차량 %s 가 주차공간(#%d) 배정을 철회하였습니다!%n", carPlate, i + 1);
                parkingSpots[i].freeSpot();
                return;
            }
        }
        System.out.println("해당 차량은 주차되어 있지 않습니다.");
    }
}