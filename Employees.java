/**
 * Created by Hiumathy on 7/3/16.
 */
abstract public class Employees {
    private Double employeeID;
    private String firstName;
    private String lastName;
    private String gender;
    private String type;
    private Date birthDate;

    /**
     * default Constructor
     */
    public Employees(){
        employeeID = 0.00;
        type = "N/A";
        firstName = "N/A";
        lastName = "N/A";
        birthDate = new Date();
        gender = "N/A";
    }


    public abstract double ComputeSalary(int salaryParameter);

    public void setEmployee(Double i, String t, String fn, String ln, Date d1, String g){
        setEmployeeID(i);
        setType(t);
        setFirstName(fn);
        setLastName(ln);
        setBirthDate(d1);
        setGender(g);
    }

    public void setEmployeeID(Double i) {
        employeeID = i;
    }
    public void setType(String t){
        type = t;
    }
    public void setFirstName(String fn){
        firstName = fn;
    }
    public void setLastName(String ln){
        lastName = ln;
    }
    public void setBirthDate(Date d){
        birthDate = d;
    }
    public void setGender(String g){
        gender = g;
    }

    public Double getEmployeeID(){
        return employeeID;
    }
    public String getType(){
        return type;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Date getBirthDate(){
        return birthDate;
    }
    public String getGender(){
        return gender;
    }

    public String toString(){
        return String.format("Employee ID: %03.0f, Type: %s, Name: %s %s, DoB: %s, Sex: %s",
                employeeID, type, firstName, lastName, birthDate, gender);
    }



}
