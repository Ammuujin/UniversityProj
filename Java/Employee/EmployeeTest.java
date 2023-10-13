package Employee;
import java.util.Scanner;

public class EmployeeTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isValidDate = false;
        int yy = 0, mm = 0, dd = 0;
        while (!isValidDate) {
            System.out.print("Enter year: ");
            yy = in.nextInt();
            System.out.print("Enter month: ");
            mm = in.nextInt();
            System.out.print("Enter day: ");
            dd = in.nextInt();

            if (Date.isValid(yy, mm, dd)) {
                isValidDate = true;
            } else {
                System.out.println("Invalid date input. Please try again.");
            }
        }
        Date d = new Date(yy, mm, dd);
        Employee e = new Employee("Hong", 100000.0, d);
        System.out.println(e);
    }
}
