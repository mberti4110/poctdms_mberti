/* DMS Project: Point of Care
 * October 13, 2024
 * CEN 3024C
 * Monica Berti
 * Main Class
 * This program will allow users to add Point of Care Tests to a database management system.
 */

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Menu menu = new Menu();

    // User Login. To change with database incorporation.
    private static Map<Integer, User> users = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);
    private static boolean isAuthenticated = false;

    // Built in users
    static {
        // Admin users
        users.put(1, new User(1, "Berti", "Monica", "password123"));
        users.put(2, new User(2, "Admin", "User", "admin123"));
    }

    // Main method
    public static void main(String[] args) {
        // Display the login screen until the user is authenticated
        while (!isAuthenticated) {
            if (login()) {
                isAuthenticated = true;
                menu.mainMenuLoop();
            } else {
                System.out.println("Invalid credentials, please try again.");
            }
        }
        scanner.close(); // Close the scanner when the main loop ends
    }

    // Login screen method
    private static boolean login() {
        System.out.println("Login Screen:");

        while (true) {
            System.out.println("Enter User ID:");
            String userIdInput = scanner.nextLine().trim();

            // Initialize User ID
            int id = 0;

            // Validate and parse the User ID
            try {
                id = Integer.parseInt(userIdInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid User ID entered. Please enter a valid integer.");
                continue; // Skip the rest of the loop and prompt again
            }

            System.out.println("Enter Password:");
            String password = scanner.nextLine(); // Read password

            // Authenticate user
            User user = users.get(id);
            if (user != null && user.getOperatorPassword().equals(password)) {
                System.out.println("Login successful. Welcome, " + user.getOperatorFirstName() + "!");
                return true; // Successful login
            } else {
                System.out.println("Invalid credentials, please try again.");
                // If credentials are invalid, restart the process without exiting
            }
        }
    }
}