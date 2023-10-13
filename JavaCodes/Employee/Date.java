package Employee;
//Date class
public class Date {
    private int year;
    private int month;
    private int day;
    public Date(int yy, int mm, int dd) {
        year = yy; month = mm; day = dd;
    }
    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDay() { return day; }
    public void setDate(Date d) {
        year = d.getYear();
        month = d.getMonth();
        day = d.getDay();
    }
    public String toString() { return year + "-" + month + "-" + day; }
    public static boolean isValid(int yy, int mm, int dd) {
        if (yy < 0 || mm < 1 || mm > 12 || dd < 1 || dd > 31) return false;
        if (mm == 4 || mm == 6 || mm == 9 || mm == 11) return (dd <= 30);
        if (mm == 2) return (dd <= 28 || (dd == 29 && isLeapYear(yy)));
        return true;
    }
    public static boolean isLeapYear(int yy) {
        return (yy % 4 == 0 && yy % 100 != 0) || (yy % 400 == 0);
    }
}//End of class Date