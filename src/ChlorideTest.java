/* DMS Project: Point of Care
 * October 13, 2024
 * CEN 3024C
 * Monica Berti
 * ChlorideTest Class
 * This class creates a test panel for the chloride tests.
 */
import java.util.Date;

public class ChlorideTest extends Test {
    private int chlorideResult;

    public ChlorideTest(int testID, int patientID, String testPanel, String deviceType, String deviceID, int operatorID, Date testDateTime, int chlorideResult) {
        super(testID, patientID, testPanel, deviceType, deviceID, operatorID, testDateTime);
        this.chlorideResult = chlorideResult;
    }

    public int getChlorideResult() { return chlorideResult; }

    public void setChlorideResult(int chlorideResult) {
        this.chlorideResult = chlorideResult;
    }

    @Override
    public String toString() {
        return super.toString() + "\nChloride Result: " + chlorideResult + " mEq/L";
    }
}
