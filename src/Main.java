import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static PatientList patients = new PatientList();
    private static TestList tests = new TestList();
    private static FileManager fileManager = new FileManager();

    public static void main(String[] args) {
        Menu menu = new Menu();
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            menu.displayMenu();
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


    private static void addTest(Scanner scanner) {
        // Simulate adding a test
        System.out.println("Enter test details...");
        // Implementation similar to addPatient
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
