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

    public void setSodiumResult(int sodiumResult) {
        this.sodiumResult = sodiumResult;
    }

    public void setPotassiumResult(float potassiumResult) {
        this.potassiumResult = potassiumResult;
    }

    public void setCalciumResult(float calciumResult) {
        this.calciumResult = calciumResult;
    }

    public void setChlorideResult(int chlorideResult) {
        this.chlorideResult = chlorideResult;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSodium Result: " + sodiumResult + " mmol/L" +
                "\nPotassium Result: " + potassiumResult + " mmol/L" +
                "\nCalcium Result: " + calciumResult + " mg/dL" +
                "\nChloride Result: " + chlorideResult + "  mEq/L";
    }
}
