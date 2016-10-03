
/**
 * Created by Hiumathy on 7/24/16.
 */
public class Accountant extends Employees {
    private double hourlyRate;
    private Date hireDate;
    private double monthlyBaseSalary;

    public Accountant(){
        hourlyRate = 0.00;
        hireDate = new Date();
        monthlyBaseSalary = 0.00;

    }

    public double ComputeSalary(int numHours) {
        double salary;
        salary = hourlyRate * numHours;
        return salary;

    }

    public void setAccountant(double i, String t, String fn, String ln, Date d1, String g, double r, Date h, double m){
        setEmployeeID(i);
        setType(t);
        setFirstName(fn);
        setLastName(ln);
        setBirthDate(d1);
        setGender(g);
        setHourlyRate(r);
        setHireDate(h);
        setMonthlyBaseSalary(m);
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
    public void setMonthlyBaseSalary(double monthlyBaseSalary) {
        this.monthlyBaseSalary = monthlyBaseSalary;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
    public Date getHireDate() {
        return hireDate;
    }
    public double getMonthlyBaseSalary() {
        return monthlyBaseSalary;
    }

    public String toString() {
        return String.format("Type: %s, Name: %s %s, DoB: %s, Sex: %s, , Hire Date: %s, Monthly Base Salary: $%.2f, Hourly Rate: %.2f",
                getType(), getFirstName(), getLastName(), getBirthDate(), getGender(), getHireDate(), getMonthlyBaseSalary(), getHourlyRate());
    }
}

