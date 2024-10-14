/* DMS Project: Point of Care
 * October 13, 2024
 * CEN 3024C
 * Monica Berti
 * PotassiumTest Class
 * This class creates a test panel for the potassium tests.
 */

import java.util.Date;

public class PotassiumTest extends Test {
    private float potassiumResult;

    public PotassiumTest(int testID, int patientID, String testPanel, String deviceType, String deviceID, int operatorID, Date testDateTime, float potassiumResult) {
        super(testID, patientID, testPanel, deviceType, deviceID, operatorID, testDateTime);
        this.potassiumResult = potassiumResult;
    }

    public float getPotassiumResult() { return potassiumResult; }

    public void setPotassiumResult(float potassiumResult) {
        this.potassiumResult = potassiumResult;
    }

    @Override
    public String toString() {
        return super.toString() + "\nPotassium Result: " + potassiumResult + " mmol/L";
    }
}
