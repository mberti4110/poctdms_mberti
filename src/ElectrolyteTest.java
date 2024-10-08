import java.util.Date;

public class ElectrolyteTest extends Test {
    private int sodiumResult;
    private float potassiumResult;
    private float calciumResult;
    private int chlorideResult;

    public ElectrolyteTest(int testID, int patientID, String testPanel, String deviceType, String deviceID, int operatorID, Date testDateTime, int sodiumResult, float potassiumResult, float calciumResult, int chlorideResult) {
        super(testID, patientID, testPanel, deviceType, deviceID, operatorID, testDateTime);
        this.sodiumResult = sodiumResult;
        this.potassiumResult = potassiumResult;
        this.calciumResult = calciumResult;
        this.chlorideResult = chlorideResult;
    }

    public int getSodiumResult() { return sodiumResult; }
    public float getPotassiumResult() { return potassiumResult; }
    public float getCalciumResult() { return calciumResult; }
    public int getChlorideResult() { return chlorideResult; }
}
