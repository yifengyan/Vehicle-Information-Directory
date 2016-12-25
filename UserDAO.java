import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

public class UserDAO {

	private Connection con;
	private ResultSet rs;

	public UserDAO(Connection con) {
		this.con = con;
	}

	/**
	 * @param connection
	 *            the SQL connection to the database to be updated
	 * @throws SQLException
	 *             if the provided SQL database cannot be accessed
	 * @throws IOException
	 *             if the provided input cannot be properly read
	 */
	public void insertDealerRating(String userName, String dealerName, int ratingValue)
			throws SQLException, IOException {

		PreparedStatement checkUser = con.prepareStatement("select id FROM user WHERE name=?");
		checkUser.setString(1, userName);
		ResultSet validUser = checkUser.executeQuery();
		
		PreparedStatement checkDealer = con.prepareStatement("SELECT d.id FROM dealer d WHERE d.name=?");
		checkDealer.setString(1, dealerName);
		ResultSet validDealer = checkDealer.executeQuery();
		
		int userId = 0, dealerId = 0;
		if(validUser.next()){
			userId = validUser.getInt(1);
			if(validDealer.next()){
				dealerId = validDealer.getInt(1);
				
				PreparedStatement insertRating = con.prepareStatement("INSERT INTO dealerRating (rates, ratedBy, rating) "
						+ "VALUES (?, ?, ?);");

				SQLWarning warning = insertRating.getWarnings();
				while (warning != null) {
					System.err.println("Database warning: " + warning);
				}

				insertRating.setInt(1, dealerId);
				insertRating.setInt(2, userId);
				insertRating.setInt(3, ratingValue);
				int insertCount = insertRating.executeUpdate();
				SQLWarning insertWarning = insertRating.getWarnings();
				while (insertWarning != null) {
					System.err.println("Update warning: " + insertWarning);
				}
				if (insertCount != 1) {
					System.out.println("No rating Inserted");
				} else {
					System.out.println("New rating has been successfully Inserted!");
				}
				
			} else {
				System.out.println("No Dealer has Name: " + dealerName);
			}
		} else {
			System.out.println("No User has Name: " + userName);
		}
		

	}

	public void returnSuggestions(String carAttributeToSearch, String attributeValue) throws Exception {
		PreparedStatement getAllApplicableVehicles = determineWhichQueryForSuggestions(carAttributeToSearch,
				attributeValue);

		SQLWarning warning = getAllApplicableVehicles.getWarnings();
		while (warning != null) {
			System.err.println("Database warning: " + warning);
		}
		try {
			rs = getAllApplicableVehicles.executeQuery();
			SQLWarning queryWarning = getAllApplicableVehicles.getWarnings();
			while (queryWarning != null) {
				System.err.println("Query warning: " + queryWarning);
			}
			if (rs.next()) {
				do{
					String model = rs.getString(1);
					String carBodyType = rs.getString(2);
					String modelYear = rs.getString(3);
					String transmissionType = rs.getString(4);
					String spare = rs.getString(5);
					int maximumSeatingCapacity = rs.getInt(6);
					String driveTrainType = rs.getString(7);

					System.out.println("model: " + model + " |carBodyType: " + carBodyType + " |modelYear: " + modelYear
							+ " |transmission Type: " + transmissionType + " |spare: " + spare
							+ " |maximumSeatingCapacity: " + maximumSeatingCapacity + " |driveTraingType: "
							+ driveTrainType);
				} while(rs.next());
			} else {
				System.out.println("No Vehicle Suggestions with provided attribute value. Please Try Again!");
				Menu reset = new Menu();
				reset.getVehicleSuggestions();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getAllApplicableVehicles.close();
		}
	}

	public PreparedStatement determineWhichQueryForSuggestions(String columnName, String attributeValue)
			throws Exception {

		PreparedStatement getAllApplicableVehicles = null;

		switch (columnName) {
		case "model":
			getAllApplicableVehicles = con.prepareStatement("select model, carBodyType, modelYear"
					+ ", transmissionType, spare, maximumSeatingCapacity, driveTrainType from vehicle v where model= ?");
			break;
		case "carBodyType":
			getAllApplicableVehicles = con.prepareStatement("select model, carBodyType, modelYear"
					+ ", transmissionType, spare, maximumSeatingCapacity, driveTrainType from vehicle v where carBodyType= ?");
			break;
		case "modelYear":
			getAllApplicableVehicles = con.prepareStatement("select model, carBodyType, modelYear"
					+ ", transmissionType, spare, maximumSeatingCapacity, driveTrainType from vehicle v where modelYear= ?");
			break;
		case "spare":
			getAllApplicableVehicles = con.prepareStatement("select model, carBodyType, modelYear"
					+ ", transmissionType, spare, maximumSeatingCapacity, driveTrainType from vehicle v where spare= ?");
			break;
		case "maximumSeatingCapacity":
			getAllApplicableVehicles = con.prepareStatement("select model, carBodyType, modelYear"
					+ ", transmissionType, spare, maximumSeatingCapacity, driveTrainType from vehicle v where maximumSeatingCapacity= ?");
			break;
		case "driveTrainType":
			getAllApplicableVehicles = con.prepareStatement("select model, carBodyType, modelYear"
					+ ", transmissionType, spare, maximumSeatingCapacity, driveTrainType from vehicle v where driveTrainType= ?");
			break;
		case "transmissionType":
			getAllApplicableVehicles = con.prepareStatement("select model, carBodyType, modelYear"
					+ ", transmissionType, spare, maximumSeatingCapacity, driveTrainType from vehicle v where transmissionType= ?");
			break;
		default:
			System.out.println("The provided car attribute was not found in the database, please try again.");
			Menu reset = new Menu();
			reset.selectUser();
			break;
		}

		getAllApplicableVehicles.setString(1, attributeValue);
		return getAllApplicableVehicles;
	}

	public void locateVehicle(String carModel) throws SQLException, IOException {

		PreparedStatement locate = con.prepareStatement(
				"SELECT d.name, d.zipcode FROM dealer d JOIN inventory i ON i.carriedBy=d.id JOIN vehicle v ON v.id=i.carries WHERE v.model=?;");

		locate.setString(1, carModel);

		SQLWarning warning = locate.getWarnings();
		while (warning != null) {
			System.err.println("Database warning: " + warning);
		}
		try {
			rs = locate.executeQuery();
			SQLWarning queryWarning = locate.getWarnings();
			while (queryWarning != null) {
				System.err.println("Query warning: " + queryWarning);
			}
			if (rs.next()) {
				do{
					String dealerName = rs.getString(1);
					String zipCode = rs.getString(2);

					System.out.println("dealer Name: " + dealerName + " | zipCode: " + zipCode);
				} while(rs.next());
			} else {
				System.out.println("No Dealer carries a vehicle with Model Name: " + carModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			locate.close();
		}
	}

	public void queryVehicle(String manufacturerName) throws Exception {
		PreparedStatement getVehicles = con.prepareStatement("select * from vehicle v, manufacturer m"
				+ " where v.manufacturedBy = m.id and m.name=?");
		SQLWarning warning = getVehicles.getWarnings();
		while (warning != null) {
			System.err.println("Database warning: " + warning);
		}
		try {
			getVehicles.setString(1, manufacturerName);
			ResultSet rs = getVehicles.executeQuery();
			SQLWarning queryWarning = getVehicles.getWarnings();
			while (warning != null) {
				System.err.println("Query warning: " + queryWarning);
			}
			if (rs.next()) {
				do{
					System.out.println("Model: " + rs.getString(2) + " | Body Type: " + rs.getString(3) + 
							" | Model Year: " + rs.getString(4));
				}
				while(rs.next());
			} else {
				System.out.println("Sorry! We don't have any vehicles for Manufacturer : " + manufacturerName);
			}
		} finally {
			getVehicles.close();
		}

	}
}