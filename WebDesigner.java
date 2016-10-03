/**
 * Created by Hiumathy on 7/24/16.
 */
public class WebDesigner extends Employees {
    private Date joinDate;
    private double baseMonthlySalary;
    private double ratePerClick;

    public WebDesigner(){
        joinDate = new Date();
        baseMonthlySalary = 0.00;
        ratePerClick = 0.00;
    }

    public double ComputeSalary(int clicks) {
        double salary;
        salary = baseMonthlySalary + (ratePerClick * clicks);
        return salary;

    }

    public void setWebDesigner(double i, String t, String fn, String ln, Date d1, String g, Date j, double b, double r){
        setEmployeeID(i);
        setType(t);
        setFirstName(fn);
        setLastName(ln);
        setBirthDate(d1);
        setGender(g);
        setJoinDate(j);
        setBaseMonthlySalary(b);
        setRatePerClick(r);
    }


    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    public void setBaseMonthlySalary(double monthlySalary) {
        this.baseMonthlySalary = monthlySalary;
    }
    public void setRatePerClick(double ratePerClick) {
        this.ratePerClick = ratePerClick;
    }

    public Date getJoinDate() {
        return joinDate;
    }
    public double getBaseMonthlySalary() {
        return baseMonthlySalary;
    }
    public double getRatePerClick() {
        return ratePerClick;
    }

    public String toString() {
        return String.format("Type: %s, Name: %s %s, DoB: %s, Sex: %s, Join Date: %s, Monthly Base Salary: $%.2f, Rate per click: $%.2f",
                getType(), getFirstName(), getLastName(), getBirthDate(), getGender(), getJoinDate(), getBaseMonthlySalary(),getRatePerClick());
    }
}
