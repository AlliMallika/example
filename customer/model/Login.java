package customer.model;

public class Login {
	private int UserId;
    private String Username;
    private String Password;
    
    
 // Constructors (you can have multiple constructors as needed)
    
    public Login() {
    	 // Default constructor
    }
	public Login(String Username, String Password) {
		
		this.Username = Username;
		this.Password = Password;
	}
	
	
	// Getter and Setter methods for each field
	
	
	public String getUsername() {
		return Username;
	}
//	public int getUserId() {
//		return UserId;
//	}
//	public void setUserId(int userId) {
//		UserId = userId;
//	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "Login [UserId=" + UserId + ", Username=" + Username + ", Password=" + Password + "]";
	}
	
	
    
}