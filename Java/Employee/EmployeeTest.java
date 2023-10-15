package Employee;

import java.util.Scanner;
// test class
public class EmployeeTest{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int yy = in.nextInt();
        int mm = in.nextInt();
        int dd = in.nextInt();
        Date d = new Date(yy, mm, dd);
        Employee e = new Employee("Hong", 100000.0, d);
        System.out.println(e);
    }
}