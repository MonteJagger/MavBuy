
/**
 * Created by Hiumathy on 7/7/16.
 */
public class Address {
    private int number;
    private String street;
    private String city;
    private String state;

    // Default Address
    public Address() {
        number = 0;
        street = "N/A";
        city = "N/A";
        state = "N/A";
    }

    // set Methods
    public void setAddress(int n, String s, String c, String st){
        setNumber(n);
        setStreet(s);
        setCity(c);
        setState(st);
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }

    // get Methods
    public int getNumber() {
        return number;
    }
    public String getStreet() {
        return street;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }

    public String toString(){
        return String.format("%d %s, %s, %s", number, street, city, state);
    }
}