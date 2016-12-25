import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

//Author: Harshali Singh, Ali Alhady
public class AdminDAO {

	private Connection con;
	private ResultSet rs;

	public AdminDAO(Connection con) {
		this.con = con;
	}

	/**
	 * @throws SQLException
	 */
	public void removeOutOfStockVehicles() throws SQLException {

		PreparedStatement checkVehicleToRemove = con.prepareStatement("select * from inventory where stock = 0");
		PreparedStatement removeVehicle = con.prepareStatement("delete from inventory where stock = 0");
		SQLWarning warning = removeVehicle.getWarnings();
		while (warning != null) {
			System.err.println("Database warning: " + warning);
		}
		try {
			rs = checkVehicleToRemove.executeQuery();
			SQLWarning queryWarning = checkVehicleToRemove.getWarnings();
			while (warning != null) {
				System.err.println("Query warning: " + queryWarning);
			}
			if (!rs.next()) {
				System.out.println("No Vehicle to delete from Inventory table!");
			} else {
				// execute delete SQL statement
				removeVehicle.executeUpdate();
				System.out.println("Vehicle is deleted from Inventory table!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			removeVehicle.close();
		}
	}

	/**
	 * 
	 * @param dealerName. name of the dealer whose contact number has to be retrieved
	 * @return String returns contact number of the dealer
	 * @throws SQLException 
	 * @throws Exception
	 */
	public void getCurrentContact(String dealerName) throws SQLException {
		PreparedStatement getContact = con.prepareStatement("select contactNumber from Dealer where name=?");
		SQLWarning warning = getContact.getWarnings();
		while (warning != null) {
			System.err.println("Database warning: " + warning);
		}
		try {
			getContact.setString(1, dealerName);
			ResultSet rs = getContact.executeQuery();
			SQLWarning queryWarning = getContact.getWarnings();
			while (queryWarning != null) {
				System.err.println("Query warning: " + queryWarning);
			}
			if(rs.next()){
				System.out.println("Current contact number: " + rs.getString(1));
			} else {
				System.out.println("No dealer has name " + dealerName);
			}
		} finally {
			getContact.close();
		}
	}

	/**
	 * 
	 * @param currContact. current contact number of the dealer
	 * @param newContact. new contact number of the dealer
	 * @throws SQLException
	 */
	public void updateContact(String dealerName, String newContact) throws SQLException {
		PreparedStatement updateContactNumber = con
				.prepareStatement("update Dealer set contactNumber=? where name=?");
		SQLWarning warning = updateContactNumber.getWarnings();
		while (warning != null) {
			System.err.println("Database warning: " + warning);
		}
		try {
			updateContactNumber.setString(1, newContact);
			updateContactNumber.setString(2, dealerName);
			int updateCount = updateContactNumber.executeUpdate();
			SQLWarning updateWarning = updateContactNumber.getWarnings();
			while (updateWarning != null) {
				System.err.println("Update warning: " + updateWarning);
			}
			if (updateCount != 1) {
				System.out.println("No dealer has name " + dealerName);
			} else {
				System.out.println("Contact updated. New Contact Number: " + newContact);
			}
		} finally {
			updateContactNumber.close();
		}
	}

	/**
	 * 
	 * @param dealerName. Name of the dealer to be removed from the database.
	 * @throws SQLException. if the provided SQL database cannot be accessed
	 * @throws IOException. if the provided input cannot be properly read
	 */
	public void removeDealer(String dealerName) throws SQLException, IOException {

		PreparedStatement updateContactNumber = con.prepareStatement("SELECT * FROM dealer where name= ?");
		updateContactNumber.setString(1, dealerName);
		ResultSet validDealer = updateContactNumber.executeQuery();
		if (validDealer.next()) {
			// Remove any entries from the user table first which purchased from
			// this dealer
			PreparedStatement removeUserPurchaseEntry = con.prepareStatement(
					"DELETE FROM `user` WHERE purchasesFrom IN (SELECT id FROM dealer WHERE name = ?);");

			SQLWarning warning = removeUserPurchaseEntry.getWarnings();
			while (warning != null) {
				System.err.println("Database warning: " + warning);
			}

			removeUserPurchaseEntry.setString(1, dealerName);
			removeUserPurchaseEntry.executeUpdate();

			// Remove the corresponding dealer
			PreparedStatement removeDealer = con.prepareStatement("DELETE FROM dealer WHERE name = ?;");

			SQLWarning warningDealer = removeDealer.getWarnings();
			while (warningDealer != null) {
				System.err.println("Database warning: " + warningDealer);
			}

			removeDealer.setString(1, dealerName);
			removeDealer.executeUpdate();

			System.out.println("Successully removed Dealer: " + dealerName);
		} else {
			System.out.println("No dealer with this name found.");
		}
	}

	public void insertVehicle(String manufacturerName, String modelName, String bodyType, int modelYear,
			String transmissionType, String spare, int maxCapacity, String driveTrainType, int adminID) throws SQLException {

		PreparedStatement getManufacturerID = con.prepareStatement("select m.id from manufacturer m where m.name=?");
		getManufacturerID.setString(1, manufacturerName);
		ResultSet validManufacturerID = getManufacturerID.executeQuery();


		PreparedStatement getVehicleID = con.prepareStatement("SELECT count(*) FROM vehicle");
		ResultSet vehicleID = getVehicleID.executeQuery();

		PreparedStatement insertVehicle = null;
		int manufacturerID = 0;
		int vehicleId = 0;
		try{
			if(validManufacturerID.next()){
				manufacturerID = validManufacturerID.getInt(1);
				if(vehicleID.next()){
					vehicleId = vehicleID.getInt(1) + 1;
				}
				insertVehicle = 
						con.prepareStatement("insert into vehicle "
								+ "(id, model, carBodyType, modelYear, transmissionType, spare, maximumSeatingCapacity, driveTrainType, addedOrRemovedBy, manufacturedBy) "
								+ "values (?,?,?,?,?,?,?,?,?,(select m.id from manufacturer m where m.name=?))");

				insertVehicle.setInt(1, vehicleId);
				insertVehicle.setString(2, modelName);
				insertVehicle.setString(3, bodyType);
				insertVehicle.setInt(4, modelYear);
				insertVehicle.setString(5, transmissionType);
				insertVehicle.setString(6, spare);
				insertVehicle.setInt(7, maxCapacity);
				insertVehicle.setString(8, driveTrainType);
				insertVehicle.setInt(9, adminID);
				insertVehicle.setInt(10, manufacturerID);
				
				int updateCount = insertVehicle.executeUpdate();
				SQLWarning updateWarning = insertVehicle.getWarnings();
				while (updateWarning != null) {
					System.err.println("Update warning: " + updateWarning);
				}
				if (updateCount != 1) {
					System.out.println("No vehicle Inserted");
				} else {
					System.out.println("New vehicle has been successfully Inserted!");
				}
			} else {
				System.out.println("Sorry! Vehicles of "+ manufacturerName +" cannot be added");
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (insertVehicle != null) {
				insertVehicle.close();
			}
		}

	}

}
