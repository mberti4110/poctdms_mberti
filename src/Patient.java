import java.util.Date;

public class Patient {
    private int patientID;
    private String patientLastName;
    private String patientFirstName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String homeAddress;
    private String patientMiddleName; // optional

    public Patient(PatientBuilder builder) {
        this.patientLastName = builder.patientLastName;
        this.patientFirstName = builder.patientFirstName;
        this.dateOfBirth = builder.dateOfBirth;
        this.phoneNumber = builder.phoneNumber;
        this.homeAddress = builder.homeAddress;
        this.patientMiddleName = builder.patientMiddleName;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getPatientID() { return patientID; }
    public String getPatientLastName() { return patientLastName; }
    public String getPatientMiddleName() { return patientMiddleName; }
    public String getPatientFirstName() { return patientFirstName; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getHomeAddress() { return homeAddress; }

    @Override
    public String toString() {
        return "Patient ID: " + patientID + "\nName: " + patientFirstName + " " + (patientMiddleName != null ? patientMiddleName + " " : "") + patientLastName + "\nDOB: " + dateOfBirth + "\nPhone: " + phoneNumber + "\nAddress: " + homeAddress;
    }
}
