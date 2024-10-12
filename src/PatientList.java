import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PatientList {
    private Map<Integer, Patient> patientList = new HashMap<>();
    private static final AtomicInteger idCounter = new AtomicInteger();

    public void addPatient(Patient patient) {
        int id = idCounter.incrementAndGet();  // Get new ID
        patient.setPatientID(id);
        patientList.put(id, patient);
        patientList.put(patient.getPatientID(), patient);
    }
    //check if patient exists
    public boolean exists(int patientId) {
        return patientList.containsKey(patientId);
    }

    public Patient getPatientById(int patientID) {
        return patientList.get(patientID);
    }

    public void removePatient(int patientID) {
        patientList.remove(patientID);
    }

    public void updatePatient(int patientID, Patient patient) {
        patientList.put(patientID, patient);
    }

    public Patient getPatient(int patientID) {
        return patientList.get(patientID);
    }

    public Map<Integer, Patient> listPatients() {
        return patientList;
    }

    public boolean hasDuplicateID(int patientID) {
        return patientList.containsKey(patientID);
    }
}
