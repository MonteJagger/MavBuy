
/**
 * Created by Hiumathy on 7/24/16.
 */
public class Agent extends Employees {
    private Date joinDate;
    private double baseMonthlySalary;
    private double hourlyOTrate;

    public Agent(){
        joinDate = new Date();
        baseMonthlySalary = 0.00;
        hourlyOTrate = 0.00;
    }

    public double ComputeSalary(int OThours) {
        double salary;
        salary = baseMonthlySalary + hourlyOTrate * OThours;
        return salary;
    }

    public void setAgent(double i, String t, String fn, String ln, Date d1, String g, Date j, double b, double h){
        setEmployeeID(i);
        setType(t);
        setFirstName(fn);
        setLastName(ln);
        setBirthDate(d1);
        setGender(g);
        setJoinDate(j);
        setBaseMonthlySalary(b);
        setHourlyOTrate(h);
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    public void setBaseMonthlySalary(double baseMonthlySalary) {
        this.baseMonthlySalary = baseMonthlySalary;
    }
    public void setHourlyOTrate(double hourlyOTrate) {
        this.hourlyOTrate = hourlyOTrate;
    }

    public Date getJoinDate() {
        return joinDate;
    }
    public double getbaseMonthlySalary() {
        return baseMonthlySalary;
    }
    public double getHourlyOTrate() {
        return hourlyOTrate;
    }

    public String toString() {
        return String.format("Type: %s, Name: %s %s, DoB: %s, Sex: %s, Join Date: %s, Monthly Base Salary: $%.2f, Hourly Overtime Rate: $%.2f",
                getType(), getFirstName(), getLastName(), getBirthDate(), getGender(), getJoinDate(), getbaseMonthlySalary(), getHourlyOTrate());
    }
}
