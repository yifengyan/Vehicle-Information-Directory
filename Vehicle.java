import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Vehicle {

	private int id;
	private String model;
	private String carBodyType;
	private int modelYear;
	private String transmissionType;
	private String spare;
	private int maximumSeatingCapacity;
	private String driveTrain;
	private int addedOrRemovedBy;
	private int manufacturedBy;
	private Connection connection;
    private Manufacturer manu=new Manufacturer();
	private Set<User> user=new HashSet<User>();
	private Set<Dealer> dealer=new HashSet<Dealer>();
	
	@ManyToMany(cascade={CascadeType.PERSIST})  
	@JoinTable(name = "Inventory", 
    joinColumns = {@JoinColumn(name = "id")}, 
    inverseJoinColumns = {@JoinColumn(name = "id")}) 
	public Set<Dealer> getDealer() {
		return dealer;
	}

	public void setDealer(Set<Dealer> dealer) {
		this.dealer = dealer;
	}

	@ManyToMany(cascade={CascadeType.PERSIST})  	
	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	public Manufacturer getManu() {
		return manu;
	}

	public void setManu(Manufacturer manu) {
		this.manu = manu;
	}

	

	/**
	 * Construct Vehicle object.
	 */
	public Vehicle() {
	}

	/**
	 * Get the identifier of the vehicle.
	 * 
	 * @return The vehicle id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the identifier of an vehicle.
	 * 
	 * @param id
	 *            The vehicle identifier.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the model of the vehicle.
	 * 
	 * @return The vehicle model.
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Set the name of an vehicle.
	 * 
	 * @param name
	 *            The name identifier.
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Get the carBodyType of the vehicle.
	 * 
	 * @return The vehicle carBodyType.
	 */
	public String getcarBodyType() {
		return carBodyType;
	}

	/**
	 * Set the carBodyType of an vehicle.
	 * 
	 * @param carBodyType
	 *            The vehicle carBodyType.
	 */
	public void setPosition(String carBodyType) {
		this.carBodyType = carBodyType;
	}

	/**
	 * Get the modelYear of the vehicle.
	 * 
	 * @return The vehicle modelYear.
	 */
	public int modelYear() {
		return modelYear;
	}

	/**
	 * Set the modelYear of an vehicle.
	 * 
	 * @param Address
	 *            The vehicle modelYear.
	 */
	public void setmodelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	/**
	 * Get the transmissionType of the vehicle.
	 * 
	 * @return The vehicle transmissionType.
	 */
	public String gettransmissionType() {
		return transmissionType;
	}

	/**
	 * Set the transmissionType of an vehicle.
	 * 
	 * @param transmissionType
	 *            The vehicle transmissionType.
	 */
	public void settransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}

	/**
	 * Get the spare of the vehicle.
	 * 
	 * @return The vehicle spare.
	 */
	public String getspare() {
		return spare;
	}

	/**
	 * Set the spare of an vehicle.
	 * 
	 * @param spare
	 *            The vehicle spare.
	 */
	public void setspare(String spare) {
		this.spare = spare;
	}
	
	/**
	 * Get the maximumSeatingCapacity of the vehicle.
	 * 
	 * @return The vehicle maximumSeatingCapacity.
	 */
	public int getmaximumSeatingCapacity() {
		return maximumSeatingCapacity;
	}

	/**
	 * Set the maximumSeatingCapacity of an vehicle.
	 * 
	 * @param maximumSeatingCapacity
	 *            The vehicle maximumSeatingCapacity.
	 */
	public void setmaximumSeatingCapacity(int maximumSeatingCapacity) {
		this.maximumSeatingCapacity = maximumSeatingCapacity;
	}
	
	/**
	 * Get the driveTrain of the vehicle.
	 * 
	 * @return The vehicle driveTrain.
	 */
	public String getdriveTrain() {
		return driveTrain;
	}

	/**
	 * Set the driveTrain of an vehicle.
	 * 
	 * @param driveTrain
	 *            The vehicle driveTrain.
	 */
	public void setdriveTrain(String driveTrain) {
		this.driveTrain = driveTrain;
	}
}