package Assignment2;
//ParkingLot.java
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;

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
}