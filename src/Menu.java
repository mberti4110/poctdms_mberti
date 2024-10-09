import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner;
    private static PatientList patients = new PatientList();
    private static TestList tests = new TestList();

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Patients");
        System.out.println("2. Tests");
        System.out.println("3. Exit");
    }

    public void displayPatientMenu() {
        System.out.println("\nPatient Menu:");
        System.out.println("1. Add Patient");
        System.out.println("2. Edit Patient");
        System.out.println("3. Delete Patient");
        System.out.println("4. View Patients");
        System.out.println("5. Return to Main Menu");
    }

    public void displayTestMenu() {
        System.out.println("\nTest Menu:");
        System.out.println("1. Add Test");
        System.out.println("2. Edit Test");
        System.out.println("3. Delete Test");
        System.out.println("4. View Tests");
        System.out.println("5. Return to Main Menu");
    }

    public int selectOption() {
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        return choice;
    }

    // Main Menu Loop
    public void mainMenuLoop() {
        boolean mainMenuRunning = true;
        while (mainMenuRunning) {
            displayMainMenu();
            int mainChoice = selectOption();
            switch (mainChoice) {
                case 1:
                    patientMenuLoop();
                    break;
                case 2:
                    testMenuLoop();
                    break;
                case 3:
                    mainMenuRunning = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    // Patient Menu Loop
    public void patientMenuLoop() {
        boolean patientMenuRunning = true;
        while (patientMenuRunning) {
            displayPatientMenu();
            int patientChoice = selectOption();
            switch (patientChoice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    //editPatient();
                    break;
                case 3:
                    //deletePatient();
                    break;
                case 4:
                    listPatients();
                    break;
                case 5:
                    patientMenuRunning = false;
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Test Menu Loop
    public void testMenuLoop() {
        boolean testMenuRunning = true;
        while (testMenuRunning) {
            displayTestMenu();
            int testChoice = selectOption();
            switch (testChoice) {
                case 1:
                    addTest();
                    break;
                case 2:
                    //editTest();
                    break;
                case 3:
                    //deleteTest();
                    break;
                case 4:
                    listTests();
                    break;
                case 5:
                    testMenuRunning = false;
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    //Patient Menu option: Add Patient
    private static void addPatient() {
        System.out.println("Select input method: 1 for manual, 2 for file");
        int inputMethod = scanner.nextInt();
        if (inputMethod == 1) {
            // Manual entry
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
                scanner.nextLine();
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
            // File format: LastName, MiddleName, FirstName,MiddleName,DOB,Phone,Address
            if (parts.length != 6) {
                System.out.println("Skipping malformed line: " + line);
                continue;
            }
            String lastName = parts[0].trim();
            String firstName = parts[1].trim();
            String middleName = parts[2].trim().equals("none") ? null : parts[2].trim();
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
        // Clear scanner
        scanner.nextLine();

    }

    //Menu option 2: Add test
    private static void addTest() {
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
        parameters.put("testID", TestList.getNextTestId()); // TestID is generated
        collectTestDetails(scanner, parameters, selectedType);

        try {
            Test test = TestFactory.createTest(selectedType, parameters);
            tests.addTest(test);
            System.out.println("Test added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating test: " + e.getMessage());
        }
    }

    private static void collectTestDetails(Scanner scanner, Map<String, Object> parameters, TestType selectedType) {
        System.out.println("Enter Patient ID:");
        int patientID = Integer.parseInt(scanner.nextLine());
        // Verify patient exists
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setLenient(false);
        System.out.println("Enter Test Date and Time (format YYYY-MM-DD HH:MM):");
        try {
            Date testDateTime = sdf.parse(scanner.nextLine());
            validateTestDateTime(testDateTime);
            parameters.put("testDateTime", testDateTime);
        } catch (ParseException e) {
            System.out.println("Invalid date and time format. Please enter the date and time in the format YYYY-MM-DD HH:MM.");
            return;
        }

        if (selectedType == TestType.ELECTROLYTES) {
            System.out.println("Enter Sodium Result:");
            parameters.put("sodiumResult", Integer.parseInt(scanner.nextLine()));
            System.out.println("Enter Potassium Result:");
            parameters.put("potassiumResult", Float.parseFloat(scanner.nextLine()));
            System.out.println("Enter Calcium Result:");
            parameters.put("calciumResult", Float.parseFloat(scanner.nextLine()));
            System.out.println("Enter Chloride Result:");
            parameters.put("chlorideResult", Integer.parseInt(scanner.nextLine()));
        } else if(selectedType == TestType.HEMATOLOGY) {
            System.out.println("Enter Hematocrit Result (%):");
            parameters.put("hematocritResult", Integer.parseInt(scanner.nextLine()));

            System.out.println("Enter Hemoglobin Result (g/dL):");
            parameters.put("hemoglobinResult", Float.parseFloat(scanner.nextLine()));
        }
        else {
            System.out.println("Enter Test Result:");
            String result = scanner.nextLine();
            if (selectedType == TestType.GLUCOSE || selectedType == TestType.SODIUM || selectedType == TestType.CHLORIDE) {
                parameters.put(selectedType.name().toLowerCase() + "Result", Integer.parseInt(result));
            } else {
                parameters.put(selectedType.name().toLowerCase() + "Result", Float.parseFloat(result));
            }
        }
    }

    private static void validateTestDateTime(Date testDateTime) throws ParseException {
        Date currentDate = new Date();
        Date earliestDate = new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-01");
        if (testDateTime.after(currentDate)) {
            throw new ParseException("The date cannot be in the future. Please enter a correct date and time.", 0);
        }
        if (testDateTime.before(earliestDate)) {
            throw new ParseException("The date cannot be earlier than January 1, 2012. Please enter a correct date and time.", 0);
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

    public void closeScanner() {
        scanner.close();
    }
}