import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class TestList {

    private Map<Integer, Test> testList = new HashMap<>();
    private static AtomicInteger nextTestId = new AtomicInteger(1);

    public static int getNextTestId() {
        return nextTestId.getAndIncrement();
    }

    public void addTest(Test test) {
        testList.put(test.getTestID(), test);
    }

    public void removeTest(int testID) {
        testList.remove(testID);
    }

    //Test Menu Option: Edit TEst
    public static void editTest(Scanner scanner, TestList tests) {
        DataValidation validator = new DataValidation(); // Initialize your validation class

        System.out.println("Enter the Test ID to edit:");
        String inputTestID = scanner.nextLine();
        int testID = -1;

        // Error checking for Test ID
        while (testID == -1) {
            if (validator.isInteger(inputTestID)) {
                testID = Integer.parseInt(inputTestID);
            } else {
                System.out.println("Invalid Test ID. Please enter a valid integer:");
                inputTestID = scanner.nextLine();
            }
        }

        // Check if test exists
        Test test = tests.getTest(testID);
        if (test == null) {
            System.out.println("Test not found with ID: " + testID);
            return;
        }

        System.out.println("Editing Test: " + test);

        boolean editing = true;
        while (editing) {
            System.out.println("\nSelect the field to edit:");
            System.out.println("1. Device Type");
            System.out.println("2. Device Serial Number");
            System.out.println("3. Operator ID");
            System.out.println("4. Test Date and Time");
            System.out.println("5. Test Result");
            System.out.println("6. Save & Exit");
            System.out.println("7. Cancel");

            String inputChoice = scanner.nextLine();
            int choice = -1;

            // Validate menu option
            while (choice == -1) {
                if (validator.isInteger(inputChoice)) {
                    choice = Integer.parseInt(inputChoice);
                } else {
                    System.out.println("Invalid option. Please enter a valid number:");
                    inputChoice = scanner.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter new Device Type:");
                    test.setDeviceType(scanner.nextLine());
                    break;

                case 2:
                    System.out.println("Enter new Device Serial Number:");
                    test.setDeviceID(scanner.nextLine());
                    break;

                case 3:
                    System.out.println("Enter new Operator ID:");
                    String operatorIDInput = scanner.nextLine();
                    int operatorID = -1;

                    // Error checking for Operator ID
                    while (operatorID == -1) {
                        if (validator.isInteger(operatorIDInput)) {
                            operatorID = Integer.parseInt(operatorIDInput);
                        } else {
                            System.out.println("Invalid Operator ID. Please enter a valid integer:");
                            operatorIDInput = scanner.nextLine();
                        }
                    }
                    test.setOperatorID(operatorID);
                    break;

                case 4:
                    System.out.println("Enter new Test Date and Time (format YYYY-MM-DD HH:MM):");
                    String dateTimeInput = scanner.nextLine();
                    while (!validator.validateTestDateTime(dateTimeInput)) {
                        System.out.println("Invalid date and time format. Please enter the date and time in the format YYYY-MM-DD HH:MM:");
                        dateTimeInput = scanner.nextLine();
                    }

                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date testDateTime = sdf.parse(dateTimeInput);
                        test.setTestDateTime(testDateTime);
                    } catch (ParseException e) {
                        System.out.println("Error parsing date. Please try again.");
                    }
                    break;

                case 5:
                    System.out.println("Enter new Test Result:");
                    updateTestResult(scanner, test);
                    break;

                case 6:
                    // Save the changes and exit
                    tests.updateTest(testID, test);
                    System.out.println("Test updated successfully!");
                    editing = false;
                    break;

                case 7:
                    // Cancel the editing process
                    System.out.println("Edit cancelled. Returning to the previous menu.");
                    editing = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    private static void updateTestResult(Scanner scanner, Test test) {
        DataValidation validator = new DataValidation();

        if (test instanceof GlucoseTest) {
            System.out.println("Enter new Glucose Result:");
            String glucoseInput = scanner.nextLine();
            while (!validator.validateAndConfirmResult("Glucose", glucoseInput, 70, 200, true)) {
                System.out.println("Invalid input or out of range. Enter new Glucose Result:");
                glucoseInput = scanner.nextLine();
            }
            int glucoseResult = Integer.parseInt(glucoseInput);
            ((GlucoseTest) test).setGlucoseResult(glucoseResult);

        } else if (test instanceof SodiumTest) {
            System.out.println("Enter new Sodium Result:");
            String sodiumInput = scanner.nextLine();
            while (!validator.validateAndConfirmResult("Sodium", sodiumInput, 135, 145, true)) {
                System.out.println("Invalid input or out of range. Enter new Sodium Result:");
                sodiumInput = scanner.nextLine();
            }
            int sodiumResult = Integer.parseInt(sodiumInput);
            ((SodiumTest) test).setSodiumResult(sodiumResult);

        } else if (test instanceof PotassiumTest) {
            System.out.println("Enter new Potassium Result:");
            String potassiumInput = scanner.nextLine();
            while (!validator.validateAndConfirmResult("Potassium", potassiumInput, 3.5, 5.1, false)) {
                System.out.println("Invalid input or out of range. Enter new Potassium Result:");
                potassiumInput = scanner.nextLine();
            }
            float potassiumResult = Float.parseFloat(potassiumInput);
            ((PotassiumTest) test).setPotassiumResult(potassiumResult);

        } else if (test instanceof CalciumTest) {
            System.out.println("Enter new Calcium Result:");
            String calciumInput = scanner.nextLine();
            while (!validator.validateAndConfirmResult("Calcium", calciumInput, 8.5, 10.2, false)) {
                System.out.println("Invalid input or out of range. Enter new Calcium Result:");
                calciumInput = scanner.nextLine();
            }
            float calciumResult = Float.parseFloat(calciumInput);
            ((CalciumTest) test).setCalciumResult(calciumResult);

        } else if (test instanceof ChlorideTest) {
            System.out.println("Enter new Chloride Result:");
            String chlorideInput = scanner.nextLine();
            while (!validator.validateAndConfirmResult("Chloride", chlorideInput, 98, 106, true)) {
                System.out.println("Invalid input or out of range. Enter new Chloride Result:");
                chlorideInput = scanner.nextLine();
            }
            int chlorideResult = Integer.parseInt(chlorideInput);
            ((ChlorideTest) test).setChlorideResult(chlorideResult);

        } else if (test instanceof LacticAcidTest) {
            System.out.println("Enter new Lactic Acid Result:");
            String lacticAcidInput = scanner.nextLine();
            while (!validator.validateAndConfirmResult("Lactic Acid", lacticAcidInput, 0.5, 2.2, false)) {
                System.out.println("Invalid input or out of range. Enter new Lactic Acid Result:");
                lacticAcidInput = scanner.nextLine();
            }
            float lacticAcidResult = Float.parseFloat(lacticAcidInput);
            ((LacticAcidTest) test).setLacticAcidResult(lacticAcidResult);

        } else if (test instanceof ElectrolyteTest) {
            System.out.println("Select the Electrolyte component to edit:");
            System.out.println("1. Sodium");
            System.out.println("2. Potassium");
            System.out.println("3. Calcium");
            System.out.println("4. Chloride");
            int electrolyteChoice = Integer.parseInt(scanner.nextLine());
            switch (electrolyteChoice) {
                case 1:
                    System.out.println("Enter new Sodium Result:");
                    String sodiumInput = scanner.nextLine();
                    while (!validator.validateAndConfirmResult("Sodium", sodiumInput, 135, 145, true)) {
                        System.out.println("Invalid input or out of range. Enter new Sodium Result:");
                        sodiumInput = scanner.nextLine();
                    }
                    ((ElectrolyteTest) test).setSodiumResult(Integer.parseInt(sodiumInput));
                    break;
                case 2:
                    System.out.println("Enter new Potassium Result:");
                    String potassiumInput = scanner.nextLine();
                    while (!validator.validateAndConfirmResult("Potassium", potassiumInput, 3.5, 5.1, false)) {
                        System.out.println("Invalid input or out of range. Enter new Potassium Result:");
                        potassiumInput = scanner.nextLine();
                    }
                    ((ElectrolyteTest) test).setPotassiumResult(Float.parseFloat(potassiumInput));
                    break;
                case 3:
                    System.out.println("Enter new Calcium Result:");
                    String calciumInput = scanner.nextLine();
                    while (!validator.validateAndConfirmResult("Calcium", calciumInput, 8.5, 10.2, false)) {
                        System.out.println("Invalid input or out of range. Enter new Calcium Result:");
                        calciumInput = scanner.nextLine();
                    }
                    ((ElectrolyteTest) test).setCalciumResult(Float.parseFloat(calciumInput));
                    break;
                case 4:
                    System.out.println("Enter new Chloride Result:");
                    String chlorideInput = scanner.nextLine();
                    while (!validator.validateAndConfirmResult("Chloride", chlorideInput, 98, 106, true)) {
                        System.out.println("Invalid input or out of range. Enter new Chloride Result:");
                        chlorideInput = scanner.nextLine();
                    }
                    ((ElectrolyteTest) test).setChlorideResult(Integer.parseInt(chlorideInput));
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } else if (test instanceof HematologyTest) {
            System.out.println("Select the Hematology component to edit:");
            System.out.println("1. Hematocrit");
            System.out.println("2. Hemoglobin");
            int hematologyChoice = Integer.parseInt(scanner.nextLine());
            switch (hematologyChoice) {
                case 1:
                    System.out.println("Enter new Hematocrit Result (%):");
                    String hematocritInput = scanner.nextLine();
                    while (!validator.validateAndConfirmResult("Hematocrit", hematocritInput, 38.3, 48.6, true)) {
                        System.out.println("Invalid input or out of range. Enter new Hematocrit Result (%):");
                        hematocritInput = scanner.nextLine();
                    }
                    ((HematologyTest) test).setHematocritResult(Integer.parseInt(hematocritInput));
                    break;
                case 2:
                    System.out.println("Enter new Hemoglobin Result (g/dL):");
                    String hemoglobinInput = scanner.nextLine();
                    while (!validator.validateAndConfirmResult("Hemoglobin", hemoglobinInput, 13.2, 17.1, false)) {
                        System.out.println("Invalid input or out of range. Enter new Hemoglobin Result (g/dL):");
                        hemoglobinInput = scanner.nextLine();
                    }
                    ((HematologyTest) test).setHemoglobinResult(Float.parseFloat(hemoglobinInput));
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void updateTest(int testID, Test test) {
        testList.put(testID, test);
    }

    public Test getTest(int testID) {
        return testList.get(testID);
    }

    public Map<Integer, Test> listTests() {
        return testList;
    }

    public boolean hasDuplicateID(int testID) {
        return testList.containsKey(testID);
    }
}
