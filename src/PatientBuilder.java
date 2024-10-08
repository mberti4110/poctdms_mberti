import java.util.Date;

public class PatientBuilder {
    String patientLastName;
    String patientFirstName;
    Date dateOfBirth;
    String phoneNumber;
    String homeAddress;
    String patientMiddleName;

    public PatientBuilder(String patientLastName, String patientFirstName) {
        this.patientLastName = patientLastName;
        this.patientFirstName = patientFirstName;
    }

    public PatientBuilder dateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public PatientBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PatientBuilder homeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public PatientBuilder patientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
        return this;
    }

    public Patient build() {
        return new Patient(this);
    }
}
