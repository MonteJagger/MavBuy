import java.util.ArrayList;

/**
 * Created by Hiumathy on 7/18/16.
 */
public class Enterprise implements DateConstants, Proj3Constants {
    private static ArrayList<Employees> employees;
    private static ArrayList<Items> items;
    private static ArrayList<Customer> customers;
    private static ArrayList<BoughtItems> boughtItems;

    public Enterprise() {
        ArrayList<Employees> employees = new ArrayList<Employees>(); // arrayList for Employees
        ArrayList<Items> items = new ArrayList<Items>(); // arrayList for Items
        ArrayList<Customer> Customer = new ArrayList<com.example.java.Customer>(); // arrayList for Customers
        ArrayList<BoughtItems> boughtItems = new ArrayList<BoughtItems>(); // arraylist for BoughtItems
    }

    public static void setEmployees(ArrayList<Employees> employees) {
        Enterprise.employees = employees;
    }
    public static void setItems(ArrayList<Items> items) {
        Enterprise.items = items;
    }
    public static void setCustomers(ArrayList<Customer> Customer) {
        Enterprise.customers = Customer;
    }
    public static void setBoughtItems(ArrayList<BoughtItems> boughtItems) {
        Enterprise.boughtItems = boughtItems;
    }

    public static ArrayList<Employees> getEmployees() {
        return employees;
    }
    public static ArrayList<Items> getItems() {
        return items;
    }
    public static ArrayList<Customer> getCustomers() {
        return customers;
    }
    public static ArrayList<BoughtItems> getBoughtItems() {
        return boughtItems;
    }

}