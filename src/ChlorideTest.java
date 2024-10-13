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
