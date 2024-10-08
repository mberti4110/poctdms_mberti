import java.util.Date;

public class GlucoseTest extends Test {
    private int glucoseResult;

    public GlucoseTest(int testID, int patientID, String testPanel, String deviceType, String deviceID, int operatorID, Date testDateTime, int glucoseResult) {
        super(testID, patientID, testPanel, deviceType, deviceID, operatorID, testDateTime);
        this.glucoseResult = glucoseResult;
    }

    public int getGlucoseResult() { return glucoseResult; }

    @Override
    public String toString() {
        return super.toString() + "\nGlucose Result: " + glucoseResult + " mg/dL";
    }
}