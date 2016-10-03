/**
 * create a class named Date
 */
public class Date {
    // instance variables - within class but outside of method
    private int month;
    private int days;
    private int year;

    //default constructor
    public Date(){
        month = 1;
        days = 1;
        year = 1111;
    }

    // parameter constructor - method that is the same name as the class
    public Date(Integer m, Integer d, Integer y){
        month = m;
        days = d;
        year = y;
    }

    /**
     * Set methods
     * @param m
     */
    public void setDate(int m, int d, int y){
        setMonth(m);
        setDays(d);
        setYear(y);
    }
    public void setMonth(int m){
        month = m;
    }
    public void setDays(int d){
        days = d;
    }
    public void setYear(int y){
        year = y;
    }

    /**
     * get methods
     * @return
     */
    public int getMonth(){
        return month;
    }
    public int getDays(){
        return days;
    }
    public int getYear(){
        return year;
    }


    /**
     * checks to see if the the dates are valid
     * @param m
     * @param d
     * @param y
     * @return
     */
    public int validate(int m, int d, int y){
        /** days with 31 days are January, March, May, July, August, October, December [1, 3, 5, 7 ,8, 10 ,12]
         *  days with 30 days are April, June, September, November [4, 6, 9, 11]
         *  February has 28 days in a month and 29 days on leap year (every 4 years)
         */

        Integer[] thirty1 = {1, 3, 5, 7 ,8, 10 ,12}; // months with 31 days
        Integer[] thirty = {4, 6, 9, 11}; // months with 30 days
        Integer count = 0;
        Integer i = 0;

        if ( (m > 0) && (m <= 12) && (d > 0) && (d <= 31) && (y > 0) ) {
            // months with 31 days
            while (i < 7) {

                if ((m == thirty1[i]) && (d <= 31)) {
                    count++;
                }
                i++;
            }
            i = 0;
            // months with 30 days
            while (i < 4){
                if ((m == thirty[i]) && (d <= 30)) {
                    count++;
                }
                i++;
            }
            // February including leap year
            if ((m == 2 && days <= 28) || (m == 2 && days <= 29 && y%4 == 0)) {
                count++;
            }
            if (y%100 == 0 && y%400 != 0) {
                count = 0;
            }
        }

        return count;
    }

    public static void daysBetween(Date oldDate, Date newDate) {
        System.out.printf("Days between two days: ");
        System.out.printf("[%d-%d-%d] and ", oldDate.month, oldDate.days, oldDate.year);
        System.out.printf("[%d-%d-%d] : ", newDate.month, newDate.days, newDate.year);

        /** days with 31 days are January, March, May, July, August, October, December [1, 3, 5, 7 ,8, 10 ,12]
         *  days with 30 days are April, June, September, November [4, 6, 9, 11]
         *  February has 28 days in a month and 29 days on leap year (every 4 years)
         */

        Integer[] months1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        Integer[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30 , 31, 30, 31}; // [0, 1, 2, 3, 4, 5,.....11]
        Integer[] leapDaysInMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30 , 31, 30, 31}; // [0, 1, 2, 3, 4, 5,.....11]
        Integer i = 0;
        Integer temp = 0;

        Integer daysBetween1;
        Integer totalNewDays, totalOldDays;

        /**
         * total days since year 0
         */
        totalNewDays = (newDate.year -1) * 365;
        totalOldDays = (oldDate.year -1)* 365;
        /**
         * Leap years
         */
        // newDate
        // adds another day to each increment of 4 but not increment of 400
        while (i < newDate.year) {
            if ((i%4 == 0 && i%100 != 0) || (i == 0)){
                totalNewDays++;
            }
            i++;
        }

        // oldDate
        // adds another day to each increment of 4 but not increment of 400
        i = 0;
        while (i < oldDate.year) {
            if ((i%4 == 0 && i%100 != 0) || (i == 0)){
                totalOldDays++;
            }
            i++;
        }

        /**
         * adding days to the total days
         */
        // New Date
        temp = 0;
        if ((newDate.year%4 == 0 && newDate.year%100 != 0) || (newDate.year%400 == 0)) {
            for (i = 0; i < newDate.month - 1; i++) {
                totalNewDays = leapDaysInMonth[i] + totalNewDays;
            }
        }
        else {
            for (i = 0; i < newDate.month - 1; i++) {
                totalNewDays = daysInMonth[i] + totalNewDays;
            }
        }
        totalNewDays += newDate.days; // adding the rest of the days

        // Old Date
        temp = 0;
        if ((oldDate.year%4 == 0 && oldDate.year%100 != 0) || (oldDate.year%400 == 0)) {
            for (i = 0; i < oldDate.month - 1; i++) {
                totalOldDays = leapDaysInMonth[i] + totalOldDays;
            }
        }
        else {
            for (i = 0; i < oldDate.month - 1; i++) {
                totalOldDays = daysInMonth[i] + totalOldDays;
            }
        }
        totalOldDays += oldDate.days; // adding the rest of the days

        daysBetween1 = Math.abs(totalNewDays - totalOldDays)-1; // absolute value

        System.out.println(daysBetween1);
    }

    /**
     * prints outs True if valid and False if invalid
     * @param m
     * @param d
     * @param y
     */
    public void isValid(Integer m, Integer d, Integer y){

        Integer count = validate(m, d, y);

        // validating
        if (count > 0){
            System.out.println("True"); // valid date
        }
        else{
            System.out.println("False"); // invalid date
        }
    }

    public String toString(){
        return String.format("%d/%d/%d", month, days, year);
    }

    /**
     * Main method
     */
    public static void main(String[] args){
        // sets the date
        Date dateObject1 = new Date();
        dateObject1.setMonth(13);
        dateObject1.setDays(31);
        dateObject1.setYear(2011);


        Date defaults = new Date();
        dateObject1.isValid(dateObject1.month, dateObject1.days, dateObject1.year);



        Date dateObject2 = new Date(3, 31, 2011);
        Date dateObject3 = new Date(4, 31, 2012);
        Date dateObject4 = new Date(2, 29, 2016);
        Date dateObject5 = new Date(2, 29, 1500);
        Date dateObject6 = new Date(2, 29, 1600);


        // check to see if the dates are valid
        //dateObject1.isValid(dateObject1.month, dateObject1.days, dateObject1.year);
        dateObject2.isValid(dateObject2.month, dateObject2.days, dateObject2.year);
        dateObject3.isValid(dateObject3.month, dateObject3.days, dateObject3.year);
        dateObject4.isValid(dateObject4.month, dateObject4.days, dateObject4.year);
        dateObject5.isValid(dateObject5.month, dateObject5.days, dateObject5.year);
        dateObject6.isValid(dateObject6.month, dateObject6.days, dateObject6.year);

        /**
         * days between the first date and the second date
         */
        // first dates
        Date a1 = new Date(1, 1, 2013);
        Date a2 = new Date(12, 31, 2019);
        Date a3 = new Date(12, 31, 2016);
        Date a4 = new Date(1, 1, 1700);
        Date a5 = new Date(1, 1, 2000);
        Date a6 = new Date(2, 12, 2000);

        //second dates
        Date b1 = new Date(12, 30, 2013);
        Date b2 = new Date(1, 1, 2020);
        Date b3 = new Date(1, 1, 2016);
        Date b4 = new Date(3, 1, 1700);
        Date b5 = new Date(3, 1, 2000);
        Date b6 = new Date(5, 23, 2005);

        System.out.println("");
        daysBetween(a1, b1);
        daysBetween(a2, b2);
        daysBetween(a3, b3);
        daysBetween(a4, b4);
        daysBetween(a5, b5);
        daysBetween(a6, b6);
    }
}
