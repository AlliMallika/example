package customer.util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenGenerator {

    public static void main(String[] args) {
        try {
            // Replace with your actual secret key
            String secretKey = KeyGenerator.generateSecretKey();
            // Replace with your actual username and password
            String username = "john_doe";
            String password = "secure_password123";

            // Generate JWT token
            String jwtToken = generateJwtToken(username, password, secretKey);

            System.out.println("JWT Token: " + jwtToken);

            // Use the obtained JWT token for authentication

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String generateJwtToken(String username, String password, String secretKey) {
        // Set the expiration time for the token (e.g., 1 hour)
        long expirationMillis = System.currentTimeMillis() + 3600000; // 1 hour

        // Create a map to store the claims (e.g., username, password)
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("password", password);

        // Build the JWT token
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(expirationMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}