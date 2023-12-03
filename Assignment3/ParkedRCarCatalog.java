package Assignment3;

public class ParkedRCarCatalog {
    private RCar[] parkedRCars;
    private int count; // To keep track of the number of parked resident cars

    public ParkedRCarCatalog(int capacity) {
        parkedRCars = new RCar[capacity];
        count = 0;
    }

    // Adds a resident car to the catalog
    public boolean addCar(RCar car) {
        if (count < parkedRCars.length) {
            parkedRCars[count] = car;
            count++;
            return true;
        }
        return false; // Catalog is full
    }

    // Deletes a resident car from the catalog by its ID
    public boolean deleteCar(String id) {
        for (int i = 0; i < count; i++) {
            if (parkedRCars[i].getId().equals(id)) {
                System.arraycopy(parkedRCars, i + 1, parkedRCars, i, count - i - 1);
                parkedRCars[count - 1] = null; // Nullify the last element
                count--;
                return true;
            }
        }
        return false; // Car not found
    }

    // Retrieves the list of all parked resident cars
    public RCar[] getCarList() {
        RCar[] currentList = new RCar[count];
        System.arraycopy(parkedRCars, 0, currentList, 0, count);
        return currentList;
    }

    // Searches for a resident car by its ID
    public RCar findCar(String id) {
        for (int i = 0; i < count; i++) {
            if (parkedRCars[i].getId().equals(id)) {
                return parkedRCars[i];
            }
        }
        return null; // Car not found
    }

    // Getter for the count
    public int getCount() {
        return count;
    }
}