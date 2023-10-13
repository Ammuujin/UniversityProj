package Employee;
// test class
public class DateTest{
    public static void main(String[] args)
    {
        System.out.println(Date.isValid(2021, 4, 13)); // true
        System.out.println(Date.isValid(2021, 2, 29)); // false
        System.out.println(Date.isValid(2020, 2, 29)); // true
        System.out.println(Date.isValid(2021, 3, 32)); // false
    }
 }