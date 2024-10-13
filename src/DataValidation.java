import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataValidation {

    // Method to validate if a string can be parsed into an integer
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate if the input is a float
    public boolean isFloat(String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validateName(String name) {
        return name != null && name.matches("[a-zA-Z]+");
    }

    //DOB Validation
    public static boolean validateDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);  // Strict date checking
            Date parsedDate = sdf.parse(date);

            // Check if the date is in the future
            if (parsedDate.after(new Date())) {
                System.out.println("The date cannot be in the future.");
                return false;
            }
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Test Time Validation
    public boolean validateTestDateTime(String input) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setLenient(false);
        try {
            Date testDateTime = sdf.parse(input);
            return validateTestDateTimeRange(testDateTime);  // Validate test date range
        } catch (ParseException e) {
            return false;
        }
    }
    // Check if the test date/time is within the valid range (not in the future or before 2012)
    private boolean validateTestDateTimeRange(Date testDateTime) throws ParseException {
        Date currentDate = new Date();
        Date earliestDate = new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-01");
        if (testDateTime.after(currentDate)) {
            System.out.println("The date cannot be in the future. Please enter a correct date.");
            return false;
        }
        if (testDateTime.before(earliestDate)) {
            System.out.println("The date cannot be earlier than January 1, 2012. Please enter a correct date.");
            return false;
        }
        return true;
    }

    // Validate result within a range for float and integer, and confirm if the user wants to proceed with an out-of-range value
    public boolean validateAndConfirmResult(String label, String resultInput, double min, double max, boolean isInteger) {
        try {
            double result;
            if (isInteger) {
                result = Integer.parseInt(resultInput);
            } else {
                result = Float.parseFloat(resultInput);
            }

            if (result < min || result > max) {
                System.out.println(label + " is out of the normal range (" + min + " - " + max + "). Would you like to proceed? (y/n)");

                String confirm;
                boolean validResponse = false;

                // Loop until valid input is received
                while (!validResponse) {
                    confirm = Main.scanner.nextLine().trim().toLowerCase();

                    if (confirm.equals("y")) {
                        return true;  // User confirms to proceed
                    } else if (confirm.equals("n")) {
                        return false; // User does not want to proceed
                    } else {
                        System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
                    }
                }
            }

            return true; // Result is within range
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for " + label + ".");
            return false;
        }
    }
    public boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }

    public boolean validateAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }
    // Validate if a string is not null and not empty
    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Validate if a date is within a specified range
    public static boolean isValidDate(Date input, Date min, Date max) {
        if (input == null) return false;
        return !input.before(min) && !input.after(max);
    }

    // Validate if an integer is within a specified range
    public static boolean isWithinRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    // Validate if a float is within a specified range
    public static boolean isWithinRange(float value, float min, float max) {
        return value >= min && value <= max;
    }

    // Method to validate medical test results with thresholds
    public static String validateTestResult(String testType, float result) {
        switch (testType.toLowerCase()) {
            case "glucose":
                if (!isWithinRange(result, 70, 140)) {
                    return "Glucose levels are abnormal.";
                }
                break;
            case "sodium":
                if (!isWithinRange(result, 135, 145)) {
                    return "Sodium levels are outside normal range.";
                }
                break;
            case "potassium":
                if (!isWithinRange(result, 3.5f, 5.1f)) {
                    return "Potassium levels are abnormal.";
                }
                break;
            case "calcium":
                if (!isWithinRange(result, 8.6f, 10.2f)) {
                    return "Calcium levels are outside normal range.";
                }
                break;
            case "chloride":
                if (!isWithinRange(result, 98, 106)) {
                    return "Chloride levels are abnormal.";
                }
                break;
            case "lacticacid":
                if (!isWithinRange(result, 0.5f, 2.2f)) {
                    return "Lactic acid levels are too high.";
                }
                break;
            case "hematocrit":
                if (!isWithinRange(result, 38.8f, 50.0f)) {
                    return "Hematocrit percentage is out of normal range.";
                }
                break;
            case "hemoglobin":
                if (!isWithinRange(result, 13.8f, 17.2f)) {
                    return "Hemoglobin levels are not within the safe range.";
                }
                break;
            default:
                return "Test type unknown or not supported.";
        }
        return "Test result is within the normal range.";
    }
}