package Assignment2;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Scanner;

//ParkingSpot.java
class ParkingSpot {
    private boolean isOccupied;
    private String carPlate; // or another identifier for a car
    private boolean isResident;
    private long arrivalTime; // Store time of arrival for calculating parking time

    // Constructors, getters, and setters

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupySpot(String carPlate, boolean isResident, long arrivalTime) {
        this.isOccupied = true;
        this.carPlate = carPlate;
        this.isResident = isResident;
        this.arrivalTime = arrivalTime;
    }

    public void freeSpot() {
        this.isOccupied = false;
        this.carPlate = null;
        this.arrivalTime = 0;
        this.isResident = false;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public boolean isResident() {
        return isResident;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public long getParkingDuration() {
        return System.currentTimeMillis() - arrivalTime;
    }

    // Consider adding a method to calculate the fee, depending on the duration and whether the car belongs to a resident.
    public double calculateFee(long feePerTen, long feeMonthly) {
        if (this.isResident) {
            return feeMonthly;
        } else {
            long durationInMinutes = getParkingDuration() / (1000 * 60);
            return (double) ((durationInMinutes / 10) + 1) * feePerTen;
        }
    }

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
    private static void assignParkingSpot(Scanner in) {
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
    private static void withdrawParking(Scanner in) {
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

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "isOccupied=" + isOccupied +
                ", carPlate='" + carPlate + '\'' +
                ", isResident=" + isResident +
                ", arrivalTime=" + arrivalTime +
                '}';
    }

    // Add any other necessary methods
}
