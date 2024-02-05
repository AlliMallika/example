package customer.dao;

public interface LoginDAO {

    // Validate login and return a validation code (0 or 1)
    int validateLogin(String username, String password);
}