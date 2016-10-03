/**
 * Created by Hiumathy on 7/3/16.
 */
public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private String custType;
    private Address address;

    // default constructor
    public Customer(){
        customerID = 0;
        firstName = "N/A";
        lastName = "N/A";
        birthDate = new Date();
        gender = "N/A";
        custType = "N/A";
        address = new Address();
    }

    public void setCustomer(int id, String fn, String ln, Date dob, String g, String c, Address addr){
        setCustomerID(id);
        setFirstName(fn);
        setLastName(ln);
        setBirthDate(dob);
        setGender(g);
        setCustType(c);
        setAddress(addr);
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setCustType(String custType) {
        this.custType = custType;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public int getCustomerID(){
        return customerID;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public String getGender() {
        return gender;
    }
    public String getCustType() {
        return custType;
    }
    public Address getAddress() {
        return address;
    }

    public String toString(){
        return String.format("%d, %s %s, %s, %s, %s, %s", customerID, firstName, lastName, birthDate,
                gender, custType, address);
    }
}
