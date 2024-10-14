/* DMS Project: Point of Care
 * October 13, 2024
 * CEN 3024C
 * Monica Berti
 * LacticAcidTest Class
 * This class creates a test panel for the lactic acid tests.
 */

import java.util.Date;

public class LacticAcidTest extends Test {
    private float lacticAcidResult;

    public LacticAcidTest(int testID, int patientID, String testPanel, String deviceType, String deviceID, int operatorID, Date testDateTime, float lacticAcidResult) {
        super(testID, patientID, testPanel, deviceType, deviceID, operatorID, testDateTime);
        this.lacticAcidResult = lacticAcidResult;
    }

    public float getLacticAcidResult() { return lacticAcidResult; }

    public void setLacticAcidResult(float lacticAcidResult) {
        this.lacticAcidResult = lacticAcidResult;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLactic Acid Result: " + lacticAcidResult + " mmol/L";
    }
}
