import java.util.Date;

public class HematologyTest extends Test {
    private int hematocritResult;
    private float hemoglobinResult;

    public HematologyTest(int testID, int patientID, String testPanel, String deviceType, String deviceID, int operatorID, Date testDateTime, int hematocritResult, float hemoglobinResult) {
        super(testID, patientID, testPanel, deviceType, deviceID, operatorID, testDateTime);
        this.hematocritResult = hematocritResult;
        this.hemoglobinResult = hemoglobinResult;
    }

    public int getHematocritResult() {
        return hematocritResult;
    }

    public float getHemoglobinResult() {
        return hemoglobinResult;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nHematocrit Result: " + hematocritResult +
                "\nHemoglobin Result: " + hemoglobinResult;
    }
}
