import java.util.Date;

public class CalciumTest extends Test {
    private float calciumResult;

    public CalciumTest(int testID, int patientID, String testPanel, String deviceType, String deviceID, int operatorID, Date testDateTime, float calciumResult) {
        super(testID, patientID, testPanel, deviceType, deviceID, operatorID, testDateTime);
        this.calciumResult = calciumResult;
    }

    public float getCalciumResult() { return calciumResult; }

    public void setCalciumResult(float calciumResult) {
        this.calciumResult = calciumResult;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCalcium Result: " + calciumResult + " mg/dL";
    }
}
