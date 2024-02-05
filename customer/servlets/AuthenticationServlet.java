package customer.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.dao.impl.LoginDAOImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import customer.dao.CustomerDAO;
import customer.dao.LoginDAO;
import customer.util.KeyGenerator; // Import the KeyGenerator class

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet("/api/auth")
public class AuthenticationServlet extends HttpServlet {
    private final LoginDAO loginDAO = LoginDAOImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Parse request parameters
        String loginId = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate the credentials using LoginDAO
        if (isValidUser(loginId, password)) {
            // Generate a Bearer token with dynamically generated secret key
            String token = generateToken();

            // Return the token in the response
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"token\": \"" + token + "\"}");
            out.flush();
        } else {
            // Invalid credentials
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private boolean isValidUser(String loginId, String password) {
        // Use the LoginDAO to validate the user
        return loginDAO.validateLogin(loginId, password) == 1;
    }

    private String generateToken() {
        // Generate a unique Bearer token using JJWT library with dynamically generated secret key
        String secretKey = KeyGenerator.generateSecretKey();

        // Set the expiration time for the token (e.g., 1 hour)
        long expirationMillis = System.currentTimeMillis() + 3600000; // 1 hour

        return Jwts.builder()
                .setSubject("user") // You can set any subject as per your application requirements
                .setExpiration(new Date(expirationMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}