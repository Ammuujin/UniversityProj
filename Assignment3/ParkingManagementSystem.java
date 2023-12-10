package Assignment3;

import java.util.Scanner;

public class ParkingManagementSystem {
    // Assuming the following classes are already implemented and have the necessary methods
    private AssignmentHandler assignmentHandler;
    private ParkingHandler parkingHandler;
    private StatisticHandler statisticHandler;
    private ResidentManager residentManager;
    private ParkingSpotManager parkingSpotManager;
    private AssignedCatalog assignedCatalog;
    private CanceledCatalog canceledCatalog;
    private UnparkedCarCatalog unparkedCarCatalog;

    private static int parkingSpots;
    private static int monthlyParkingFee;
    private static int parkingFeePerTenMinutes;

    // Main method to start the application
    public static void main(String[] args) {
        new ParkingManagementSystem().run();
    }

    // Constructor to initialize all system components
    public ParkingManagementSystem() {
        // Initialize all components here
        parkingSpotManager = new ParkingSpotManager(numSpots);
        assignedCatalog = new AssignedCatalog(0);
        canceledCatalog = new CanceledCatalog(0);
        unparkedCarCatalog = new UnparkedCarCatalog(0);
        // Continue initializing other components like assignmentHandler, parkingHandler, etc.
    }

    // Method to run the parking management system
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Starting...");
        System.out.print("Enter the number of parking spots: ");
        parkingSpots = scanner.nextInt();
        System.out.print("Enter the monthly parking fee for residents: ");
        monthlyParkingFee = scanner.nextInt();
        System.out.print("Enter the parking fee per 10 minutes: ");
        parkingFeePerTenMinutes = scanner.nextInt();
        boolean exit = false;
        while (!exit) {
            String choice = in.nextLine()

            switch (choice) {
                case "a":
                    AssignmentHandler();
                    break;
                case 2:
                    handleCancel();
                    break;
                case 3:
                    handleResidentCarEnter();
                    break;
                case 4:
                    handleNonResidentCarEnter();
                    break;
                case 5:
                    handleCarExit();
                    break;
                case 6:
                    handleShowCarList();
                    break;
                case 7:
                    handleMonthlyIncome();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        in.close(); // Don't forget to close the scanner
    }
}