import com.mysql.jdbc.Connection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

public class User {

	private int id;
	private String name;
	private String email;
    private Set<Vehicle> vehicle=new HashSet<Vehicle>();
    private Dealer dealer=new Dealer();
    
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
    public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	@ManyToMany(cascade={CascadeType.PERSIST})  	 
	public Set<Vehicle> getVehicle() {
		return vehicle;
	}

	public void setVehicle(Set<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

	
	private Connection connection;

	/**
	 * Construct User object.
	 */
	public User() {
	}

	/**
	 * Get the identifier of the User.
	 * 
	 * @return The User id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the identifier of an User.
	 * 
	 * @param id
	 *            The User identifier.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the name of the User.
	 * 
	 * @return The User name.
	 */
	public String getname() {
		return name;
	}

	/**
	 * Set the name of an User.
	 * 
	 * @param name
	 *            The name identifier.
	 */
	public void setname(String name) {
		this.name = name;
	}

	/**
	 * Get the headquartersLocation of the User.
	 * 
	 * @return The User headquartersLocation.
	 */
	public String getemail() {
		return email;
	}

	/**
	 * Set the email of a User.
	 * 
	 * @param email
	 *            The User email.
	 */
	public void setemail(String email) {
		this.email = email;
	}
	
	/*******************************************************************************
	 * GeneralUser information.
	 ******************************************************************************/
	public class GeneralUser {

		private int id;

		private Connection connection;

		/**
		 * Construct GeneralUser object.
		 */
		public GeneralUser() {
		}

		/**
		 * Get the identifier of the GeneralUser.
		 * 
		 * @return The GeneralUser id.
		 */
		public int getId() {
			return id;
		}

		/**
		 * Set the identifier of a GeneralUser.
		 * 
		 * @param id
		 *            The GeneralUser identifier.
		 */
		public void setId(int id) {
			this.id = id;
		}
	}
	
	/*******************************************************************************
	 * prospectiveBuyer information.
	 ******************************************************************************/
	public class prospectiveBuyer {

		private int id;
		private String personalNumber;

		private Connection connection;

		/**
		 * Construct prospectiveBuyer object.
		 */
		public prospectiveBuyer() {
		}

		/**
		 * Get the identifier of the prospectiveBuyer.
		 * 
		 * @return The prospectiveBuyer id.
		 */
		public int getId() {
			return id;
		}

		/**
		 * Set the identifier of a prospectiveBuyer.
		 * 
		 * @param id
		 *            The prospectiveBuyer identifier.
		 */
		public void setId(int id) {
			this.id = id;
		}
		
		/**
		 * Get the personalNumber of the prospectiveBuyer.
		 * 
		 * @return The prospectiveBuyer personalNumber.
		 */
		public String getpersonalNumber() {
			return personalNumber;
		}

		/**
		 * Set the personalNumber of a prospectiveBuyer.
		 * 
		 * @param personalNumber
		 *            The prospectiveBuyer personalNumber.
		 */
		public void setpersonalNumber(String personalNumber) {
			this.personalNumber = personalNumber;
		}
	}
	
	/*******************************************************************************
	 * Admin information.
	 ******************************************************************************/
	public class Admin {

		private int id;
		private String name;
		private String email;

		private Connection connection;

		/**
		 * Construct Admin object.
		 */
		public Admin() {
		}

		/**
		 * Get the identifier of the Admin.
		 * 
		 * @return The Admin id.
		 */
		public int getId() {
			return id;
		}

		/**
		 * Set the identifier of a Admin.
		 * 
		 * @param id
		 *            The Admin identifier.
		 */
		public void setId(int id) {
			this.id = id;
		}
		
		/**
		 * Get the name of the Admin.
		 * 
		 * @return The Admin name.
		 */
		public String getname() {
			return name;
		}

		/**
		 * Set the name of a Admin.
		 * 
		 * @param name
		 *            The Admin name.
		 */
		public void setname(String name) {
			this.name = name;
		}
		
		/**
		 * Get the email of the Admin.
		 * 
		 * @return The Admin email.
		 */
		public String getemail() {
			return email;
		}

		/**
		 * Set the email of a Admin.
		 * 
		 * @param email
		 *            The Admin email.
		 */
		public void setemail(String email) {
			this.email = email;
		}
	}
}