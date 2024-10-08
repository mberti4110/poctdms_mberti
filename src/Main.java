import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static PatientList patients = new PatientList();
    private static TestList tests = new TestList();
    private static FileManager fileManager = new FileManager();
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
        // Login screen
        while (!isAuthenticated) {
            if (login()) {
                isAuthenticated = true;
                mainMenu();
            } else {
                System.out.println("Invalid credentials, please try again.");
            }
        }
        scanner.close();
    }

    private static boolean login() {
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
    // Main Menu
    private static void mainMenu() {
        boolean running = true;
        while (running) {
            menu.displayMenu();
            //Menu Selection
            int choice = menu.selectOption();
            switch (choice) {
                case 1:
                    addPatient(scanner);
                    break;
                case 2:
                    addTest(scanner);
                    break;
                case 3:
                    listPatients();
                    break;
                case 4:
                    listTests();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    //Menu option 1: Add Patient
    private static void addPatient(Scanner scanner) {
        System.out.println("Select input method: 1 for manual, 2 for file");
        int inputMethod = scanner.nextInt();
        if (inputMethod == 1) {
            // Existing manual data entry code here
            try {
                System.out.println("Enter first name:");
                String firstName = scanner.next();

                System.out.println("Enter middle name (or type 'none' if no middle name):");
                String middleName = scanner.next();
                middleName = "none".equalsIgnoreCase(middleName) ? null : middleName;

                System.out.println("Enter last name:");
                String lastName = scanner.next();

                System.out.println("Enter date of birth (format YYYY-MM-DD):");
                String dateInput = scanner.next();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfBirth = sdf.parse(dateInput);

                System.out.println("Enter phone number:");
                String phoneNumber = scanner.next();

                System.out.println("Enter home address:");
                scanner.nextLine();  // Consume newline left-over
                String homeAddress = scanner.nextLine();

                Patient patient = new PatientBuilder(lastName, firstName)
                        .patientMiddleName(middleName)
                        .dateOfBirth(dateOfBirth)
                        .phoneNumber(phoneNumber)
                        .homeAddress(homeAddress)
                        .build();

                patients.addPatient(patient);
                System.out.println("Patient added successfully!");
            } catch (Exception e) {
                System.out.println("Failed to add patient: " + e.getMessage());
                scanner.nextLine();  // Consume buffer to clear out bad input
            }
        } else if (inputMethod == 2) {
            System.out.println("Enter file path:");
            String filePath = scanner.next();
            try {
                readPatientFromFile(filePath);
            } catch (Exception e) {
                System.out.println("Failed to read patients from file: " + e.getMessage());
            }
        }
    }

    //Menu option 1: Add patient via file
    private static void readPatientFromFile(String filePath) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            // File format: ID,LastName,FirstName,MiddleName,DOB,Phone,Address
            String lastName = parts[0].trim();
            String firstName = parts[1].trim();
            String middleName = parts[2].trim().equals("none") ? null : parts[3].trim();
            Date dateOfBirth = sdf.parse(parts[3].trim());
            String phoneNumber = parts[4].trim();
            String homeAddress = parts[5].trim();

            Patient patient = new PatientBuilder(lastName, firstName)
                    .patientMiddleName(middleName)
                    .dateOfBirth(dateOfBirth)
                    .phoneNumber(phoneNumber)
                    .homeAddress(homeAddress)
                    .build();
            patients.addPatient(patient);
        }
        reader.close();
        System.out.println("Patients added successfully from file!");
    }

    //Menu option 2: Add test
    private static void addTest(Scanner scanner) {
        System.out.println("Select Test Type:");
        for (TestType type : TestType.values()) {
            System.out.println((type.ordinal() + 1) + ". " + type);
        }
        int choice = Integer.parseInt(scanner.nextLine()) - 1;
        if (choice < 0 || choice >= TestType.values().length) {
            System.out.println("Invalid test type selected. Please try again.");
            return;
        }
        TestType selectedType = TestType.values()[choice];

        // Initialize parameters map
        Map<String, Object> parameters = new HashMap<>();

        // TestID is generated
        parameters.put("testID", TestList.getNextTestId());

        System.out.println("Enter Patient ID:");
        int patientID = Integer.parseInt(scanner.nextLine());
        // Check if patient exists
        if (!patients.exists(patientID)) {
            System.out.println("No patient found with ID: " + patientID + ". Please add the patient first.");
            return;
        }
        parameters.put("patientID", patientID);
        System.out.println("Enter Device Type:");
        parameters.put("deviceType", scanner.nextLine());

        System.out.println("Enter Device Serial Number:");
        parameters.put("deviceID", scanner.nextLine());

        System.out.println("Enter Operator ID:");
        parameters.put("operatorID", Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter Test Date and Time (format YYYY-MM-DD HH:MM):");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            parameters.put("testDateTime", sdf.parse(scanner.nextLine()));
        } catch (ParseException e) {
            System.out.println("Invalid date and time format. Please enter the date and time in the format YYYY-MM-DD HH:MM.");
            return;
        }

        System.out.println("Enter Test Result (as appropriate for the test type):");
        // Parse the result depending on the test type (int or float)
        String result = scanner.nextLine();
        try {
            if (selectedType == TestType.GLUCOSE || selectedType == TestType.SODIUM || selectedType == TestType.CHLORIDE) {
                parameters.put(selectedType.name().toLowerCase() + "Result", Integer.parseInt(result));
            } else {
                parameters.put(selectedType.name().toLowerCase() + "Result", Float.parseFloat(result));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid format for test result. Please enter a valid number.");
            return;
        }

        try {
            Test test = TestFactory.createTest(selectedType, parameters);
            tests.addTest(test);
            System.out.println("Test added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating test: " + e.getMessage());
        }
    }


    private static void listPatients() {
        System.out.println("Listing all patients:");
        patients.listPatients().values().forEach(System.out::println);
    }

    private static void listTests() {
        System.out.println("Listing all tests:");
        tests.listTests().values().forEach(System.out::println);
    }
}
