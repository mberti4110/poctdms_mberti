import java.util.Date;

public class SodiumTest extends Test {
    private int sodiumResult;

    public SodiumTest(int testID, int patientID, String testPanel, String deviceType, String deviceID, int operatorID, Date testDateTime, int sodiumResult) {
        super(testID, patientID, testPanel, deviceType, deviceID, operatorID, testDateTime);
        this.sodiumResult = sodiumResult;
    }

    public int getSodiumResult() { return sodiumResult; }
}
