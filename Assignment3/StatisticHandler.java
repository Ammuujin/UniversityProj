package Assignment3;

public class StatisticHandler {
    private AssignedCatalog assignedCatalog;
    private UnparkedCarCatalog unparkedCarCatalog;
    private double monthlyResidentRate;
    private double nonResidentRatePerTenMinutes;

    // Constructor
    public StatisticHandler(AssignedCatalog assignedCatalog, UnparkedCarCatalog unparkedCarCatalog, 
                            double monthlyResidentRate, double nonResidentRatePerTenMinutes) {
        this.assignedCatalog = assignedCatalog;
        this.unparkedCarCatalog = unparkedCarCatalog;
        this.monthlyResidentRate = monthlyResidentRate;
        this.nonResidentRatePerTenMinutes = nonResidentRatePerTenMinutes;
    }

    // Method to handle the display of monthly income
    public void handleMonthlyIncome() {
        double income = calcMonthlyIncome();
        System.out.println("Total monthly income: $" + income);
    }

    // Method to calculate the monthly income
    private double calcMonthlyIncome() {
        // Calculate income from resident cars
        double incomeFromResidents = assignedCatalog.getCount() * monthlyResidentRate;

        // Calculate income from non-resident cars
        double incomeFromNonResidents = 0.0;
        Car[] cars = unparkedCarCatalog.getCarList();
        for (Car car : cars) {
            // Calculate the total time the car was parked in minutes
            long parkedTimeInMinutes = car.getParkingDurationInMinutes();
            incomeFromNonResidents += (parkedTimeInMinutes / 10) * nonResidentRatePerTenMinutes;
        }

        return incomeFromResidents + incomeFromNonResidents;
    }
}
