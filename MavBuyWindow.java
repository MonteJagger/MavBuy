import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;



/**
 * Created by Hiumathy on 8/6/16.
 */
public class MavBuyWindow extends JFrame implements ActionListener, ListSelectionListener { // 1a. JFrame
    private final int WINDOW_WIDTH  = 1250; // width of frame
    private final int WINDOW_HEIGHT = 550; // height of frame
    private JComboBox box;  // Drop down menu                        // 1b. JComboBox
    private JList list;     // List for the content pane             // 2a. JList
    private JPanel panel;   // panel                                 // 3a. Jpanel
    private JPanel panelLeftMenu;
    private JScrollPane scroll;
    private BufferedImage mavImg;
    MavBuyTest mavbuy = new MavBuyTest();
    String userYear; // purchase detail year

    public MavBuyWindow(){

        panel = new JPanel(); // organizes
        list = new JList();
        try {
            mavImg = ImageIO.read(new File("UTA Logo.png"));
        }
        catch (IOException e){
            System.out.println("Image not found.");
        }



        GridLayout g = new GridLayout(3,1);
        panelLeftMenu = new JPanel(g);
        panelLeftMenu.setBackground(Color.BLUE);
        panelLeftMenu.setPreferredSize(new Dimension(300, 510));
        panel.setBackground(Color.ORANGE);


        // Setting up the JFrame
        setTitle("MavBuy");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BuildDropDownMenu();    // setting up the drop down menu
        BuildList();            // setting up the List
        setVisible(true);
        scroll.setBackground(Color.blue);
    }

    public void BuildDropDownMenu(){
        setLayout(new FlowLayout());
        String[] dropDown = {"Menu", "Employees", "Items", "Customers", "Items Sold", "Revenue",
                "Oldest Employee Member", "Purchase Details", "Highest Spent", "Lowest Spent"}; // Lists in the drop down menu
        box = new JComboBox(dropDown);

        try {mavImg = ImageIO.read(new File("UTA Logo.png"));}
        catch (IOException e){ System.out.println("Image not found.");}
        Image image = mavImg.getScaledInstance(320, 320, Image.SCALE_DEFAULT);
        JLabel mavPic = new JLabel(new ImageIcon(image));



        JLabel menu = new JLabel("MavBuy"); // menu text             // 3b. JLabel
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font("Futura", Font.BOLD, 65));
        menu.setHorizontalAlignment(JLabel.CENTER);
        menu.setVerticalAlignment(JLabel.CENTER);

        box.addActionListener(this);

        panelLeftMenu.add(menu, BorderLayout.CENTER);   // adds menu into panel1
        panelLeftMenu.add(mavPic);
        panelLeftMenu.add(box, BorderLayout.CENTER);    // adds drop down menu to panel1
        add(panelLeftMenu); // adds the panel to the content pane

    }

    public void BuildList(){
        scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        list.setFixedCellHeight(35); // size of cell height
        scroll.setPreferredSize(new Dimension(900, 500)); // size of scroll dimension

        panel.add(scroll);        // add list to the panel
        add(panel, BorderLayout.CENTER);    // add panel to the content pane while locating it at the center
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == box) { // Source of the event is the comboBox message list
            JComboBox cb = (JComboBox) e.getSource(); // copies the original combo box into a new one
            String msg = (String) cb.getSelectedItem(); // msg equals the string that is selected

            DefaultListModel mavList = new DefaultListModel();

            if (msg.equals("Employees")) {
                for (Employees a : mavbuy.getWorkers()) {
                    mavList.addElement(a);
                }
                list.setModel(mavList);
            } else if (msg.equals("Items")) {
                for (Items a : mavbuy.getStuff()) {
                    mavList.addElement(a);
                }
                list.setModel(mavList);
            } else if (msg.equals("Customers")) {
                for (Customer a : mavbuy.getClients()) {
                    mavList.addElement(a);
                }
                list.setModel(mavList);
            } else if (msg.equals(("Items Sold"))) {
                double yearlyRevenue = 0;
                double totalPrice;
                int count = 0;
                DecimalFormat df = new DecimalFormat("#.##");
                String userSold = JOptionPane.showInputDialog(null, "Enter an the item model");

                for (BoughtItems a : mavbuy.getPurchases()) {
                    if (a.getModel().equals(userSold)) {
                        for (Items b : mavbuy.getStuff()) {
                            if (a.getModel().equals(b.getModelID())) {
                                for (Customer c : mavbuy.getClients()) {
                                    if (a.getCustomerID() == c.getCustomerID()) {
                                        if (c.getCustType().equals("GOLD")) {
                                            totalPrice = (a.getQuantity() * b.getPrice()) * .9;
                                            yearlyRevenue = totalPrice + yearlyRevenue;
                                        } else {
                                            totalPrice = a.getQuantity() * b.getPrice();
                                            yearlyRevenue = totalPrice + yearlyRevenue;
                                        }
                                        mavList.addElement("Client: "+ c.getCustomerID()+ ", Name: " +c.getFirstName() +" "+ c.getLastName()+
                                                ", Model ID: "+ a.getModel()+ ", Date: "+ a.getDate()+ ", Quantity: "+ a.getQuantity() +
                                                ", Total Price: $"+ df.format(totalPrice));
                                    }
                                }
                            }
                        }
                        count = 1;
                    }
                }
                if (count == 0) {
                    JOptionPane.showMessageDialog(null, "Item " + userSold + " does not exist ");
                } else {
                    list.setModel(mavList);
                }
            } else if (msg.equals("Revenue")) {
                double yearlyRevenue = 0;
                double totalPrice;
                int count = 0;
                DecimalFormat df = new DecimalFormat("#.##");
                String userYear = JOptionPane.showInputDialog(null, "Enter year or type all");

                // total revenue
                if(userYear == null || (userYear != null && ("".equals(userYear))))
                {

                }
                else {
                    for (BoughtItems a : mavbuy.getPurchases()) {
                        if (userYear.equals("all")) {
                            for (Items b : mavbuy.getStuff()) {
                                if (a.getModel().equals(b.getModelID())) {
                                    for (Customer c : mavbuy.getClients()) {
                                        if (a.getCustomerID() == c.getCustomerID()) {
                                            if (c.getCustType().equals("GOLD")) {
                                                totalPrice = (a.getQuantity() * b.getPrice()) * .9;
                                                yearlyRevenue = totalPrice + yearlyRevenue;
                                            } else {
                                                totalPrice = a.getQuantity() * b.getPrice();
                                                yearlyRevenue = totalPrice + yearlyRevenue;
                                            }
                                            mavList.addElement("Model ID: " + a.getModel() + " Date: " + a.getDate() + " Quantity: " + a.getQuantity() +
                                                    " Total Price: $" + df.format(totalPrice));
                                        }
                                    }
                                }
                            }
                            count = 2;
                        }
                    }
                    if (count == 2) {
                        mavList.addElement("Total Revenue is $" + df.format(yearlyRevenue));
                        list.setModel(mavList);

                    } else {
                        try {
                            for (BoughtItems a : mavbuy.getPurchases()) {
                                if (a.getDate().getYear() == Integer.parseInt(userYear)) {
                                    for (Items b : mavbuy.getStuff()) {
                                        if (a.getModel().equals(b.getModelID())) {
                                            for (Customer c : mavbuy.getClients()) {
                                                if (a.getCustomerID() == c.getCustomerID()) {
                                                    if (c.getCustType().equals("GOLD")) {
                                                        totalPrice = (a.getQuantity() * b.getPrice()) * .9;
                                                        yearlyRevenue = totalPrice + yearlyRevenue;
                                                    } else {

                                                        totalPrice = a.getQuantity() * b.getPrice();
                                                        yearlyRevenue = totalPrice + yearlyRevenue;
                                                    }
                                                    mavList.addElement("Model ID: " + a.getModel() + " Date: " + a.getDate() + " Quantity: " + a.getQuantity() +
                                                            " Total Price: $" + df.format(totalPrice));
                                                }
                                            }
                                        }
                                    }
                                    count = 1;
                                }
                            }

                            if (count == 0) {
                                JOptionPane.showMessageDialog(null, "No purchases in " + userYear);
                            } else if (count == 1) {
                                mavList.addElement("Total Revenue is $" + df.format(yearlyRevenue));
                                list.setModel(mavList);
                            }

                        } catch (Exception event) {
                            System.out.println("Wrong input");
                            JOptionPane.showMessageDialog(null, "Input is invalid");
                        }

                    }
                }
            }
             else if (msg.equals("Oldest Employee Member")) {
                Employees oldMember = mavbuy.SortDate();
                mavList.addElement(oldMember);
                list.setModel(mavList);
            }
            else if (msg.equals("Purchase Details")) {
                int count = 0;
                userYear = JOptionPane.showInputDialog(null, "Enter year");
                if (userYear == null || (userYear != null && ("".equals(userYear))) ) {
                }
                else {
                try {
                    int userYearInt = Integer.parseInt(userYear);
                    String custID = JOptionPane.showInputDialog(null, "Enter Model ID");
                    if (custID == null || (custID != null && ("".equals(custID))) ) {
                    }
                    else {
                        try {
                            int custIDInt = Integer.parseInt(custID);
                            for (BoughtItems a : mavbuy.getPurchases()) {
                                if (userYearInt == a.getDate().getYear() && custIDInt == a.getCustomerID()) {
                                    System.out.println(a);
                                    mavList.addElement(a);
                                    count = 1;
                                }
                            }
                            if (count == 0) {
                                mavList.addElement("No purchases have been made in " + userYear + " for user " + custID);
                            }
                        } catch (Exception ev){
                            JOptionPane.showMessageDialog(null, "Not Valid", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (Exception event) {
                    JOptionPane.showMessageDialog(null, "Not Valid", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                    list.setModel(mavList);
                    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    JOptionPane.showMessageDialog(null, "Select an item for more details", "Select an Item", JOptionPane.PLAIN_MESSAGE);
                    list.addListSelectionListener(this);
            }
            }
            else if (msg.equals("Highest Spent")) {
                String userYear = JOptionPane.showInputDialog(null, "Enter year");
                double totalPrice;
                double totalPriceYear = 0;
                double highestPriceYear = 0;
                int customerID = -1;
                int count = 0;
                DecimalFormat df = new DecimalFormat("#.##");

                if (userYear == null || (userYear != null && ("".equals(userYear)))) {

                }
                else {
                    try {
                        int userYearInt = Integer.parseInt(userYear);
                        for (Customer a : mavbuy.getClients()) {
                            if (count == 1) {
                                totalPriceYear = 0;
                            }
                            for (Items b : mavbuy.getStuff()) {
                                for (BoughtItems c : mavbuy.getPurchases()) {
                                    if (c.getModel().equals(b.getModelID()) && c.getDate().getYear() == userYearInt && a.getCustomerID() == c.getCustomerID()) {
                                        count = 1;
                                        if (a.getCustType().equals("GOLD")) {
                                            totalPrice = (c.getQuantity() * b.getPrice() + b.getShippingCost()) * .9;
                                            totalPriceYear = totalPrice + totalPrice;
                                        } else {
                                            totalPrice = c.getQuantity() * b.getPrice() + b.getShippingCost();
                                            totalPriceYear = totalPrice + totalPriceYear;
                                        }
                                    }
                                }

                            }
                            if (totalPriceYear >= highestPriceYear) {
                                System.out.println("Customer ID: " + a.getCustomerID() + " total price: $" + df.format(totalPriceYear));
                                customerID = a.getCustomerID();
                                highestPriceYear = totalPriceYear;
                            }
                        }

                        count = 0;
                        for (Customer a : mavbuy.getClients()) {
                            if (a.getCustomerID() == customerID) {
                                mavList.addElement("The highest spent in " + userYear);
                                for (BoughtItems b : mavbuy.getPurchases()) {
                                    if (b.getCustomerID() == a.getCustomerID() && b.getCustomerID() == customerID) {
                                        for (Items c : mavbuy.getStuff()) {
                                            if (c.getModelID().equals(b.getModel())) {
                                                totalPrice = (b.getQuantity() * c.getPrice() + c.getShippingCost()) * .9;
                                                mavList.addElement("Name: " + a.getFirstName() + " " + a.getLastName() + " Model ID: " +
                                                        b.getModel() + " Quantity: " + b.getQuantity() + " Total Price w/ shipping: $" + df.format(totalPrice));
                                                count = 1;
                                            }
                                        }
                                    }
                                }
                                mavList.addElement("Total spent in 2013: $" + highestPriceYear);
                            }
                        }
                        if (count == 0) {
                            mavList.addElement("No purchases in " + userYear);
                        }
                    } catch (Exception event) {
                        JOptionPane.showMessageDialog(null, "Not Valid", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    list.setModel(mavList);
                }
            }
            else if (msg.equals("Lowest Spent")){
                String userYear = JOptionPane.showInputDialog(null, "Enter year");

                double totalPrice;
                double totalPriceYear = 0;
                double lowestPriceYear = 1000000;
                int customerID = -1;
                int count = 0;
                DecimalFormat df = new DecimalFormat("#.##");
                if(userYear == null || (userYear != null && ("".equals(userYear))))
                {
                }
                else{
                    try {
                        int userYearInt = Integer.parseInt(userYear);
                        for (Customer a : mavbuy.getClients()) {
                            if (count == 1) {
                                totalPriceYear = 0;
                            }
                            for (Items b : mavbuy.getStuff()) {
                                for (BoughtItems c : mavbuy.getPurchases()) {
                                    if (c.getModel().equals(b.getModelID()) && c.getDate().getYear() == userYearInt && a.getCustomerID() == c.getCustomerID()) {
                                        count = 1;
                                        if (a.getCustType().equals("GOLD")) {
                                            totalPrice = (c.getQuantity() * b.getPrice() + b.getShippingCost()) * .9;
                                            totalPriceYear = totalPrice + totalPrice;
                                        } else {
                                            totalPrice = c.getQuantity() * b.getPrice() + b.getShippingCost();
                                            totalPriceYear = totalPrice + totalPriceYear;
                                        }
                                    }
                                }
                            }
                            if (totalPriceYear <= lowestPriceYear && totalPriceYear != 0) {
                                System.out.println("Customer ID: " + a.getCustomerID() + " total price: $" + df.format(totalPriceYear));
                                customerID = a.getCustomerID();
                                lowestPriceYear = totalPriceYear;
                            }
                        }

                        count = 0;
                        for (Customer a : mavbuy.getClients()) {
                            if (a.getCustomerID() == customerID) {
                                mavList.addElement("The lowest spent in " + userYear);
                                for (BoughtItems b : mavbuy.getPurchases()) {
                                    if (b.getCustomerID() == a.getCustomerID() && b.getCustomerID() == customerID) {
                                        for (Items c : mavbuy.getStuff()) {
                                            if (c.getModelID().equals(b.getModel())) {
                                                totalPrice = (b.getQuantity() * c.getPrice() + c.getShippingCost()) * .9;
                                                mavList.addElement("Name: " + a.getFirstName() + " " + a.getLastName() + " Model ID: " +
                                                        b.getModel() + " Quantity: " + b.getQuantity() + " Total Price w/ shipping: $" + df.format(totalPrice));
                                                count = 1;
                                            }
                                        }
                                    }
                                }
                                mavList.addElement("Total spent in 2013: $" + lowestPriceYear);
                            }
                        }
                        if (count == 0) {
                            mavList.addElement("No purchases in " + userYear);
                        }
                    }
                    catch (Exception event) {
                        JOptionPane.showMessageDialog(null, "Not Valid", "ERROR", JOptionPane.ERROR_MESSAGE);

                    }
                    list.setModel(mavList);
                }
            }
        }
    }
    // This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        double totalPrice = 0;
        double totalPriceYear = 0;
        JList mList = new JList();
        DefaultListModel defList = new DefaultListModel();
        DecimalFormat df = new DecimalFormat("#.##");
        int count = 0;

        if (e.getValueIsAdjusting() == false) {
            int selected = list.getSelectedIndex(); // gets the selected item. Starts at 0
            for (int i = 0; i < list.getModel().getSize(); i++) { // scans through the list
                // when the user clicks the item
                if (selected == i) {
                    BoughtItems purchases = (BoughtItems) list.getModel().getElementAt(selected); // turns the item in the list to an object
                    int custID = purchases.getCustomerID(); // get the customer ID
                    for (Customer a : mavbuy.getClients()) {
                        if (custID == a.getCustomerID()) {
                            defList.addElement("Client ID: " + custID);
                            defList.addElement("First Name: " + a.getFirstName());
                            defList.addElement("Last Name: " + a.getLastName());
                            defList.addElement("Purchases in year " + userYear);
                            for (Items b : mavbuy.getStuff()) {
                                for (BoughtItems c : mavbuy.getPurchases()) {
                                    if (c.getModel().equals(b.getModelID()) && c.getCustomerID() == custID) {
                                        count = 1;

                                        if (a.getCustType().equals("GOLD")) {
                                            totalPrice = (c.getQuantity() * b.getPrice() + b.getShippingCost())*.9;
                                            totalPriceYear = totalPrice + totalPriceYear;
                                        } else {
                                            totalPrice = c.getQuantity() * b.getPrice() + b.getShippingCost();
                                            totalPriceYear = totalPrice + totalPriceYear;
                                        }
                                        System.out.println(c + " Total Price: $" + totalPriceYear);
                                        if (count == 1);{
                                            defList.addElement("Item: " + c.getModel() + " Quantity: " + c.getQuantity()+ " Total Price: $" + df.format(totalPrice));
                                            count = 0;
                                        }
                                    }

                                }

                            }
                        }
                        defList.addElement("Total Spent in "+ userYear + " is $"+ df.format(totalPriceYear));
                        mList.setModel(defList);
                        JOptionPane.showMessageDialog(null, mList);
                        break;
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        MavBuyWindow m = new MavBuyWindow();
    }
}

