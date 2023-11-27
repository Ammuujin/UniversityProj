package hw3;

public class UnparkedCarCatalog {
    private Car[] unparkedCars;
    private int count; // To keep track of the number of unparked cars

    public UnparkedCarCatalog(int capacity) {
        unparkedCars = new Car[capacity];
        count = 0;
    }

    // Adds a car to the catalog
    public boolean addCar(Car car) {
        if (count < unparkedCars.length) {
            unparkedCars[count] = car;
            count++;
            return true;
        }
        return false; // Catalog is full
    }

    // Retrieves the list of all unparked cars
    public Car[] getCarList() {
        Car[] currentList = new Car[count];
        System.arraycopy(unparkedCars, 0, currentList, 0, count);
        return currentList;
    }

    // Getter for the count
    public int getCount() {
        return count;
    }
}