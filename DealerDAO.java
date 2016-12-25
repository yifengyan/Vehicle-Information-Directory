import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//Author: Harshali Singh, Meha Verma

public class DealerDAO {

	private Connection con;
	private ResultSet rs;

	public DealerDAO(Connection con) {
		this.con = con;
	}

	public Set<String> getAllVehicles(String dealername) throws SQLException {
		Set<String> vehiclesList = new HashSet<String>();
		PreparedStatement getAllVehiclesQuery = con.prepareStatement(
				"select v.id from vehicle v, Dealer d, Inventory i where d.name = ? and d.id = i.carriedBy and v.id = i.carries");

		getAllVehiclesQuery.setString(1, dealername);

		SQLWarning warning = getAllVehiclesQuery.getWarnings();
		while (warning != null) {
			System.err.println("Database warning: " + warning);
		}
		try {
			rs = getAllVehiclesQuery.executeQuery();
			SQLWarning queryWarning = getAllVehiclesQuery.getWarnings();
			while (queryWarning != null) {
				System.err.println("Query warning: " + queryWarning);
			}
			ArrayList<Integer> vehiclesIdList = new ArrayList<>();
			if (rs.next()) {
				do{
					int vehicleId = rs.getInt(1);
					vehiclesIdList.add(vehicleId);
				} while(rs.next());

				// now get all the vehicles from vehicle table using all these ids
				for (int vid : vehiclesIdList) {
					PreparedStatement getVehicleQuery = con.prepareStatement("select * from vehicle v where v.id = ? ");

					getVehicleQuery.setInt(1, vid);
					rs = getVehicleQuery.executeQuery();

					while (rs.next()) {
						String vehicleId = rs.getString(1);
						String model = rs.getString(2);
						String carBodyType = rs.getString(3);
						int modelYear = rs.getInt(4);
						String transmissionType = rs.getString(5);
						String spare = rs.getString(6);
						int maximumSeatingCapacity = rs.getInt(7);
						String driveTrainType = rs.getString(8);

						String carDetails = "vehicleId:" + vehicleId + " | model:" + model + " | carBodyType:" + carBodyType
								+ " | model year:" + modelYear + " | transmissionType:" + transmissionType + " | spare:" + spare
								+ " | maximum Seating Capacity:" + maximumSeatingCapacity + " | driveTrainType:"
								+ driveTrainType;

						vehiclesList.add(carDetails);

					}
				} 
			}
			else {
				System.out.println("Sorry! The Dealer " + dealername + " currently does not offer any vehicles.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getAllVehiclesQuery.close();
		}

		return vehiclesList;
	}

	public void submitVehicle(int dealerId, int vehicleId, int stock) throws SQLException {

		PreparedStatement checkVehicleInInventory = con.prepareStatement("SELECT * FROM inventory where carries=? and carriedBy=?;");
		checkVehicleInInventory.setInt(1, vehicleId);
		checkVehicleInInventory.setInt(2, dealerId);
		ResultSet vehicleInInventory = checkVehicleInInventory.executeQuery();

		PreparedStatement updateStock = null; 
		PreparedStatement insertStock = null;

		try{
			if(vehicleInInventory.next()){
				int existingStock = vehicleInInventory.getInt(3);
				int newStock = existingStock + stock;
				updateStock = con
						.prepareStatement("update inventory set stock=? where carries=? and carriedBy=?");

				updateStock.setInt(1, newStock);
				updateStock.setInt(2, vehicleId);
				updateStock.setInt(3, dealerId);

				int updateCount = updateStock.executeUpdate();
				SQLWarning updateWarning = updateStock.getWarnings();
				while (updateWarning != null) {
					System.err.println("Update warning: " + updateWarning);
				}
				if (updateCount != 1) {
					System.out.println("Inventory did not get updated");
				} else {
					System.out.println("Dealer with ID " + dealerId + " now carries " + newStock + " Vehicles with ID " + vehicleId);
				}
			} else {
				insertStock = con
						.prepareStatement("insert into inventory (carries, carriedBy, stock) "
								+ "values (?,?,?)");

				insertStock.setInt(1, stock);
				insertStock.setInt(2, vehicleId);
				insertStock.setInt(3, dealerId);

				int updateCount = insertStock.executeUpdate();
				SQLWarning updateWarning = insertStock.getWarnings();
				while (updateWarning != null) {
					System.err.println("Update warning: " + updateWarning);
				}
				if (updateCount != 1) {
					System.out.println("Inventory did not get updated");
				} else {
					System.out.println("Dealer " + dealerId + " now carries " + stock + " Vehicles with ID " + vehicleId);
				}
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (updateStock != null) {
				updateStock.close();
			}

			if (insertStock != null) {
				insertStock.close();
			}
		}

	}
}
