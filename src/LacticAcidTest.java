import java.util.Date;

public class LacticAcidTest extends Test {
    private float lacticAcidResult;

    public LacticAcidTest(int testID, int patientID, String testPanel, String deviceType, String deviceID, int operatorID, Date testDateTime, float lacticAcidResult) {
        super(testID, patientID, testPanel, deviceType, deviceID, operatorID, testDateTime);
        this.lacticAcidResult = lacticAcidResult;
    }

    public float getLacticAcidResult() { return lacticAcidResult; }
}
