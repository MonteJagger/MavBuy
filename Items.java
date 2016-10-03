import java.util.Arrays;

/**
 * Created by Hiumathy on 7/3/16.
 */
public class Items {
    private String modelID;
    private String companyName;
    private String condition;
    private double shippingCost;
    private int[] storeAv = new int[10];
    private Date dateAvail;
    private double shippingDays;
    private double price;
    private String type;

    /**
     * default constructor
     */
    public Items() {
        modelID = "x";
        companyName = "x";
        condition = "x";
        shippingCost = 0.00;
        storeAv = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        dateAvail = new Date();
        shippingDays = 0;
        price = 0.00;
        type = "x";
    }


    public void setItems(String id, String cName, String con, double shipC, int[] s,
                         Date d, double shipD, double p, String t) {
        setModelID(id);
        setCompanyName(cName);
        setCondition(con);
        setShippingCost(shipC);
        setStoreAv(s);
        setDateAvail(d);
        setShippingDays(shipD);
        setPrice(p);
        setType(t);
    }

    public void setModelID(String id) {
        modelID = id;
    }
    public void setCompanyName(String cName) {
        companyName = cName;
    }
    public void setCondition(String con) {
        condition = con;
    }
    public void setShippingCost(double shipC) {
        shippingCost = shipC;
    }
    public void setStoreAv(int[] s) {
        storeAv = s;
    }
    public void setDateAvail(Date d)
    {
        dateAvail = d;
    }
    public void setShippingDays(double shipD) {
        shippingDays = shipD;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getModelID() {
        return modelID;
    }
    public String getCompanyName() {
        return companyName;
    }
    public String getCondition() {
        return condition;
    }
    public double getShippingCost() {
        return shippingCost;
    }
    public int[] getStoreAv() {
        return storeAv;
    }
    public Date getDateAvail() {
        return dateAvail;
    }
    public double getShippingDays() {
        return shippingDays;
    }
    public double getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }

    public String toString() {
        String storeA = Arrays.toString(getStoreAv()); // used to print an array
        return String.format("%s, %s, %s, $%.2f, %s, %s, %.0f days, $%.2f, %s",
                modelID, companyName, condition, shippingCost, storeA, dateAvail, shippingDays, price, type);
    }

    // tests the array print
    /*
    public static void main(String[] args) {

        Items a = new Items();
        int[] arr = new int[10];
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        a.setStoreAv(arr);
        System.out.println(a);

    }
    */
}

