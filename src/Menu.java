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

    public static void displayTestMenu() {
        System.out.println("\nTest Menu:");
        System.out.println("1. Add Test");
        System.out.println("2. Edit Test");
        System.out.println("3. Delete Test");
        System.out.println("4. View Tests");
        System.out.println("5. Return to Main Menu");
    }

    public static int selectOption() {
        while (true) {
            System.out.print("Select an option: ");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
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
                    editPatient(scanner);
                    break;
                case 3:
                    deletePatient(scanner);
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
    public static void testMenuLoop() {
        boolean testMenuRunning = true;
        while (testMenuRunning) {
            displayTestMenu();
            int testChoice = selectOption();
            switch (testChoice) {
                case 1:
                    addTest();
                    break;
                case 2:
                    TestList.editTest(scanner, tests);
                    break;
                case 3:
                    deleteTest();
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
        int inputMethod = 0;
        boolean validInput = false;

        //input validation
        while (!validInput) {
            System.out.println("Select input method: 1 for manual, 2 for file");
            if (scanner.hasNextInt()) {
                inputMethod = scanner.nextInt();
                if (inputMethod == 1 || inputMethod == 2) {
                    validInput = true;  // Exit the loop once a valid input is received
                } else {
                    System.out.println("Invalid option. Please enter 1 for manual or 2 for file.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();  // Consume the invalid input
            }
        }
        if (inputMethod == 1) {
            // Manual entry
            // Assuming DataValidation class is created with appropriate validation methods
            DataValidation validator = new DataValidation();

            try {
                // Enter first name
                String firstName = "";
                while (firstName.isEmpty() || !validator.validateName(firstName)) {
                    System.out.println("Enter first name:");
                    firstName = scanner.next();
                    if (!validator.validateName(firstName)) {
                        System.out.println("Invalid name. Please enter a valid first name.");
                    }
                }

                // Enter middle name
                System.out.println("Enter middle name (or type 'none' if no middle name):");
                String middleName = scanner.next();
                middleName = "none".equalsIgnoreCase(middleName) ? null : middleName;

                // Enter last name
                String lastName = "";
                while (lastName.isEmpty() || !validator.validateName(lastName)) {
                    System.out.println("Enter last name:");
                    lastName = scanner.next();
                    if (!validator.validateName(lastName)) {
                        System.out.println("Invalid name. Please enter a valid last name.");
                    }
                }

                // Enter date of birth
                Date dateOfBirth = null;
                while (dateOfBirth == null) {
                    System.out.println("Enter date of birth (format YYYY-MM-DD):");
                    String dateInput = scanner.next();
                    if (!validator.validateDate(dateInput)) {
                        System.out.println("Invalid date format. Please enter a valid date (YYYY-MM-DD).");
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        dateOfBirth = sdf.parse(dateInput);
                    }
                }

                // Enter phone number
                String phoneNumber = "";
                while (phoneNumber.isEmpty() || !validator.validatePhoneNumber(phoneNumber)) {
                    System.out.println("Enter phone number:");
                    phoneNumber = scanner.next();
                    if (!validator.validatePhoneNumber(phoneNumber)) {
                        System.out.println("Invalid phone number. Please enter a valid phone number.");
                    }
                }

                // Enter home address
                String homeAddress = "";
                while (homeAddress.isEmpty() || !validator.validateAddress(homeAddress)) {
                    System.out.println("Enter home address:");
                    scanner.nextLine();  // Consume the leftover newline
                    homeAddress = scanner.nextLine();
                    if (!validator.validateAddress(homeAddress)) {
                        System.out.println("Invalid address. Please enter a valid home address.");
                    }
                }

                // Create the patient object if all inputs are valid
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
        DataValidation validator = new DataValidation();  // Instantiate the DataValidation class
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);  // Strict date validation

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            // Check if the line has the correct number of fields
            if (parts.length != 6) {
                System.out.println("Skipping malformed line: " + line);
                continue;
            }

            String lastName = parts[0].trim();
            String firstName = parts[1].trim();
            String middleName = parts[2].trim().equals("none") ? null : parts[2].trim();
            String dateOfBirthStr = parts[3].trim();
            String phoneNumber = parts[4].trim();
            String homeAddress = parts[5].trim();

            // Validate each field
            if (!validator.validateName(firstName) || !validator.validateName(lastName)) {
                System.out.println("Invalid name. Skipping line: " + line);
                continue;
            }

            if (!validator.validatePhoneNumber(phoneNumber)) {
                System.out.println("Invalid phone number. Skipping line: " + line);
                continue;
            }

            if (!validator.validateAddress(homeAddress)) {
                System.out.println("Invalid address. Skipping line: " + line);
                continue;
            }

            // Validate date of birth
            Date dateOfBirth;
            if (!validator.validateDate(dateOfBirthStr)) {
                System.out.println("Invalid date of birth. Skipping line: " + line);
                continue;
            } else {
                dateOfBirth = sdf.parse(dateOfBirthStr);  // Parse valid date
            }

            // If all validations pass, create the Patient object
            Patient patient = new PatientBuilder(lastName, firstName)
                    .patientMiddleName(middleName)
                    .dateOfBirth(dateOfBirth)
                    .phoneNumber(phoneNumber)
                    .homeAddress(homeAddress)
                    .build();

            patients.addPatient(patient);  // Add the patient if valid
        }

        reader.close();
        System.out.println("Patients added successfully from file!");
        // Clear scanner
        scanner.nextLine();
    }

    //Patient Menu option: Edit patient
    private static void editPatient(Scanner scanner) {
        System.out.println("Enter the Patient ID to edit:");

        // Validate if the input is an integer
        String patientIDInput = scanner.nextLine();
        DataValidation validator = new DataValidation();

        while (!validator.isInteger(patientIDInput)) {
            System.out.println("Invalid ID format. Please enter a valid Patient ID:");
            patientIDInput = scanner.nextLine();
        }

        int patientID = Integer.parseInt(patientIDInput);

        // Check if patient exists
        Patient patient = patients.getPatientById(patientID);
        if (patient == null) {
            System.out.println("Patient not found with ID: " + patientID);
            return;
        }

        System.out.println("Editing Patient: " + patient);

        boolean editing = true;
        while (editing) {
            System.out.println("\nSelect the field to edit:");
            System.out.println("1. Last Name");
            System.out.println("2. First Name");
            System.out.println("3. Middle Name");
            System.out.println("4. Date of Birth");
            System.out.println("5. Phone Number");
            System.out.println("6. Home Address");
            System.out.println("7. Save & Exit");
            System.out.println("8. Cancel");

            String choiceInput = scanner.nextLine();
            while (!validator.isInteger(choiceInput)) {
                System.out.println("Invalid choice. Please enter a valid number:");
                choiceInput = scanner.nextLine();
            }
            int choice = Integer.parseInt(choiceInput);

            switch (choice) {
                case 1:
                    // Last Name
                    String lastName = "";
                    while (lastName.isEmpty() || !validator.validateName(lastName)) {
                        System.out.println("Enter new Last Name:");
                        lastName = scanner.nextLine();
                        if (!validator.validateName(lastName)) {
                            System.out.println("Invalid name. Please enter a valid last name.");
                        }
                    }
                    patient.setPatientLastName(lastName);
                    break;

                case 2:
                    // First Name
                    String firstName = "";
                    while (firstName.isEmpty() || !validator.validateName(firstName)) {
                        System.out.println("Enter new First Name:");
                        firstName = scanner.nextLine();
                        if (!validator.validateName(firstName)) {
                            System.out.println("Invalid name. Please enter a valid first name.");
                        }
                    }
                    patient.setPatientFirstName(firstName);
                    break;

                case 3:
                    // Middle Name
                    System.out.println("Enter new Middle Name (or type 'none' if no middle name):");
                    String middleName = scanner.nextLine();
                    patient.setPatientMiddleName("none".equalsIgnoreCase(middleName) ? null : middleName);
                    break;

                case 4:
                    // Date of Birth
                    Date dateOfBirth = null;
                    while (dateOfBirth == null) {
                        System.out.println("Enter new Date of Birth (format YYYY-MM-DD):");
                        String dateInput = scanner.nextLine();
                        if (!validator.validateDate(dateInput)) {
                            System.out.println("Invalid date format. Please enter a valid date (YYYY-MM-DD).");
                        } else {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                dateOfBirth = sdf.parse(dateInput);
                            } catch (ParseException e) {
                                System.out.println("Invalid date format.");
                            }
                        }
                    }
                    patient.setDateOfBirth(dateOfBirth);
                    break;

                case 5:
                    // Phone Number
                    String phoneNumber = "";
                    while (phoneNumber.isEmpty() || !validator.validatePhoneNumber(phoneNumber)) {
                        System.out.println("Enter new Phone Number:");
                        phoneNumber = scanner.nextLine();
                        if (!validator.validatePhoneNumber(phoneNumber)) {
                            System.out.println("Invalid phone number. Please enter a valid phone number.");
                        }
                    }
                    patient.setPhoneNumber(phoneNumber);
                    break;

                case 6:
                    // Home Address
                    String homeAddress = "";
                    while (homeAddress.isEmpty() || !validator.validateAddress(homeAddress)) {
                        System.out.println("Enter new Home Address:");
                        homeAddress = scanner.nextLine();
                        if (!validator.validateAddress(homeAddress)) {
                            System.out.println("Invalid address. Please enter a valid home address.");
                        }
                    }
                    patient.setHomeAddress(homeAddress);
                    break;

                case 7:
                    // Save & Exit
                    patients.updatePatient(patientID, patient);
                    System.out.println("Patient updated successfully!");
                    editing = false;
                    break;

                case 8:
                    // Cancel
                    System.out.println("Edit cancelled. Returning to the previous menu.");
                    editing = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    //Patient Menu option: delete patient
    private static void deletePatient(Scanner scanner) {
        DataValidation validator = new DataValidation();
        boolean validInput = false;
        int patientID = 0;

        // Input validation loop for patient ID
        while (!validInput) {
            System.out.println("Enter Patient ID to delete:");
            String input = scanner.nextLine();

            if (validator.isInteger(input)) {
                patientID = Integer.parseInt(input);
                validInput = true; // If valid integer, exit loop
            } else {
                System.out.println("Invalid ID format. Please enter a valid integer Patient ID.");
            }
        }

        // Check if the patient exists before attempting to delete
        if (patients.getPatientById(patientID) == null) {
            System.out.println("Patient not found with ID: " + patientID);
        } else {
            boolean isDeleted = patients.deletePatient(patientID);
            if (isDeleted) {
                System.out.println("Patient deleted successfully.");
            } else {
                System.out.println("Failed to delete patient.");
            }
        }
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

        if (parameters.isEmpty()) {
            return;  // Exit if no valid parameters were provided
        }

        try {
            Test test = TestFactory.createTest(selectedType, parameters);
            tests.addTest(test);
            System.out.println("Test added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating test: " + e.getMessage());
        }
    }

    private static void collectTestDetails(Scanner scanner, Map<String, Object> parameters, TestType selectedType) {
        DataValidation validator = new DataValidation();

        int patientID = -1;
        boolean validPatientID = false;
        while (!validPatientID) {
            System.out.println("Enter Patient ID:");
            String input = scanner.nextLine();
            try {
                patientID = Integer.parseInt(input);
                if (!patients.exists(patientID)) {
                    System.out.println("No patient found with ID: " + patientID + ". Please add the patient first.");
                    testMenuLoop(); // Call test menu loop again
                    return;
                }
                validPatientID = true; // Valid integer input
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for Patient ID.");
            }
        }
        parameters.put("patientID", patientID);

        System.out.println("Enter Device Type:");
        parameters.put("deviceType", scanner.nextLine());

        System.out.println("Enter Device Serial Number:");
        parameters.put("deviceID", scanner.nextLine());

        // Error checking for Operator ID
        int operatorID = -1;
        boolean validOperatorID = false;
        while (!validOperatorID) {
            System.out.println("Enter Operator ID:");
            String input = scanner.nextLine();
            try {
                operatorID = Integer.parseInt(input);
                validOperatorID = true; // If no exception, the input is valid
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for Operator ID.");
            }
        }
        parameters.put("operatorID", operatorID);

        // Validate test date and time
        System.out.println("Enter Test Date and Time (format YYYY-MM-DD HH:MM):");
        String dateTimeInput = scanner.nextLine();
        while (!validator.validateTestDateTime(dateTimeInput)) {
            System.out.println("Invalid date and time format. Please enter the date and time in the format YYYY-MM-DD HH:MM:");
            dateTimeInput = scanner.nextLine();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date testDateTime;
        try {
            testDateTime = sdf.parse(dateTimeInput);
            parameters.put("testDateTime", testDateTime);
        } catch (ParseException e) {
            return; // Error already handled in validateTestDateTime
        }

        // Check if the test is multi-component (e.g., Electrolytes or Hematology)
        if (selectedType == TestType.ELECTROLYTES) {
            System.out.println("Enter Sodium Result:");
            String sodiumResult = scanner.nextLine();
            if (!validator.validateAndConfirmResult("Sodium", sodiumResult, 135, 145, true)) {
                System.out.println("Test not added.");
                parameters.clear();
                return;
            }
            parameters.put("sodiumResult", Integer.parseInt(sodiumResult));

            System.out.println("Enter Potassium Result:");
            String potassiumResult = scanner.nextLine();
            if (!validator.validateAndConfirmResult("Potassium", potassiumResult, 3.5, 5.1, false)) {
                System.out.println("Test not added.");
                return;
            }
            parameters.put("potassiumResult", Float.parseFloat(potassiumResult));

            // Similarly, add validation for Calcium and Chloride
            System.out.println("Enter Calcium Result:");
            String calciumResult = scanner.nextLine();
            if (!validator.validateAndConfirmResult("Calcium", calciumResult, 8.5, 10.2, false)) {
                System.out.println("Test not added.");
                parameters.clear();
                return;
            }
            parameters.put("calciumResult", Float.parseFloat(calciumResult));

            System.out.println("Enter Chloride Result:");
            String chlorideResult = scanner.nextLine();
            if (!validator.validateAndConfirmResult("Chloride", chlorideResult, 98, 106, true)) {
                System.out.println("Test not added.");
                parameters.clear();
                return;
            }
            parameters.put("chlorideResult", Integer.parseInt(chlorideResult));

        } else if (selectedType == TestType.HEMATOLOGY) {
            System.out.println("Enter Hematocrit Result (%):");
            String hematocritResult = scanner.nextLine();
            if (!validator.validateAndConfirmResult("Hematocrit", hematocritResult, 38.3, 48.6, true)) {
                System.out.println("Test not added.");
                parameters.clear();
                return;
            }
            parameters.put("hematocritResult", Integer.parseInt(hematocritResult));

            System.out.println("Enter Hemoglobin Result (g/dL):");
            String hemoglobinResult = scanner.nextLine();
            if (!validator.validateAndConfirmResult("Hemoglobin", hemoglobinResult, 13.2, 17.1, false)) {
                System.out.println("Test not added.");
                parameters.clear();
                return;
            }
            parameters.put("hemoglobinResult", Float.parseFloat(hemoglobinResult));

        } else {
            // For individual test panels, only prompt for a single result
            System.out.println("Enter Test Result:");
            String result = scanner.nextLine();
            switch (selectedType) {
                case GLUCOSE:
                    if (!validator.validateAndConfirmResult("Glucose", result, 70, 200, true)) {
                        System.out.println("Test not added.");
                        parameters.clear();
                        return;
                    }
                    parameters.put("glucoseResult", Integer.parseInt(result));
                    break;

                case SODIUM:
                    if (!validator.validateAndConfirmResult("Sodium", result, 135, 145, true)) {
                        System.out.println("Test not added.");
                        parameters.clear();
                        return;
                    }
                    parameters.put("sodiumResult", Integer.parseInt(result));
                    break;

                case POTASSIUM:
                    if (!validator.validateAndConfirmResult("Potassium", result, 3.5, 5.1, false)) {
                        System.out.println("Test not added.");
                        parameters.clear();
                        return;
                    }
                    parameters.put("potassiumResult", Float.parseFloat(result));
                    break;

                case CALCIUM:
                    if (!validator.validateAndConfirmResult("Calcium", result, 8.5, 10.2, false)) {
                        System.out.println("Test not added.");
                        parameters.clear();
                        return;
                    }
                    parameters.put("calciumResult", Float.parseFloat(result));
                    break;

                case CHLORIDE:
                    if (!validator.validateAndConfirmResult("Chloride", result, 98, 106, true)) {
                        System.out.println("Test not added.");
                        parameters.clear();
                        return;
                    }
                    parameters.put("chlorideResult", Integer.parseInt(result));
                    break;

                case LACTICACID:
                    if (!validator.validateAndConfirmResult("Lactic Acid", result, 0.5, 2.2, false)) {
                        System.out.println("Test not added.");
                        parameters.clear();
                        return;
                    }
                    parameters.put("lacticAcidResult", Float.parseFloat(result));
                    break;

                default:
                    System.out.println("Unknown test type.");
                    return;
            }
        }
    }

    //Test Menu Option: Delete test
    public static void deleteTest() {
        int testID = -1;
        boolean validTestID = false;

        // Loop until a valid integer is entered for Test ID
        while (!validTestID) {
            System.out.println("Enter Test ID to delete:");
            String input = scanner.nextLine();

            try {
                testID = Integer.parseInt(input);
                validTestID = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for Test ID.");
            }
        }

        // Check if the test exists
        if (tests.getTest(testID) != null) {
            tests.removeTest(testID);
            System.out.println("Test with ID " + testID + " has been successfully deleted.");
        } else {
            System.out.println("No test found with ID: " + testID + ". Please try again.");
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