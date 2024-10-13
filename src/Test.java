import java.util.Date;

public class Test {
    private int testID;
    private int patientID;
    private String testPanel;
    private String deviceType;
    private String deviceID;
    private int operatorID;
    private Date testDateTime;

    public Test(int testID, int patientID, String testPanel, String deviceType, String deviceID, int operatorID, Date testDateTime) {
        this.testID = testID;
        this.patientID = patientID;
        this.testPanel = testPanel;
        this.deviceType = deviceType;
        this.deviceID = deviceID;
        this.operatorID = operatorID;
        this.testDateTime = testDateTime;
    }

    public int getTestID() { return testID; }
    public int getPatientID() { return patientID; }
    public String getTestPanel() { return testPanel; }
    public String getDeviceType() { return deviceType; }
    public String getDeviceID() { return deviceID; }
    public int getOperatorID() { return operatorID; }
    public Date getTestDateTime() { return testDateTime; }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setTestPanel(String testPanel) {
        this.testPanel = testPanel;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public void setOperatorID(int operatorID) {
        this.operatorID = operatorID;
    }

    public void setTestDateTime(Date testDateTime) {
        this.testDateTime = testDateTime;
    }

    @Override
    public String toString() {
        return "Test ID: " + testID +
                "\nPatient ID: " + patientID +
                "\nTest Panel: " + testPanel +
                "\nDevice Type: " + deviceType +
                "\nDevice ID: " + deviceID +
                "\nOperator ID: " + operatorID +
                "\nTest Date and Time: " + testDateTime;
    }
}
