package Assignment2;

import java.util.Scanner;

public class ParkingLot {
    public static void main(String[] args) {
        int numSpots = 0;
        int feeMonthly = 0;
        int feePerTen = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Starting ...");
        System.out.println("Enter the number of parking spots : ");
        numSpots = in.nextInt();
        System.out.println("Enter the monthly parking fee for residents : ");
        feeMonthly = in.nextInt();
        System.out.println("Enter the parking fee per 10 minutes : ");
        feePerTen = in.nextInt();
    }
}
