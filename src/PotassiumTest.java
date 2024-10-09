import java.util.Date;

public class PotassiumTest extends Test {
    private float potassiumResult;

    public PotassiumTest(int testID, int patientID, String testPanel, String deviceType, String deviceID, int operatorID, Date testDateTime, float potassiumResult) {
        super(testID, patientID, testPanel, deviceType, deviceID, operatorID, testDateTime);
        this.potassiumResult = potassiumResult;
    }

    public float getPotassiumResult() { return potassiumResult; }

    @Override
    public String toString() {
        return super.toString() + "\nPotassium Result: " + potassiumResult + " mmol/L";
    }
}
