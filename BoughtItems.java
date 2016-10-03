/**
 * Created by Hiumathy on 7/8/16.
 */
public class BoughtItems {
    private int customerID;
    private String model;
    private int quantity;
    private int storeID;
    private Date date;

    public BoughtItems(){
        customerID = 0;
        model = "N/A";
        quantity = 0;
        storeID = 0;
        date = new Date();
    }

    public void setBoughtItems(int c, String m, int q, int s, Date d){
        setCustomerID(c);
        setModel(m);
        setQuantity(q);
        setStoreID(s);
        setDate(d);
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public int getCustomerID() {
        return customerID;
    }
    public String getModel() {
        return model;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getStoreID() {
        return storeID;
    }
    public Date getDate() {
        return date;
    }

    public String toString(){
        return String.format("Customer ID: %d, Model ID: %s, Quantity: %d, Store ID: %d, Date: %s",
                customerID, model, quantity, storeID, date);
    }
}
