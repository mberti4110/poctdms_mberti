import java.util.Date;

public class DataValidation {

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