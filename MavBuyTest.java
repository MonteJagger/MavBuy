/**
 * Programmer: 	Soumyava Das
 * Language:	Java
 * date:        07/10/2016
 * Purpose:		This program uses MavBuyTest class to read data from a text file to initialize
 * 				employees, items, clients, etc.
 * 				
 *              It checks and recovers from some exceptions while reading the input file
 *                 uses a bufferedReader which is different from project 2
 *              NO assumptions on the #employees etc. adjust your code accordingly!
 
 *              also shows how to write to an output file
 * 
 * USAGE:       You need to initialize your data structures as the first step. 
 *              once the values are read into local variables, 
 *              it  is YOUR responsibility to add code at proper places to create objects and manage them!
 *
 *              input and output file names are given as a command line argument 
 *              (e.g, java MavBuyTest dataFile-proj3.txt proj3-output.txt)
 *              for the naming convention used in this file. if you forget to give the data 
 *              files as  command line arguments, it will prompt you for that.	
 *          
 *              you can remove or comment out prints once you are sure it is reading the input correctly
 *
 * MAKE SURE:   your program is case insensitive (for state code, city, gender, employee type etc.)
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Double.compare;

/**
 * @param inputfileName
 *            as input data filename contaning input items with : as item separators
 * @param   outputFileName as output file name 
 */

public class MavBuyTest implements DateConstants, Proj3Constants {

	// introduce your (class and instance) attributes for this test class
	private static Enterprise enterprise = new Enterprise();
	private static ArrayList<Employees> workers = new ArrayList<Employees>();
	private static ArrayList<Items> stuff = new ArrayList<Items>(); // arrayList for Items
	private static ArrayList<Customer> clients = new ArrayList<Customer>(); // arrayList for Customers
	private static ArrayList<BoughtItems> purchases = new ArrayList<BoughtItems>(); // arraylist for BoughtItems
	private static ArrayList<Integer[]> yearPurchases = new ArrayList<Integer[]>();



    private static BufferedReader finput;
	private static String input = "dataFile-proj3.txt";
	private static String output = "output.txt";
    private static Scanner cp;  // this is still command prompt
    private static PrintWriter foutput;


	// Get Methods for the GUI
	public static ArrayList<Employees> getWorkers() {
		return workers;
	}
	public static ArrayList<Items> getStuff() {
		return stuff;
	}
	public static ArrayList<Customer> getClients() {
		return clients;
	}
	public static ArrayList<BoughtItems> getPurchases() {
		return purchases;
	}

	private static void menu() {
		System.out.println(
				"Please select an option below:\n" +
						"1. ) List employees.\n" +
						"2. ) List clients in a state.\n" +
						"3. ) List portfolio of a client.\n" +
						"4. ) Release an employee.\n" +
						"5. ) Display items for purchasing/selling.\n" +
						"6. ) Display customer details.\n" +
						"7. ) Buy an item for a customer.\n" +
                        "8. ) Show menu Project 3.\n" +
						"0. ) Exit Program\n"
		);

		// switch statement
		Scanner swit = new Scanner(System.in);
		System.out.printf("Enter a number from the menu: ");
		int n = swit.nextInt();
		System.out.println("\nYour number is " + n);
		switch (n) {
			case 1:
				PrintE();
				menu();
				break;
			case 2:
				StateClient();
				System.out.println();
				menu();
				break;
			case 3:
				ItemsBought();
				menu();
				break;
			case 4:
				RemoveEmployee();
				menu();
				break;
			case 5:
				PrintI();
				menu();
				break;
			case 6:
				PrintC();
				menu();
				break;
			case 7:
				BuyItem();
				menu();
				break;
            case 8:
                MenuProj3();
                menu();
                break;
			case 0:
				System.out.println("Goodbye");
				break;


		}
	}

	private static void PrintE(){
		for (Employees a : enterprise.getEmployees()) System.out.println(a.toString());
		System.out.println();
	} 				// 1. List employees with details (including specialization)
	private static void StateClient(){
		Scanner read = new Scanner(System.in);
		System.out.printf("Enter a State: ");
		String userState = read.next();
		//System.out.println(clients.get(0).getAddress().getState());
		for (Customer a: enterprise.getCustomers()) {
			String clientState = a.getAddress().getState();
			if (userState.equals(clientState)) {
				System.out.println(a);
			}
		}
	}    		// 2. List clients in that state
	private static void ItemsBought(){
		System.out.println("Items bought by the client");
		System.out.printf("Enter client's ID number: ");
		Scanner read = new Scanner(System.in);
		int userNum = read.nextInt();
		int count = 0;

		while(enterprise.getBoughtItems() != null) {
			for (BoughtItems a2 : enterprise.getBoughtItems()) {
				if (userNum == a2.getCustomerID()) {
					System.out.println(a2);
					count++;
				}
			}
		}

		if (count==0) System.out.printf("%d does not have a list\n", userNum);
		System.out.println();




	} 			// 3. List the items bought by a client (input: client number)
	private static void RemoveEmployee(){
		System.out.printf("To remove an employee, please enter the Employee ID: ");
		Scanner read = new Scanner(System.in);
		double userNum = read.nextDouble();
		int count = 0;

		for (Employees a1 : enterprise.getEmployees()){
			if (compare(userNum, a1.getEmployeeID()) == 0){
				ArrayList<Employees> work = enterprise.getEmployees();
				work.remove(a1);
				count++;
				break;
			}
		}
		if (count==0) System.out.println("No employee with the ID number "+ userNum);
		System.out.println();
	} 		// 4. Release an employee (input: employee id)
	private static void PrintI() {
		for (Items a : enterprise.getItems()) System.out.println(a.toString());
		System.out.println();
	}    			// 5. Displays all items
	private static void PrintC(){
		System.out.printf("\nEnter customer ID: ");
		Scanner user = new Scanner(System.in);
		int userNum = user.nextInt();
		if (userNum == 0){
			for (Customer c : enterprise.getCustomers()) System.out.println(c);
		}
		for (Customer a : enterprise.getCustomers()) {
			if (userNum == a.getCustomerID()) {
				System.out.println(a.toString());
			}
		}
		System.out.println();
	}  				// 6. Display customer details (input: client number or 0 for all)
	private static void BuyItem() {
		int count = 0;

		/**
		 *  asking for customer ID
		 */
		System.out.printf("Enter Customer ID: ");
		Scanner read = new Scanner(System.in);
		int userClient = read.nextInt(); // customer ID

		// validating
		while (count == 0) {
			for (Customer a9 : enterprise.getCustomers()) {
				if (userClient == a9.getCustomerID()) {
					System.out.println("Buying items for " + a9.getFirstName() + " " + a9.getLastName());
					count++;
				}
			}
			if (count == 0) {
				System.out.println("Customer ID " + userClient + " does not exist.");
				System.out.printf("Enter Customer ID: ");
				userClient = read.nextInt();
			}
		}

		/**
		 * asking user for Model ID
		 */
		System.out.printf("Enter Model ID: ");
		String userModel = read.next(); // Model

		count = 0;
		while (count == 0) {
			for (Items a : enterprise.getItems()) {
				if (userModel.equals(a.getModelID())) {
					System.out.println("Buying the " + a.getCompanyName() + " " + a.getModelID() + ", Price: $" + a.getPrice());
					count++;
				}
			}
			if (count == 0) {
				System.out.println("Cannot find Model ID for " + userModel);
				System.out.printf("Enter Model ID: ");
				userModel = read.next();
			}
		}

		/**
		 * asking user for quantity and store ID
		 * if the store ID does not have enough quantity, then loop then ask quantity and store ID again
		 */
		count = 0;
		int quantity = 0;
		int userStoreID = 0;
		while (count == 0) {
			/**
			 * asking user for quantity
			 */
			System.out.printf("Enter quantity: ");
			quantity = read.nextInt(); // quantity
			System.out.println("Quantity is " + quantity);

			/**
			 * asking user for Store ID
			 */
			System.out.println("Enter store ID: ");
			userStoreID = read.nextInt(); // store ID
			while (userStoreID < 0 || userStoreID > 9) {
				System.out.println("Store ID " + userStoreID + " does not exist. Try again");
				System.out.println("Enter store ID: ");
				userStoreID = read.nextInt();
			}

			/**
			 * check to see if there is enough quantity at the certain store
			 */
			// scans through each item
			for (int i = 0; i < enterprise.getItems().size(); i++) {
				Items eachItem = enterprise.getItems().get(i); // each item and its data
				String itemModelID = eachItem.getModelID(); // model in the each item
				int[] eachStoreAv = eachItem.getStoreAv(); // store availability in each item
				if (itemModelID.equals(userModel)) // comparing string to the entered user model ID
				{
					// check to see if the user asked for more than what the store has
					if (quantity > eachStoreAv[userStoreID]) {
						System.out.println("Not enough items at the store");
					}
					// else purchase is successful
					else {
						System.out.println("Purchase successful");
						eachStoreAv[userStoreID] -= quantity;
						count++;
					}
				}
			}
		}

		/**
		 * asking user for Date
		 */
		count = 0;
		Date purchaseDate = null;
		while (count == 0) {
			System.out.printf("Enter month: ");
			int month = read.nextInt();
			System.out.printf("Enter day: ");
			int day = read.nextInt();
			System.out.printf("Enter year: ");
			int year = read.nextInt();
			purchaseDate = new Date(month, day, year);

			if (purchaseDate.validate(month, day, year) == 1) {
				count++;
			}
			else System.out.println("Invalid date. Try again.");
		}


		// assigning the variances in the BoughtItems object
		BoughtItems customerItems = new BoughtItems();
		customerItems.setBoughtItems(userClient, userModel, quantity, userStoreID, purchaseDate);

		purchases.add(customerItems); // adding the data to the arrayList
		enterprise.setBoughtItems(purchases);
	} 			// 7. Buy an item for a customer: input: customer id, item id, quantity, store id and a date
    private static void MenuProj3(){
        System.out.println(
                "Please select an option below:\n" +
                        "10. ) Show menu project 1\n"+
                        "11. ) Make a sale\n" +
                        "12. ) List client purchases in a year\n" +
                        "13. ) Add employee\n" +
                        "14. ) Release an employee\n" +
                        "15. ) Compute Salary\n" +
                        "16. ) Expenditure\n" +
                        "17. ) Gold status members\n" +
                        "0.  ) Exit Program\n"
        );
        Scanner read = new Scanner(System.in);
        System.out.printf("Enter a number from the menu: ");
        int x = read.nextInt();
        switch (x) {
            case 10:
                menu();
				break;
            case 11:
                //ProcessSale();
                MenuProj3();
                break;
            case 12:
                ListItemsYearly();
                MenuProj3();
                break;
            case 13:
                HireNewEmployee();
                MenuProj3();
                break;
            case 14:
                ReleaseEmployee(x);
                MenuProj3();
                break;
            case 15:
                PrintSalary();
                MenuProj3();
                break;
            case 16:
                Expenditure();
                MenuProj3();
                break;
            case 17:
                GoldStatus();
                MenuProj3();
                break;
            case 0:
                System.out.println("Goodbye");
                break;
        }
    }

	public static double CheckDate(int empMonth, int empDay, int empYear, double oldestMember, double empID){
		double oldestEmpID = oldestMember; // updates the oldest member
		Date d = new Date(); 		// create a new Date to copy

		// searches the employee the oldest hire date to compare
		for (Employees b : workers){
			if (b.getEmployeeID().equals(oldestEmpID)){
				if (b.getType().equals("AGT")){
					Agent agt = new Agent();
					agt = (Agent) b;
					d = agt.getJoinDate();
				}
				else if (b.getType().equals("ACCT")){
					Accountant acct = new Accountant();
					acct = (Accountant) b;
					d = acct.getHireDate();
				}
				else if (b.getType().equals("WD")){
					WebDesigner wd = new WebDesigner();
					wd = (WebDesigner) b;
					wd.getJoinDate();
					d = wd.getJoinDate();
				}

			}
		}

		int earliestYear = d.getYear();
		int earliestMonth = d.getMonth();
		int earliestDay = d.getDays();

		if (empYear < earliestYear) { // if year is less than its the earliest
			earliestYear = empYear;
			oldestEmpID = empID;
		}
		else if (empYear == earliestYear){ // else if it's equal check the month
			if (empMonth < earliestMonth){
				earliestMonth = empMonth;
				oldestEmpID = empID;
			}
			else if (empMonth == earliestMonth){
				if (empDay <= earliestDay);
				earliestDay = empDay;
				oldestEmpID = empID;
			}
		}
		return oldestEmpID;
	}

	public static Employees SortDate() {
		double oldestEmpID = 0; // Oldest employee ID
		int count = 0;

		for (Employees a : workers) {
			if (count == 0) {
				oldestEmpID = a.getEmployeeID();
				count = 1;
			}

			if (a.getType().equals("AGT")) {
				Agent agt = new Agent();
				agt = (Agent) a;
				int agtY = agt.getJoinDate().getYear();
				int agtM = agt.getJoinDate().getMonth();
				int agtD = agt.getJoinDate().getDays();
				double empID = a.getEmployeeID();
				oldestEmpID = CheckDate(agtM, agtD, agtY, oldestEmpID, empID);

			} else if (a.getType().equals("ACCT")) {
				Accountant acct = new Accountant();
				acct = (Accountant) a;
				int acctY = acct.getHireDate().getYear();
				int acctM = acct.getHireDate().getMonth();
				int acctD = acct.getHireDate().getDays();
				double empID = a.getEmployeeID();
				oldestEmpID = CheckDate(acctM, acctD, acctY, oldestEmpID, empID);
			} else if (a.getType().equals("WD")) {
				WebDesigner wb = new WebDesigner();
				wb = (WebDesigner) a;
				int wbY = wb.getJoinDate().getYear();
				int wbM = wb.getJoinDate().getMonth();
				int wbD = wb.getJoinDate().getDays();
				double empID = a.getEmployeeID();
				oldestEmpID = CheckDate(wbM, wbD, wbY, oldestEmpID, empID);
			}
		}

		for (Employees b : workers) {
			if (b.getEmployeeID().equals(oldestEmpID)) {
				System.out.println("Oldest Member is:");
				System.out.println(b);
				System.out.println();
				return b;
			}
		}
		return null;
	} // PROJECT 4 METHOD
    public static void ProcessSale(int CustID, String id, Date date, int quant, int storeID){

       /* System.out.printf("Client: %d %s\t%s\t bought %d of item %s   \t for $%.2f on [%s] at store #%d with shipping cost: $%.2f\n",
                Integer.parseInt(chopProcess[ONEI]), "Hiumathy", "Lam", Integer.parseInt(chopProcess[FOURI]), chopProcess[TWOI],
                0.00, chopProcess[THREEI], Integer.parseInt(chopProcess[FIVEI]), 0.00);
*/

        for (BoughtItems a: purchases){
            System.out.println(a);
        }
    }
    public static void ListItemsYearly(){
        for( int i = 0; i < yearPurchases.size(); i++ ) {
            for( int j = 0; j < yearPurchases.get(i).length; j++ )
                System.out.printf(" $ " + yearPurchases.get(i)[j]);

            System.out.println();
        }
    }
    public static void HireNewEmployee(){
        int i =0;
    }
    public static void ReleaseEmployee(double x){
        int i =0;
    }
    public static void ComputeSalary(int empID, int value, int month){
        Scanner read = new Scanner(System.in);
        System.out.printf("Enter Employee ID: ");
        double salary = 0;
        int count = 0;

        for (Employees a : workers){
            if (a.getEmployeeID() == empID) {
                if (a.getType().equals("AGT")) {
                    salary = a.ComputeSalary(value);
                }
                else if (a.getType().equals("ACCT")) {
                    salary = a.ComputeSalary(value);
                }
                else if (a.getType().equals("WD")) {
                    salary = a.ComputeSalary(value);
                }
                count++;
                System.out.printf("ID: %.0f\t%s \t%s %s\t Salary: $%.2f\n", a.getEmployeeID(), a.getType(), a.getFirstName(), a.getLastName(), salary);
				foutput.printf("ID: %.0f\t%s \t%s %s\t Salary: $%.2f\n", a.getEmployeeID(), a.getType(), a.getFirstName(), a.getLastName(), salary);
            }
        }
        if (count == 0){
            System.out.println("No employee with ID: " + empID);
			foutput.println("No employee with ID: " + empID);
        }
    }
    public static void PrintSalary(){

    }
    public static void Expenditure(){
        int i =0;
    }
    public static void GoldStatus(){
        int i =0;
    }

	public static int[] Split(String date, String z){
		String[] a = date.split(z); // splitting every z and adding it as an item to the String array
		// convert String to int
		int num[] = new int[a.length]; // int array
		for (int i=0; i<a.length; i++){
			int split = Integer.parseInt(a[i]);
			num[i] = split;
		}
		return num;
	} // splits string then converts to integers

	//Note that we are using a DIFFERENT method for reading input file;
	/**
	 * @param iFileName is the input data file name
	 */

	public static BufferedReader openReadFile(String fileName){
		BufferedReader bf = null;
		try{
			bf = new BufferedReader(new FileReader(fileName));
		}
		catch(FileNotFoundException FNFE){
			bf = null;
		}
		finally{
			return bf;
		}
	}

	/**
	 * @param oFileName is the input data file name
	 */


	public static PrintWriter openWriteFile(String fileName){
		PrintWriter outputFile = null;
		try{
			outputFile = new PrintWriter(new FileWriter(fileName));
		}
		catch(IOException IOE){
			outputFile = null;
		}
		finally{
			return outputFile;
		}
	}

	// add here constructors and methods as needed

	/**
	 * @param takes
	 *            fileNames as command line argument. prompts if not given
	 */
	public static void main(String[] args) {
		// declare variables used for input handling
		String enterprisename = DEFAULT_ENTERPRISE_NAME;
		String inputLine = "", ifName = "", ofName = "";

		// determine if correct input file is provided
/*
		cp = new Scanner(System.in);
		if (args.length < 1) {
			System.out.println("Input Data file name was not supplied");
			System.out.printf("Please type input data file name: ");
			ifName = cp.nextLine();
		} else if (args.length < 2) {
			ifName = args[ZEROI];
			System.out.printf("Please type output data file name: ");
			ofName = cp.nextLine();
		} else {
			ifName = args[ZEROI];
			ofName = args[ONEI];
		}
*/
		// See HOW RECOVERY is done (will cover next week)
		//System.out.println("ifName ="+ifName);
		//System.out.println("ofName ="+ofName);
		finput = openReadFile(input); // hardcode EDITED
		while (finput == null) {
			System.out.println("ifName =" + ifName);
			System.out.println("Error: Input FILE " + ifName + " does not exist.\nEnter correct file name: ");
			ifName = cp.nextLine();
			finput = openReadFile(ifName);
		}
		foutput = openWriteFile(output);// hardcode EDITED
		System.out.println("ofName =" + ofName);
		while (foutput == null) {
			System.out.printf("Error: Output FILE %s %s", ofName, " does not exist.\nEnter correct file name: ");
			ofName = cp.nextLine();
			foutput = openWriteFile(ofName);
		}

		/* GET MavBuyTest DETAILS */
		// start reading from data file
		// get name
		try {
			inputLine = finput.readLine();
			while (inputLine.charAt(BASE_INDEX) == '/')
				inputLine = finput.readLine();
			String enterpriseName = inputLine;
			System.out.printf("\n%s %s \n", "Enterprise name is: ",
					enterpriseName);

			// add code as needed

						/* GET EMPLOYEE DETAILS */

			// reading details for each employee from the data file
			//System.out.printf("\nEmployees: \n");
			int numEmployees = 0;
			inputLine = finput.readLine();
			while (inputLine.charAt(BASE_INDEX) == '/')
				inputLine = finput.readLine();
			while ((!inputLine.toLowerCase().equals("end"))) {
				String[] chopEmp = inputLine.split(":");

				// fill all fields for a single employee from a single line
				String empType = chopEmp[ZEROI];
				String empFName = chopEmp[ONEI];
				String empLName = chopEmp[TWOI];
				String empBDate = chopEmp[THREEI];
				int[] empB = Split(empBDate, "-"); // split method
				Date dateObject = new Date(empB[0], empB[1], empB[2]); // create new Date object and add it onto there

				String empGender = chopEmp[FOURI];
				String empHireDate = chopEmp[FIVEI];

				int[] empH = Split(empHireDate, "-"); // split method
				Date dateObject2 = new Date(empH[0], empH[1], empH[2]); // create new Date object and add it onto there

				double empBaseSalary = Double.parseDouble(chopEmp[SIXI]);
				double empRate = Double.parseDouble(chopEmp[SEVENI]);

				// add code here: in order to convert a date string to a Date object,
				// use the following
				// i.e., invoke the appropriate constructor of the Date class

				// prints out each employee
				/*System.out.printf("(%6s, %10s, %6s, %12s, %10.2f, %4s, %12s, %.2f)\n",
						empFName, empLName, empGender, empHireDate,
						empBaseSalary, empType, empBDate, empRate); */

				// create an object based on what type of employee
				int empID = numEmployees + 1;
				if (new String("AGT").equals(empType)) {
					Agent agent = new Agent();
					agent.setAgent(empID, empType, empFName, empLName, dateObject, empGender, dateObject2, empBaseSalary, empRate);
					workers.add(agent);
					enterprise.setEmployees(workers);

				} else if (new String("ACCT").equals((empType))) {
					Accountant accountant = new Accountant();
					accountant.setAccountant(empID, empType, empFName, empLName, dateObject, empGender, empRate, dateObject2, empBaseSalary);
					workers.add(accountant);
					enterprise.setEmployees(workers);
				} else if (new String("WD").equals((empType))) {
					WebDesigner webDesign = new WebDesigner();
					webDesign.setWebDesigner(empID, empType, empFName, empLName, dateObject, empGender, dateObject2, empBaseSalary, empRate);
					workers.add(webDesign);
					enterprise.setEmployees(workers);
				}

				// add code here: also, empTYpe is read as a string; if u are using a
				// enum, you need to convert it  using a switch or if statement

				//add this employee to array list
				inputLine = finput.readLine();
				while (inputLine.charAt(BASE_INDEX) == '/')
					inputLine = finput.readLine();
				numEmployees += 1;
			}
			//System.out.printf("#Employees: %d\n", numEmployees);

			// reading details of items from the data file
			//System.out.printf("\nItems: \n");

			int numItems = 0;
			inputLine = finput.readLine();
			while (inputLine.charAt(BASE_INDEX) == '/')
				inputLine = finput.readLine();

			while ((!inputLine.toLowerCase().equals("end"))) {
				String[] chopitem = inputLine.split(":");

				// get all fields of the donor
				String itemId = chopitem[ZEROI];
				String itemCompanyName = chopitem[ONEI];
				String itemCondition = chopitem[TWOI];
				double itemShippingCost = Double.parseDouble(chopitem[THREEI]);
				String itemAvailability = chopitem[FOURI];
				int[] stAvail = Split(itemAvailability, ",");
				String itemAvailabilityDate = chopitem[FIVEI];
				int[] availDate = Split(itemAvailabilityDate, "-"); // split method for Date
				Date availObj = new Date(availDate[0], availDate[1], availDate[2]); // new object for Date
				double itemShippingDays = Double.parseDouble(chopitem[SIXI]);
				double itemPrice = Double.parseDouble(chopitem[SEVENI]);
				String itemCategory = chopitem[EIGHTI];

				Items item = new Items();
				item.setItems(itemId, itemCompanyName, itemCondition, itemShippingCost,
						stAvail, availObj, itemShippingDays, itemPrice, itemCategory);
				stuff.add(item);
				enterprise.setItems(stuff);

				// prints out each item
				/*System.out.printf("[%s, %s, %s, %f, %s, %s, %f, %f, %s]\n",
                    itemId, itemCompanyName, itemCondition, itemShippingCost,
                               itemAvailability, itemAvailabilityDate, itemShippingDays, itemPrice, itemCategory); */

				// add code here to add item object to the enterprise

				inputLine = finput.readLine();
				while (inputLine.charAt(BASE_INDEX) == '/')
					inputLine = finput.readLine();
				numItems += 1;
			}
			//System.out.printf("#Items: %d\n", numItems);

			/* GET CLIENT DETAILS */

			// reading details for each client from the data file
			// System.out.printf("\nClients: \n");

			int numClients = 0;
			inputLine = finput.readLine();
			while (inputLine.charAt(BASE_INDEX) == '/')
				inputLine = finput.readLine();

			while ((!inputLine.toLowerCase().equals("end"))) {
				String[] chopAthlete = inputLine.split(":");

				// fill all fields for a single client from a single line
				String[] chopClient = inputLine.split(":");

				// fill all fields for a single client from a single line
				int clientId = Integer.parseInt(chopClient[ZEROI]);
				String clientFName = chopClient[ONEI];
				String clientLName = chopClient[TWOI];
				String clientDOB = chopClient[THREEI];
				int[] clientBirthTemp = Split(clientDOB, "-");
				Date clientBirth = new Date(clientBirthTemp[0], clientBirthTemp[1], clientBirthTemp[2]);

				String clientGender = chopClient[FOURI];
				String clientMemType = chopClient[FIVEI];
				int clientHouseNum = Integer.parseInt(chopClient[SIXI]); // house number
				String clientStreet = chopClient[SEVENI]; // street name
				String clientCity = chopClient[EIGHTI]; // city
				String clientState = chopClient[NINEI]; // state

				// add code: construct client object as appropriate
				Customer customer = new Customer();
				Address clientAddress = new Address();
				clientAddress.setAddress(clientHouseNum, clientStreet, clientCity, clientState);
				customer.setCustomer(clientId, clientFName, clientLName, clientBirth, clientGender, clientMemType, clientAddress);
				clients.add(customer);
				enterprise.setCustomers(clients);


				// prints out each client
                /*
				System.out.printf("{%d, %s, %s, %s, %s, %s, %d, %s, %s, %s} \n",
                    clientId, clientFName, clientLName, clientDOB, clientGender, clientMemType,
				    clientHouseNum, clientStreet, clientCity, clientState);
                */

				inputLine = finput.readLine();
				while (inputLine.charAt(BASE_INDEX) == '/')
					inputLine = finput.readLine();
				numClients += 1;
			}
			System.out.printf("#clients: %d\n", numClients);

			// process menu from here
			inputLine = finput.readLine();
			System.out.println(inputLine + "\n"); // start the menu backward compatibility
			foutput.println(inputLine + "\n");


			System.out.println("------------------------Menu Item Number: 10\n" +
					"\n" +
					"------------ WELCOME TO MavBuy Inc. ------------\n");
			foutput.println("------------------------Menu Item Number: 10\n" +
					"\n" +
					"------------ WELCOME TO MavBuy Inc. ------------\n");
			System.out.println(
					"Please select an option below:\n" +
							"1. ) List employees.\n" +
							"2. ) List clients in a state.\n" +
							"3. ) List portfolio of a client.\n" +
							"4. ) Release an employee.\n" +
							"5. ) Display items for purchasing/selling.\n" +
							"6. ) Display customer details.\n" +
							"7. ) Buy an item for a customer.\n" +
							"8. ) Show menu Project 3.\n" +
							"0. ) Go Back\n"
			);
			foutput.println(
					"Please select an option below:\n" +
							"1. ) List employees.\n" +
							"2. ) List clients in a state.\n" +
							"3. ) List portfolio of a client.\n" +
							"4. ) Release an employee.\n" +
							"5. ) Display items for purchasing/selling.\n" +
							"6. ) Display customer details.\n" +
							"7. ) Buy an item for a customer.\n" +
							"8. ) Show menu Project 3.\n" +
							"0. ) Go Back\n"
			);


			while (inputLine.charAt(ONEI) == '/' || inputLine.charAt(ONEI) == '0')
				inputLine = finput.readLine();
			System.out.println(inputLine);
			foutput.println(inputLine);
			System.out.println("---------------------Menu Item Number: 11\n");
			foutput.println("---------------------Menu Item Number: 11\n");

			inputLine = finput.readLine();
			// --------------- ITEMS BOUGHT FROM FILE -------------------
			while (inputLine.charAt(BASE_INDEX) != '/') {
				double totalPrice;
				// fill all fields for a single client from a single line
				String[] chopClientItems = inputLine.split(":");

				// fill all fields for a single client from a single line
				int clientID = Integer.parseInt(chopClientItems[ONEI]);
				String clientItemID = chopClientItems[TWOI];
				String clientDateBought = chopClientItems[THREEI];
				int[] splitClientDate = Split(clientDateBought, "-");
				Date clientDate = new Date(splitClientDate[0], splitClientDate[1], splitClientDate[2]);

				int clientQuantity = Integer.parseInt(chopClientItems[FOURI]);
				int clientStoreID = Integer.parseInt(chopClientItems[FIVEI]);

				// add code: construct client object as appropriate
				BoughtItems boughtItems = new BoughtItems();
				boughtItems.setBoughtItems(clientID, clientItemID, clientQuantity, clientStoreID, clientDate);
				purchases.add(boughtItems);
				enterprise.setBoughtItems(purchases);

				for (Customer a : clients) {
					if (a.getCustomerID() == clientID) {
						for (Items b : stuff) {
							if (b.getModelID().equals(clientItemID)) {
								if (a.getCustType().equals("GOLD")) {

									totalPrice = (clientQuantity * b.getPrice()) * .9;

								} else {
									totalPrice = clientQuantity * b.getPrice();
								}
								System.out.printf("Client: %d %s\t%s\t bought %d of item %s\t for %.2f on [%s] at store #%d with shipping cost: $%.2f\n",
										clientID, a.getFirstName(), a.getLastName(), clientQuantity, clientItemID, totalPrice, clientDate, clientStoreID, b.getShippingCost());
								foutput.printf("Client: %d %s\t%s\t bought %d of item %s\t for %.2f on [%s] at store #%d with shipping cost: $%.2f\n",
										clientID, a.getFirstName(), a.getLastName(), clientQuantity, clientItemID, totalPrice, clientDate, clientStoreID, b.getShippingCost());
							}
						}
					}
				}
				inputLine = finput.readLine();
			}

			System.out.println(inputLine); // /*items bought by clients
			foutput.println(inputLine);
			System.out.println("\n---------------------Menu Item Number: 12");
			foutput.println("\n---------------------Menu Item Number: 12");
			inputLine = finput.readLine();

			// --------------- ITEMS BOUGHT IN A YEAR -------------------
			while (inputLine.charAt(BASE_INDEX) != '/') {
				String[] chopYear = inputLine.split(":");
				int custYear;
				int custID;
				double totalPrice;
				System.out.println();
				foutput.println();

				if (chopYear.length < 3 || chopYear[2].length() < 4) {
					if (chopYear[TWOI].equals("*")) {
						custID = Integer.parseInt(chopYear[ONEI]);
						System.out.println("Purchases from Client " + custID + " in all year");
						foutput.println("Purchases from Client " + custID + " in all year");

						for (BoughtItems a : purchases) {
							for (Items b : stuff) {
								if (a.getModel().equals(b.getModelID())) {
									for (Customer c : clients) {
										if (c.getCustomerID() == custID && a.getCustomerID() == custID) {
											if (c.getCustType().equals("GOLD")) {
												totalPrice = (b.getPrice() * a.getQuantity()) * .9 + b.getShippingCost();
											} else {
												totalPrice = b.getPrice() * a.getQuantity() + b.getShippingCost();
											}
											System.out.printf("Item %s\t%s on [%s]\tamount: %d for: $%.2f\t by %s %s\n",
													a.getModel(), b.getCompanyName(), a.getDate(), a.getQuantity(), totalPrice, c.getFirstName(), c.getLastName());
											foutput.printf("Item %s\t%s on [%s]\tamount: %d for: $%.2f\t by %s %s\n",
													a.getModel(), b.getCompanyName(), a.getDate(), a.getQuantity(), totalPrice, c.getFirstName(), c.getLastName());
										}
									}
								}
							}
						}
					}
					inputLine = finput.readLine();
				} else {
					custID = Integer.parseInt(chopYear[ONEI]);
					custYear = Integer.parseInt(chopYear[TWOI]);
					System.out.println("Purchases from Client " + custID + " in " + custYear);
					foutput.println("Purchases from Client " + custID + " in " + custYear);

					for (BoughtItems a : purchases) {
						if (custID == a.getCustomerID() && (custYear == a.getDate().getYear())) {
							for (Items b : stuff) {
								if (a.getModel().equals(b.getModelID())) {
									for (Customer c : clients) {
										if (c.getCustomerID() == a.getCustomerID()) {
											if (c.getCustType().equals("GOLD")) {
												totalPrice = (b.getPrice() * a.getQuantity()) * .9;
											} else {
												totalPrice = b.getPrice() * a.getQuantity();
											}
											System.out.printf("Item %s\t%s on [%s]\tamount: %d for: $%.2f\t by %s %s\n",
													a.getModel(), b.getCompanyName(), a.getDate(), a.getQuantity(), totalPrice, c.getFirstName(), c.getLastName());
											foutput.printf("Item %s\t%s on [%s]\tamount: %d for: $%.2f\t by %s %s\n",
													a.getModel(), b.getCompanyName(), a.getDate(), a.getQuantity(), totalPrice, c.getFirstName(), c.getLastName());
										}
									}
								}
							}
						}
					}
					inputLine = finput.readLine();
				}
			}

			System.out.println(inputLine);
			foutput.println(inputLine);
			System.out.println("\n---------------------Menu Item Number: 13\n");
			foutput.println("\n---------------------Menu Item Number: 13\n");
			inputLine = finput.readLine();

			// --------------- ADD EMPLOYEES -------------------

			while (inputLine.charAt(BASE_INDEX) != '/') {
				String[] chopAddEmp = inputLine.split(":");
				String empType = chopAddEmp[1];
				String empFname = chopAddEmp[2];
				String empLname = chopAddEmp[3];

				String empD = chopAddEmp[4];
				int[] split = Split(empD, "-");
				Date empDob = new Date();
				empDob.setDate(split[0], split[1], split[2]);

				String empGender = chopAddEmp[5];

				empD = chopAddEmp[6];
				split = Split(empD, "-");
				Date empHire = new Date();
				empHire.setDate(split[0], split[1], split[2]);

				double empBsal = Double.parseDouble(chopAddEmp[7]);
				double empValue = Double.parseDouble(chopAddEmp[8]);
				numEmployees++;

				if (new String("AGT").equals(empType)) {
					Agent agent = new Agent();
					agent.setAgent(numEmployees, empType, empFname, empLname, empDob, empGender, empHire, empBsal, empValue);
					System.out.printf("ID: %d\t%s\t%s %s\tDOB: %s %s\thired on %s\t with $%.2f base + $%.2f OT rate\n", numEmployees, empType, empFname, empLname, empDob, empGender, empHire, empBsal, empValue);
					foutput.printf("ID: %d\t%s\t%s %s\tDOB: %s %s\thired on %s\t with $%.2f base + $%.2f OT rate\n", numEmployees, empType, empFname, empLname, empDob, empGender, empHire, empBsal, empValue);
					workers.add(agent);
					enterprise.setEmployees(workers);

				} else if (new String("ACCT").equals((empType))) {
					Accountant accountant = new Accountant();
					accountant.setAccountant(numEmployees, empType, empFname, empLname, empDob, empGender, empValue, empHire, empBsal);
					System.out.printf("ID: %d\t%s\t%s %s\tDOB: %s %s\thired on %s\t with $%.2f per hour\n", numEmployees, empType, empFname, empLname, empDob, empGender, empHire, empValue);
					foutput.printf("ID: %d\t%s\t%s %s\tDOB: %s %s\thired on %s\t with $%.2f per hour\n", numEmployees, empType, empFname, empLname, empDob, empGender, empHire, empValue);
					workers.add(accountant);
					enterprise.setEmployees(workers);
				} else if (new String("WD").equals((empType))) {
					WebDesigner webDesign = new WebDesigner();
					webDesign.setWebDesigner(numEmployees, empType, empFname, empLname, empDob, empGender, empHire, empBsal, empValue);
					System.out.printf("ID: %d\t%s\t%s %s\tDOB: %s %s\thired on %s\t with $%.2f base + $%.2f per click\n", numEmployees, empType, empFname, empLname, empDob, empGender, empHire, empBsal, empValue);
					foutput.printf("ID: %d\t%s\t%s %s\tDOB: %s %s\thired on %s\t with $%.2f base + $%.2f per click\n", numEmployees, empType, empFname, empLname, empDob, empGender, empHire, empBsal, empValue);
					workers.add(webDesign);
					enterprise.setEmployees(workers);
				}

				inputLine = finput.readLine();
			}

			System.out.println(inputLine);
			foutput.println(inputLine);
			System.out.println("---------------------Menu Item Number: 14\n");
			foutput.println("---------------------Menu Item Number: 14\n");
			inputLine = finput.readLine();

			// ----------- RELEASE EMPLOYEE ------------
			while (inputLine.charAt(BASE_INDEX) != '/') {
				String[] chopRemove = inputLine.split(":");
				double employeeNum = Integer.parseInt(chopRemove[1]);
				int count = 0;
				ReleaseEmployee(employeeNum);
				for (Employees a : workers) {
					if (a.getEmployeeID().equals(employeeNum)) {
						System.out.printf("Releasing Employee ID: %.0f\n", a.getEmployeeID());
						foutput.printf("Releasing Employee ID: %.0f\n", a.getEmployeeID());
						System.out.printf("ID: %.0f\t %s\t%s %s\t %s\t %s\n", employeeNum, a.getType(), a.getFirstName(), a.getLastName(), a.getBirthDate(), a.getGender());
						foutput.printf("ID: %.0f\t %s\t%s %s\t %s\t %s\n", employeeNum, a.getType(), a.getFirstName(), a.getLastName(), a.getBirthDate(), a.getGender());
						workers.remove(a);
						count++;
						break;
					}
				}

				if (count == 0) {
					System.out.printf("No employee with the ID number %.0f\n", employeeNum);
					foutput.printf("No employee with the ID number %.0f\n", employeeNum);
				}
				System.out.println();
				foutput.println();

				for (Employees a : workers) {
					System.out.printf("ID: %.0f\t %s\t%s %s\t %s\t %s\n", a.getEmployeeID(), a.getType(), a.getFirstName(), a.getLastName(), a.getBirthDate(), a.getGender());
					foutput.printf("ID: %.0f\t %s\t%s %s\t %s\t %s\n", a.getEmployeeID(), a.getType(), a.getFirstName(), a.getLastName(), a.getBirthDate(), a.getGender());
				}

				System.out.println();
				foutput.println();
				inputLine = finput.readLine();
			}

			System.out.println(inputLine);
			foutput.println(inputLine);
			System.out.println("\n---------------------Menu Item Number: 15\n");
			foutput.println("\n---------------------Menu Item Number: 15\n");
			inputLine = finput.readLine();

			// ----------- COMPUTE MONTHLY SALARY ------------
			while (inputLine.charAt(BASE_INDEX) != '/') {
				String[] chopSal = inputLine.split(":");
				int empID = Integer.parseInt(chopSal[ONEI]);
				int empVal = Integer.parseInt(chopSal[TWOI]);
				int empMonth = Integer.parseInt((chopSal[THREEI]));
				ComputeSalary(empID, empVal, empMonth);
				inputLine = finput.readLine();
			}

			// ----------- EXPENDITURE ------------
			System.out.println(inputLine);
			foutput.println(inputLine);
			System.out.println("---------------------Menu Item Number: 16\n");
			foutput.println("\n---------------------Menu Item Number: 16\n");
			inputLine = finput.readLine();
			while (inputLine.charAt(BASE_INDEX) != '/') {
				String[] chopExp = inputLine.split(":");
				String expModelID = chopExp[ONEI];
				double totalPrice;

				for (Items a : stuff) { // scans through items
					if (expModelID.equals(a.getModelID())) {
						for (BoughtItems b : purchases) { // scans through purchases
							if (a.getModelID().equals(b.getModel())) {
								for (Customer c : clients) {
									if (c.getCustomerID() == b.getCustomerID()) {
										if (c.getCustType().equals("GOLD")) {
											totalPrice = (b.getQuantity() * a.getPrice()) * .9  + a.getShippingCost();
										} else {
											totalPrice = b.getQuantity() * a.getPrice()  + a.getShippingCost();
										}
										System.out.printf("%d Item: %s was sold on %s to %s %s for $%.2f\n", b.getQuantity(), b.getModel(), b.getDate(), c.getFirstName(), c.getLastName(), totalPrice);
										foutput.printf("%d Item: %s was sold on %s to %s %s for $%.2f\n", b.getQuantity(), b.getModel(), b.getDate(), c.getFirstName(), c.getLastName(), totalPrice);
									}
								}
							}
						}
					}
				}
				inputLine = finput.readLine();


			}

			System.out.println(inputLine);
			foutput.println(inputLine);
			System.out.println("---------------------Menu Item Number: 17\n");
			foutput.println("---------------------Menu Item Number: 17\n");
			inputLine = finput.readLine();

			// ----------- GOLD SALARY ------------
			while (inputLine.charAt(BASE_INDEX) != '/') {
				String[] chopGold = inputLine.split(":");
				double totalPrice = 0;
				double temp;

				System.out.println("Membership Status for Client " + chopGold[ONEI] + " in " + chopGold[TWOI]);
				foutput.println("Membership Status for Client " + chopGold[ONEI] + " in " + chopGold[TWOI]);
				for (Customer a : clients) {
					if (chopGold[TWOI].equals("*")) {
						continue;
					} else {
						int chopID = Integer.parseInt(chopGold[ONEI]);
						int chopYear = Integer.parseInt(chopGold[TWOI]);
						if (a.getCustomerID() == chopID) {
							for (BoughtItems b : purchases) {
								int year = b.getDate().getYear();
								if (year == chopYear && b.getCustomerID() == chopID) {
									for (Items c : stuff) {
										if (c.getModelID().equals(b.getModel())) {
											temp = (c.getPrice() * b.getQuantity() + c.getShippingCost()) * .9;
											totalPrice = temp + totalPrice;
										}
									}

								}
							}
							if (totalPrice > 1000) {
								System.out.printf("Client: %d %s %s is GOLD purchase a total of $%.2f\n\n", a.getCustomerID(), a.getFirstName(), a.getLastName(), totalPrice);
								foutput.printf("Client: %d %s %s is GOLD purchase a total of $%.2f\n\n", a.getCustomerID(), a.getFirstName(), a.getLastName(), totalPrice);
							}
						}
					}
				}
				inputLine = finput.readLine();
			}

			MavBuyWindow gui = new MavBuyWindow();

		} catch (NumberFormatException NFE) {
			System.out.println("I/O Error in File: " + ifName + "\ncheck for: "
					+ NFE.getMessage() + " and correct it in: " + inputLine);
		} catch (RuntimeException RE) {
			System.out.println("Invalid Data error in File: " + ifName
					+ "\nPlease correct " + RE.getMessage() + " in the file!" + inputLine);
		}
		catch(IOException IOE){
			System.out.println("input/output Data error in File: " + ifName + "\nPlease correct " + IOE.getMessage() + " in the file!" + inputLine);
		}
		finally {
			foutput.close();
		}


	}
}
