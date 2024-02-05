package customer.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APICaller {

    private static final String BASE_URL = "http://localhost:8080/CustomerManagementSystem/api";

    // Method to make GET requests with Bearer token
    public static String makeGetRequest(String endpoint, String token) throws Exception {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        // Set the request method
        connection.setRequestMethod("GET");

        // Set the Bearer token in the Authorization header
        connection.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode = connection.getResponseCode();

        // Check if the request was successful (HTTP 200)
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            // Handle the error response
            System.out.println("Error: " + responseCode);
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        // Assume you have obtained the token after the login
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNzA2OTY5NjQyfQ.S0PBvhiy4mpF93C0mBKawhahQhONshSH4gtkA_fa1hs";

        // Make API call to get customer list
        String customerListResponse = makeGetRequest("/customers", token);

        // Process the response as needed
        System.out.println(customerListResponse);
    }
}