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
}//End of class Date