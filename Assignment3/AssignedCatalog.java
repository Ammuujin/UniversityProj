package Assignment3;

public class AssignedCatalog {
    private RCar[] assignedCars;
    private int count; // To keep track of the actual number of assigned cars

    public AssignedCatalog(int capacity) {
        assignedCars = new RCar[capacity];
        count = 0;
    }

    public boolean addCar(RCar car) {
        if (count < assignedCars.length) {
            assignedCars[count] = car;
            count++;
            return true;
        }
        return false; // Catalog is full
    }

    public boolean deleteCar(String id) {
        for (int i = 0; i < count; i++) {
            if (assignedCars[i].getId().equals(id)) {
                // Shift the array elements to fill the gap left by the removed car
                System.arraycopy(assignedCars, i + 1, assignedCars, i, count - i - 1);
                assignedCars[count - 1] = null; // Nullify the last element
                count--;
                return true;
            }
        }
        return false; // Car not found to remove
    }

    public RCar[] getCarList() {
        RCar[] currentList = new RCar[count];
        System.arraycopy(assignedCars, 0, currentList, 0, count);
        return currentList;
    }

    public RCar searchCar(String id) {
        for (int i = 0; i < count; i++) {
            if (assignedCars[i].getId().equals(id)) {
                return assignedCars[i];
            }
        }
        return null; // Car not found
    }

    // Getter for count
    public int getCount() {
        return count;
    }
}
