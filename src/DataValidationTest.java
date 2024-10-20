import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataValidationTest {

    private DataValidation validator;

    @BeforeEach
    void setUp() {
        validator = new DataValidation();  // Initialize the validation object before each test
    }

    // Test case for name validation
    @Test
    void validateName() {
        assertTrue(validator.validateName("John"), "Valid name should pass.");
        assertFalse(validator.validateName("John123"), "Name with numbers should fail.");
        assertFalse(validator.validateName(""), "Empty name should fail.");
        assertFalse(validator.validateName("@John"), "Name with special characters should fail.");
    }

    // Test case for date of birth validation (format YYYY-MM-DD)
    @Test
    void validateDate() {
        assertTrue(validator.validateDate("1990-05-10"), "Valid date should pass.");
        assertFalse(validator.validateDate("2025-01-01"), "Future date should fail.");
        assertFalse(validator.validateDate("10-05-1990"), "Incorrect date format should fail.");
        assertFalse(validator.validateDate("invalid-date"), "Invalid string should fail.");
    }

    // Test case for test date and time validation (format YYYY-MM-DD HH:MM)
    @Test
    void validateTestDateTime() {
        assertTrue(validator.validateTestDateTime("2023-10-01 14:30"), "Valid test date and time should pass.");
        assertFalse(validator.validateTestDateTime("2023-13-01 14:30"), "Invalid month should fail.");
        assertFalse(validator.validateTestDateTime("2023-10-01"), "Missing time should fail.");
        assertFalse(validator.validateTestDateTime("2023/10/01 14:30"), "Incorrect date format should fail.");
    }


    // Test case for phone number validation
    @Test
    void validatePhoneNumber() {
        // Valid phone number (10 digits, no formatting)
        assertTrue(validator.validatePhoneNumber("1234567890"), "Valid phone number should pass.");

        // Invalid phone numbers
        assertFalse(validator.validatePhoneNumber("12345"), "Too short phone number should fail.");
        assertFalse(validator.validatePhoneNumber("12345678901"), "Phone number with more than 10 digits should fail.");
        assertFalse(validator.validatePhoneNumber("123456789a"), "Phone number with non-digit characters should fail.");
        assertFalse(validator.validatePhoneNumber(""), "Empty phone number should fail.");
    }


    // Test case for address validation
    @Test
    void validateAddress() {
        assertTrue(validator.validateAddress("123 Main St"), "Valid address should pass.");
        assertFalse(validator.validateAddress(""), "Empty address should fail.");
        assertFalse(validator.validateAddress("!!! Main St"), "Address with invalid characters should fail.");
        assertTrue(validator.validateAddress("456 Oak St, Apt 2B"), "Valid complex address should pass.");
    }
}
