import com.mysql.jdbc.Connection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

public class Dealer {

	private int id;
	private String name;
	private String city;
	private String streetAddress;
	private String zipCode;
	private String cordinates;
	private boolean franchiseDealer;
	private String contactNumber;
	private Connection connection;
    private Set<Vehicle> vehicle=new HashSet<Vehicle>();
	private Set<User> user=new HashSet<User>();
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="order")
	@JoinColumn(name="id")
	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}
	
    @ManyToMany(cascade={CascadeType.PERSIST}) 
	@JoinTable(name = "Inventory", 
    joinColumns = {@JoinColumn(name = "id")}, 
    inverseJoinColumns = {@JoinColumn(name = "id")}) 
	public Set<Vehicle> getVehicle() {
		return vehicle;
	}

	public void setVehicle(Set<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}
	/**
	 * Construct dealer object.
	 */
	public Dealer() {
	}

	/**
	 * Get the identifier of the dealer.
	 * 
	 * @return The dealer id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the identifier of an dealer.
	 * 
	 * @param id
	 *            The dealer identifier.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the name of the dealer.
	 * 
	 * @return The dealer name.
	 */
	public String getname() {
		return name;
	}

	/**
	 * Set the name of an dealer.
	 * 
	 * @param name
	 *            The name identifier.
	 */
	public void setModel(String name) {
		this.name = name;
	}

	/**
	 * Get the city of the dealer.
	 * 
	 * @return The dealer city.
	 */
	public String getcity() {
		return city;
	}

	/**
	 * Set the city of an dealer.
	 * 
	 * @param city
	 *            The dealer city.
	 */
	public void setcity(String city) {
		this.city = city;
	}

	/**
	 * Get the streetAddress of the dealer.
	 * 
	 * @return The dealer streetAddress.
	 */
	public String getstreetAddress() {
		return streetAddress;
	}

	/**
	 * Set the streetAddress of an dealer.
	 * 
	 * @param streetAddress
	 *            The dealer streetAddress.
	 */
	public void setstreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * Get the zipCode of the dealer.
	 * 
	 * @return The dealer zipCode.
	 */
	public String getzipCode() {
		return zipCode;
	}

	/**
	 * Set the zipCode of an dealer.
	 * 
	 * @param zipCode
	 *            The dealer zipCode.
	 */
	public void setzipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Get the cordinates of the dealer.
	 * 
	 * @return The dealer cordinates.
	 */
	public String getcordinates() {
		return cordinates;
	}

	/**
	 * Set the cordinates of an dealer.
	 * 
	 * @param cordinates
	 *            The dealer cordinates.
	 */
	public void setcordinates(String cordinates) {
		this.cordinates = cordinates;
	}

	/**
	 * Get the franchiseDealer indicator of the dealer.
	 * 
	 * @return The dealer franchiseDealer indicator.
	 */
	public boolean getfranchiseDealer() {
		return franchiseDealer;
	}

	/**
	 * Set the franchiseDealer indicator of an dealer.
	 * 
	 * @param franchiseDealer
	 *            The dealer franchiseDealer.
	 */
	public void setfranchiseDealer(boolean franchiseDealer) {
		this.franchiseDealer = franchiseDealer;
	}

	/**
	 * Get the contactNumber of the dealer.
	 * 
	 * @return The dealer contactNumber.
	 */
	public String getcontactNumber() {
		return contactNumber;
	}

	/**
	 * Set the contactNumber of an dealer.
	 * 
	 * @param contactNumber
	 *            The dealer contactNumber.
	 */
	public void setcontactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/*******************************************************************************
	 * dealerRating information.
	 ******************************************************************************/
	public class dealerRating {

		private int id;
		private String rating;

		private Connection connection;

		/**
		 * Construct dealerRating object.
		 */
		public dealerRating() {
		}

		/**
		 * Get the identifier of the dealerRating.
		 * 
		 * @return The dealerRating id.
		 */
		public int getId() {
			return id;
		}

		/**
		 * Set the identifier of a dealerRating.
		 * 
		 * @param id
		 *            The dealerRating identifier.
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * Set the rating of an dealer.
		 * 
		 * @param rating
		 *            The dealer rating.
		 */
		public void setrating(String rating) {
			this.rating = rating;
		}

		/**
		 * Get the rating indicator of the dealer.
		 * 
		 * @return The dealer rating indicator.
		 */
		public String getrating() {
			return rating;
		}
	}
	
	/*******************************************************************************
	 * Inventory information.
	 ******************************************************************************/
	public class Inventory {

		private int id;
		private int stock;

		private Connection connection;

		/**
		 * Construct Inventory object.
		 */
		public Inventory() {
		}

		/**
		 * Get the identifier of the Inventory.
		 * 
		 * @return The Inventory id.
		 */
		public int getId() {
			return id;
		}

		/**
		 * Set the identifier of an Inventory.
		 * 
		 * @param id
		 *            The Inventory identifier.
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * Set the stock of an Inventory item.
		 * 
		 * @param stock
		 *            The Inventory item stock.
		 */
		public void setstock(int stock) {
			this.stock = stock;
		}

		/**
		 * Get the stock of the Inventory item.
		 * 
		 * @return The Inventory stock.
		 */
		public int getstock() {
			return stock;
		}
	}
}