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

    public static void editTest(Scanner scanner, TestList tests) {
        System.out.println("Enter the Test ID to edit:");
        int testID = Integer.parseInt(scanner.nextLine()); // Getting test ID from user

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

            int choice = Integer.parseInt(scanner.nextLine());
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
                    test.setOperatorID(Integer.parseInt(scanner.nextLine()));
                    break;
                case 4:
                    System.out.println("Enter new Test Date and Time (format YYYY-MM-DD HH:MM):");
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date testDateTime = sdf.parse(scanner.nextLine());
                        test.setTestDateTime(testDateTime);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please try again.");
                    }
                    break;
                case 5:
                    System.out.println("Enter new Test Result:");
                    updateTestResult(scanner, test);
                    break;
                case 6:
                    // Save the changes and exit
                    tests.updateTest(testID, test); // Assuming updateTest() exists in TestList
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
        if (test instanceof GlucoseTest) {
            System.out.println("Enter new Glucose Result:");
            int glucoseResult = Integer.parseInt(scanner.nextLine());
            ((GlucoseTest) test).setGlucoseResult(glucoseResult);
        } else if (test instanceof SodiumTest) {
            System.out.println("Enter new Sodium Result:");
            int sodiumResult = Integer.parseInt(scanner.nextLine());
            ((SodiumTest) test).setSodiumResult(sodiumResult);
        } else if (test instanceof PotassiumTest) {
            System.out.println("Enter new Potassium Result:");
            float potassiumResult = Float.parseFloat(scanner.nextLine());
            ((PotassiumTest) test).setPotassiumResult(potassiumResult);
        } else if (test instanceof CalciumTest) {
            System.out.println("Enter new Calcium Result:");
            float calciumResult = Float.parseFloat(scanner.nextLine());
            ((CalciumTest) test).setCalciumResult(calciumResult);
        } else if (test instanceof ChlorideTest) {
            System.out.println("Enter new Chloride Result:");
            int chlorideResult = Integer.parseInt(scanner.nextLine());
            ((ChlorideTest) test).setChlorideResult(chlorideResult);
        } else if (test instanceof LacticAcidTest) {
            System.out.println("Enter new Lactic Acid Result:");
            float lacticAcidResult = Float.parseFloat(scanner.nextLine());
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
                    ((ElectrolyteTest) test).setSodiumResult(Integer.parseInt(scanner.nextLine()));
                    break;
                case 2:
                    System.out.println("Enter new Potassium Result:");
                    ((ElectrolyteTest) test).setPotassiumResult(Float.parseFloat(scanner.nextLine()));
                    break;
                case 3:
                    System.out.println("Enter new Calcium Result:");
                    ((ElectrolyteTest) test).setCalciumResult(Float.parseFloat(scanner.nextLine()));
                    break;
                case 4:
                    System.out.println("Enter new Chloride Result:");
                    ((ElectrolyteTest) test).setChlorideResult(Integer.parseInt(scanner.nextLine()));
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
                    ((HematologyTest) test).setHematocritResult(Integer.parseInt(scanner.nextLine()));
                    break;
                case 2:
                    System.out.println("Enter new Hemoglobin Result (g/dL):");
                    ((HematologyTest) test).setHemoglobinResult(Float.parseFloat(scanner.nextLine()));
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
