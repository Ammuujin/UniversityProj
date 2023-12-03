package Assignment3;

public class ParkedCarCatalog {
    private Car[] parkedCars;
    private int count; // To keep track of the number of parked cars

    public ParkedCarCatalog(int capacity) {
        parkedCars = new Car[capacity];
        count = 0;
    }

    // Adds a car to the catalog
    public boolean addCar(Car car) {
        if (count >= parkedCars.length) {
            return false; // Catalog is full
        }
        parkedCars[count] = car;
        count++;
        return true;
    }

    // Deletes a car from the catalog by its ID
    public boolean deleteCar(String id) {
        for (int i = 0; i < count; i++) {
            if (parkedCars[i].getId().equals(id)) {
                System.arraycopy(parkedCars, i + 1, parkedCars, i, count - i - 1);
                parkedCars[count - 1] = null; // Nullify the last element
                count--;
                return true;
            }
        }
        return false; // Car not found
    }

    // Retrieves the list of all parked cars
    public Car[] getCarList() {
        Car[] currentList = new Car[count];
        System.arraycopy(parkedCars, 0, currentList, 0, count);
        return currentList;
    }

    // Searches for a car by its ID
    public Car findCar(String id) {
        for (int i = 0; i < count; i++) {
            if (parkedCars[i].getId().equals(id)) {
                return parkedCars[i];
            }
        }
        return null; // Car not found
    }

    // Getter for the count
    public int getCount() {
        return count;
    }
}