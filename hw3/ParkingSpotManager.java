package hw3;

public class ParkingSpotManager {
    private boolean[] spotsOccupied; // True if spot is occupied, false if available
    private int count; // Number of spots currently occupied

    public ParkingSpotManager(int N) {
        this.spotsOccupied = new boolean[N];
        this.count = 0;
    }

    public boolean assignResidentSpot(int spotNumber) {
        if (spotNumber >= 1 && spotNumber <= spotsOccupied.length && !spotsOccupied[spotNumber - 1]) {
            spotsOccupied[spotNumber - 1] = true;
            count++;
            return true;
        }
        return false;
    }

    public boolean cancelResidentSpot(int spotNumber) {
        if (spotNumber >= 1 && spotNumber <= spotsOccupied.length && spotsOccupied[spotNumber - 1]) {
            spotsOccupied[spotNumber - 1] = false;
            count--;
            return true;
        }
        return false;
    }

    // Assuming parkResident would simply mark the spot as occupied
    public boolean parkResident(int spotNumber) {
        // This can be similar to assignResidentSpot if parking directly assigns the spot
        return assignResidentSpot(spotNumber);
    }

    public boolean returnSpot(int spotNumber) {
        if (spotNumber >= 1 && spotNumber <= spotsOccupied.length && spotsOccupied[spotNumber - 1]) {
            spotsOccupied[spotNumber - 1] = false;
            count--;
            return true;
        }
        return false;
    }

    // Optionally, if you need to track assignments for non-residents as well:
    public boolean assignSpot(int spotNumber) {
        // This could be the same as assignResidentSpot if there's no distinction in assignment between residents and non-residents
        return assignResidentSpot(spotNumber);
    }
}