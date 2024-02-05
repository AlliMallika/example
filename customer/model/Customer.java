package customer.model;

public class Customer {
    private int id;
    private String firstname;
    private String lastname;
    private String street;
    private String address;
    private String city;
    private String state;
    private String email;
    private long phone;
    
    
    
    
 // Fields for search parameters
    private String searchField;
    private String searchTerm;

    // Constructors (you can have multiple constructors as needed)

    public Customer() {
        // Default constructor
    }

    public Customer(int id, String firstname, String lastname, String street, String address, String city, String state, String email, long phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.state = state;
        this.email = email;
        this.phone = phone;
    }

    
    
 // Constructor for search parameters
    public Customer(String searchField, String searchTerm) {
        this.searchField = searchField;
        this.searchTerm = searchTerm;

        // Set the appropriate field based on the searchField
        if ("firstname".equalsIgnoreCase(searchField)) {
            this.firstname = searchTerm;
        } else if ("lastname".equalsIgnoreCase(searchField)) {
            this.lastname = searchTerm;
        } else if ("street".equalsIgnoreCase(searchField)) {
            this.street = searchTerm;
        } else if ("address".equalsIgnoreCase(searchField)) {
            this.address = searchTerm;
        } else if ("city".equalsIgnoreCase(searchField)) {
            this.city = searchTerm;
        } else if ("state".equalsIgnoreCase(searchField)) {
            this.state = searchTerm;
        } else if ("email".equalsIgnoreCase(searchField)) {
            this.email = searchTerm;
        } else if ("phone".equalsIgnoreCase(searchField)) {
            // Assuming phone is a long field
            try {
                this.phone = Long.parseLong(searchTerm);
            } catch (NumberFormatException e) {
                // Handle the exception or log it
            }
        } else {
            // Add similar checks for other fields if needed
        }
    }

    
    
    
    
    // Getter and Setter methods for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    
    
    
    public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    
    
    
    
    
    
    public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	@Override
    public String toString() {
        return "Customer [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", address=" + address
                + ", city=" + city + ", state=" + state + ", email=" + email + ", phone=" + phone + "]";
    }
}