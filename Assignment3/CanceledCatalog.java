package Assignment3;

public class CanceledCatalog {
    private RCar[] canceledCars;
    private int count; // To keep track of the number of canceled cars

    public CanceledCatalog(int capacity) {
        canceledCars = new RCar[capacity];
        count = 0;
    }

    public boolean addCar(RCar car) {
        if (count < canceledCars.length) {
            canceledCars[count] = car;
            count++;
            return true;
        }
        return false; // Catalog is full
    }

    public boolean deleteCar(String id) {
        for (int i = 0; i < count; i++) {
            if (canceledCars[i].getId().equals(id)) {
                // Shift the array elements to fill the gap left by the removed car
                System.arraycopy(canceledCars, i + 1, canceledCars, i, count - i - 1);
                canceledCars[count - 1] = null; // Nullify the last element
                count--;
                return true;
            }
        }
        return false; // Car not found to remove
    }

    public RCar[] getCarList() {
        RCar[] currentList = new RCar[count];
        System.arraycopy(canceledCars, 0, currentList, 0, count);
        return currentList;
    }

    // Getter for count
    public int getCount() {
        return count;
    }
}