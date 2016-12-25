import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.*;

public class Menu {
	private AdminDAO admin = null;
	private DealerDAO dealer = null;
	private UserDAO user = null;
	private static Connection sharedConnection;

	public Menu() {
		admin = new AdminDAO(sharedConnection);
		dealer = new DealerDAO(sharedConnection);
		user = new UserDAO(sharedConnection);
	}

	/**
	 * Construct an ownership object.
	 * 
	 * @param driver. The driver class name.
	 * @param url. The database URL.
	 * @param user. The database user.
	 * @param password. The database password.
	 */
	public static Connection createConnection(String driver, String url, String user, String password) {
		Connection connect = sharedConnection;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(url, user, password);
			if (sharedConnection == null) {
				sharedConnection = connect;
			}
			return connect;
		} catch (Exception e) {
			System.err.println("Unable to connect to the database due to " + e);
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		createConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/projectfinalpart", "sa", "Coolguy1");
		Menu menu = new Menu();
		menu.selectUser();
	}

	public void selectUser() throws Exception {

		System.out.println("Select the user type:");
		System.out.println("1. Admin");
		System.out.println("2. Dealer");
		System.out.println("3. General User");
		System.out.println("4. Exit");

		System.out.print("Selection: ");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice = Integer.parseInt(br.readLine());

		switch (choice) {
		case 1:
			showAdminMenu();
			br.close();
			break;
		case 2:
			showDealerMenu();
			br.close();
			break;
		case 3:
			showUserMenu();
			br.close();
			break;
		case 4:
			System.exit(0);
			break;
		default:
			System.out.println("The input entered was not valid, please try again.");
			selectUser();
			break;
		}
	}

	private void showUserMenu() throws Exception {
		// Printing User Menu
		System.out.println("User Privileges:");
		System.out.println("1. Query Vehicle");
		System.out.println("2. Insert Dealer Rating");
		System.out.println("3. Get Vehicle Suggestions");
		System.out.println("4. Get Dealers which offer a specific car model");
		System.out.println("5. Get All vehicles offered by the dealer");
		System.out.println("6. Main Menu");

		System.out.print("Selection: ");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice = Integer.parseInt(br.readLine());

		switch (choice) {
		case 1: 
			queryVehicle();
			break;
		case 2:
			insertDealerRating();
			break;
		case 3:
			getVehicleSuggestions();
			break;
		case 4:
			locateUserVehicle();
			break;
		case 5:
			listAllVehicles();
		case 6:
			System.out.println("Main Menu");
			break;
		default:
			System.out.println("The input entered was not valid, please try again.");
			showDealerMenu();
			break;
		}
		selectUser();

	}

	private void showDealerMenu() throws Exception {
		// Printing Dealer Menu
		System.out.println("Dealer Privileges:");
		System.out.println("1. Submit a new Vehicle");
		System.out.println("2. Main Menu");

		System.out.print("Selection: ");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice = Integer.parseInt(br.readLine());

		switch (choice) {
		case 1:
			submitNewVehicle();
			break;
		case 2:
			System.out.println("Main Menu");
			break;
		default:
			System.out.println("The input entered was not valid, please try again.");
			showDealerMenu();
			break;
		}
		selectUser();

	}

	private void showAdminMenu() throws Exception {
		// Printing menu to screen
		System.out.println("Admin Privileges:");
		System.out.println("1. Remove Dealer");
		System.out.println("2. Update Dealer Information");
		System.out.println("3. Remove Vehicle");
		System.out.println("4. Add Vehicle");
		System.out.println("5. Main Menu");

		System.out.print("Selection: ");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice = Integer.parseInt(br.readLine());
		switch (choice) {
		case 1:
			removeDealerMenu();
			break;
		case 2:
			updateDealerMenu();
			break;
		case 3:
			removeVehicleMenu();
			break;
		case 4:
			addVehicle();
			break;
		case 5:
			System.out.println("Main Menu");
			break;
		default:
			System.out.println("The input entered was not valid, please try again");
			showAdminMenu();
			break;
		}
		selectUser();

	}

	/**
	 * Method requests details of new Vehicle to be added.
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	private void addVehicle() throws IOException, NumberFormatException, SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter your Vehicle ID: ");
		String vehicleID = br.readLine();
		
		System.out.print("Enter Manufacturer's Name: ");
		String manufacturerName = br.readLine();
		
		System.out.print("Enter Model's Name: ");
		String modelName = br.readLine();
		
		System.out.print("Enter Car Body Type (Sedan/SUV/Coupe/Motorcycle/MiniVan: ");
		String bodyType = br.readLine();
		
		System.out.print("Enter Model Year: ");
		String modelYear = br.readLine();
		
		System.out.print("Enter Transmission Type (Automatic/Manual): ");
		String transmissionType = br.readLine();
		
		System.out.print("Enter Spare (Full/Donut/None): ");
		String spare = br.readLine();
		
		System.out.print("Enter Max Seating capacity: ");
		String maxCapacity = br.readLine();
		
		System.out.print("Enter Drivetrain Type (FWD/RWD): ");
		String driveTrainType = br.readLine();
		
		if(!empty(manufacturerName) && !empty(modelName) && validBodyType(bodyType) && isNumeric(modelYear)
				&& validTransmissionType(transmissionType) && validSpare(spare) && isNumeric(maxCapacity)
				&& validDriveTrainType(driveTrainType)){
			admin.insertVehicle(manufacturerName, modelName, bodyType, Integer.parseInt(modelYear), transmissionType, spare,
					Integer.parseInt(maxCapacity), driveTrainType, Integer.parseInt(vehicleID));
		} else {
			System.out.println("The input entered was not valid, please try again.");
			addVehicle();
		}
	}

	private boolean validDriveTrainType(String driveTrainType) {
		if(driveTrainType.equalsIgnoreCase("fwd") || driveTrainType.equalsIgnoreCase("rwd"))
			return true;
		return false;
	}

	private boolean validSpare(String spare) {
		if(spare.equalsIgnoreCase("full") || spare.equalsIgnoreCase("donut") || spare.equalsIgnoreCase("none"))
			return true;
		return false;
	}

	private boolean validTransmissionType(String transmissionType) {
		if(transmissionType.equalsIgnoreCase("automatic") || transmissionType.equalsIgnoreCase("manual"))
			return true;
		return false;
	}

	private boolean validBodyType(String bodyType) {
		if(bodyType.equalsIgnoreCase("sedan") || bodyType.equalsIgnoreCase("suv") || bodyType.equalsIgnoreCase("coupe")
				|| bodyType.equalsIgnoreCase("motorcyle") || bodyType.equalsIgnoreCase("minivan"))
			return true;
		return false;
	}

	/**
	 * Method lists all vehicles manufactured by the given Manufacturer Name
	 * @throws Exception
	 */
	private void queryVehicle() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter the manufacturer: ");
		String manufacturerName = br.readLine();
		if(!empty(manufacturerName)){
			user.queryVehicle(manufacturerName);
		} else {
			System.out.println("The input entered was not valid, please try again.");
			queryVehicle();
		}
	}

	/**
	 * Method locates all the dealers with zipcode which offer the given car model.
	 * @throws IOException
	 * @throws SQLException
	 */
	private void locateUserVehicle() throws IOException, SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter the model: ");
		String modelName = br.readLine();
		if(!empty(modelName)){
			user.locateVehicle(modelName);
		} else {
			System.out.println("The input entered was not valid, please try again.");
			locateUserVehicle();
		}

	}


	void getVehicleSuggestions() throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		System.out.println("Available Attributes: model/carBodyType/modelYear/"
				+ "transmissionType/spare/maximumSeatingCapacity/driveTrainType ");
		System.out.print("Provide the car attribute you would like to search on: ");
		String carAttributeToSearch = br.readLine();

		System.out.print("Provide the value for this car attribute: ");

		String attributeValue = br.readLine();

		if(!empty(carAttributeToSearch) && !empty(attributeValue)){
			user.returnSuggestions(carAttributeToSearch, attributeValue);
		} else {
			System.out.println("The input entered was not valid, please try again.");
			getVehicleSuggestions();
		}
	}

	/**
	 * Method to Insert new Dealer rating by the user.
	 * @throws Exception
	 */
	private void insertDealerRating() throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Provide the user name for the user: ");
		String userName = br.readLine();

		System.out.print("Provide the dealer you would like to rate: ");

		String dealerName = br.readLine();

		System.out.print("Provide the rating value for this dealer: ");
		String rating = br.readLine();

		int ratingValue = 1;
		if(!empty(userName) && !empty(dealerName) && isNumeric(rating)){
			ratingValue = Integer.parseInt(rating);
			user.insertDealerRating(userName, dealerName, ratingValue);
		} else {
			System.out.println("The input entered was not valid, please try again.");
			insertDealerRating();
		}
	}

	/**
	 * Method adds existing vehicles to the inventory. This method updates the stock
	 * if the vehicle is already offered by the Dealer in inventory.
	 * @throws Exception
	 */
	private void submitNewVehicle() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Please Your Dealer ID: ");
		String strDealerId = br.readLine();

		System.out.print("Please Enter Vehicle's ID to be added to Inventory: ");
		String strVehicleId = br.readLine();

		System.out.print("Please Enter Number of vehicles to be added: ");
		String strStock = br.readLine();

		if(isNumeric(strDealerId) && isNumeric(strVehicleId) && isNumeric(strStock)){
			int dealerId = Integer.parseInt(strDealerId);
			int vehicleId = Integer.parseInt(strVehicleId);
			int stock = Integer.parseInt(strStock);
			dealer.submitVehicle(dealerId, vehicleId, stock);
		} else {
			System.out.println("The input entered was not valid, please try again.");
			submitNewVehicle();
		}

	}

	/**
	 * Method displays all vehicles offered by the given Dealer.
	 * @throws Exception
	 */
	private void listAllVehicles() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please Enter Dealer's Name: ");
		String dealerName = br.readLine();

		if(!empty(dealerName)){
			for (String Vehicle : dealer.getAllVehicles(dealerName)) {
				System.out.println(Vehicle);
			}
		} else {
			System.out.println("The input entered was not valid, please try again.");
			listAllVehicles();
		}
	}

	/**
	 * Method removes all vehicles from the inventory which are out of stock.
	 * @throws SQLException
	 */
	private void removeVehicleMenu() throws SQLException {
		System.out.println("Removing Vehicles which are out of stock....");
		admin.removeOutOfStockVehicles();

	}

	private void updateDealerMenu() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please Enter Dealer's Name:");
		String dealerName = br.readLine();

		System.out.print("Enter new contact number:");
		String newContact = br.readLine();

		if(!empty(dealerName) && isValidPhoneNumber(newContact)){
			admin.updateContact(dealerName, newContact);
		} else {
			System.out.println("The input entered was not valid, please try again.");
			updateDealerMenu();
		}
	}

	/**
	 * This Method requests the name of the Dealer to be removed.
	 * @throws IOException
	 * @throws SQLException
	 */
	private void removeDealerMenu() throws IOException, SQLException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Provide the dealer you would like to remove: ");
		String dealerName = br.readLine();
		if(!empty(dealerName)){
			admin.removeDealer(dealerName);
		} else {
			System.out.println("The input entered was not valid, please try again.");
			removeDealerMenu();
		}

	}

	/**
	 * Utility method to check if string is empty or not
	 * @param s
	 * @return boolean true if String is empty; false otherwise
	 */
	public static boolean empty( final String s ) {
		// Null-safe, short-circuit evaluation.
		return s == null || s.trim().isEmpty();
	}

	/**
	 * Utility method to check if String is a valid number or not
	 * @param str
	 * @return boolean true if String is valid number; false otherwise
	 */
	public static boolean isNumeric(String str)  
	{  
		try  
		{  
			int i = Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}

	/**
	 * Utility method to check if input string is a valid phone number.
	 * @param phoneNumber String. Phone number to validate.
	 * @return boolean: true if phone number is valid, false otherwise.
	 */
	public static boolean isValidPhoneNumber(String phoneNumber){
		boolean isValid = false;
		String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";  
		CharSequence inputStr = phoneNumber;  
		Pattern pattern = Pattern.compile(expression);  
		Matcher matcher = pattern.matcher(inputStr);  
		if(matcher.matches()){  
			isValid = true;  
		}  
		return isValid;  
	}
}