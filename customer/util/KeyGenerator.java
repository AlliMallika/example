package customer.util;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {
    public static String generateSecretKey() {
        byte[] keyBytes = new byte[64]; // Adjust the byte array size as needed
        new SecureRandom().nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    public static void main(String[] args) {
        String secretKey = generateSecretKey();
        System.out.println("Generated Secret Key: " + secretKey);
    }
}
