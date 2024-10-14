/* DMS Project: Point of Care
 * October 13, 2024
 * CEN 3024C
 * Monica Berti
 * SodiumTest Class
 * This class creates a test panel for the sodium tests.
 */

import java.util.Date;

public class SodiumTest extends Test {
    private int sodiumResult;

    public SodiumTest(int testID, int patientID, String testPanel, String deviceType, String deviceID, int operatorID, Date testDateTime, int sodiumResult) {
        super(testID, patientID, testPanel, deviceType, deviceID, operatorID, testDateTime);
        this.sodiumResult = sodiumResult;
    }

    public int getSodiumResult() { return sodiumResult; }

    public void setSodiumResult(int sodiumResult) {
        this.sodiumResult = sodiumResult;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSodium Result: " + sodiumResult + " mmol/L";
    }
}
