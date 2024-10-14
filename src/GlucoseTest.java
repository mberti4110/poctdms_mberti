/* DMS Project: Point of Care
 * October 13, 2024
 * CEN 3024C
 * Monica Berti
 * Glucose Class
 * This class creates a test panel for the glucose tests.
 */

import java.util.Date;

public class GlucoseTest extends Test {
    private int glucoseResult;

    public GlucoseTest(int testID, int patientID, String testPanel, String deviceType, String deviceID, int operatorID, Date testDateTime, int glucoseResult) {
        super(testID, patientID, testPanel, deviceType, deviceID, operatorID, testDateTime);
        this.glucoseResult = glucoseResult;
    }

    public int getGlucoseResult() { return glucoseResult; }

    public void setGlucoseResult(int glucoseResult) {
        this.glucoseResult = glucoseResult;
    }

    @Override
    public String toString() {
        return super.toString() + "\nGlucose Result: " + glucoseResult + " mg/dL";
    }
}