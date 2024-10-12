import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Menu menu = new Menu();

    //User Login. To change with database incorporation.
    private static Map<Integer, User> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isAuthenticated = false;

    static {
        // Admin users
        users.put(1, new User(1, "Berti", "Monica", "password123"));
        users.put(2, new User(2, "Admin", "User", "admin123"));
    }

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

    private static boolean login() {
        System.out.println("Login Screen:");
        System.out.println("Enter User ID:");
        int id = Integer.parseInt(scanner.nextLine());  // Read user ID
        System.out.println("Enter Password:");
        String password = scanner.nextLine();  // Read password

        User user = users.get(id);
        if (user != null && user.getOperatorPassword().equals(password)) {
            System.out.println("Login successful. Welcome, " + user.getOperatorFirstName() + "!");
            return true;
        }
        return false;
    }
}