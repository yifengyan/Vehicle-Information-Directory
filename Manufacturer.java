import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


import com.mysql.jdbc.Connection;

public class Manufacturer {

	private int id;
	private String name;
	private String headquartersLocation;
	private Set<Vehicle> veh=new HashSet<Vehicle>();


	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="order")
	@JoinColumn(name="id")
	public Set<Vehicle> getVeh() {
		return veh;
	}

	public void setVeh(Set<Vehicle> veh) {
		this.veh = veh;
	}

	private Connection connection;

	/**
	 * Construct manufacturer object.
	 */
	public Manufacturer() {
	}

	/**
	 * Get the identifier of the Manufacturer.
	 * 
	 * @return The Manufacturer id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the identifier of an Manufacturer.
	 * 
	 * @param id
	 *            The Manufacturer identifier.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the name of the manufacturer.
	 * 
	 * @return The manufacturer name.
	 */
	public String getname() {
		return name;
	}

	/**
	 * Set the name of an manufacturer.
	 * 
	 * @param name
	 *            The name identifier.
	 */
	public void setname(String name) {
		this.name = name;
	}

	/**
	 * Get the headquartersLocation of the manufacturer.
	 * 
	 * @return The manufacturer headquartersLocation.
	 */
	public String getheadquartersLocation() {
		return headquartersLocation;
	}

	/**
	 * Set the headquartersLocation of a manufacturer.
	 * 
	 * @param headquartersLocation
	 *            The manufacturer headquartersLocation.
	 */
	public void setheadquartersLocation(String headquartersLocation) {
		this.headquartersLocation = headquartersLocation;
	}
}