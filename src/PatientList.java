/* DMS Project: Point of Care
 * October 13, 2024
 * CEN 3024C
 * Monica Berti
 * PatientList Class
 * This class creates a list of patients in which they will be stored in.
 */

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

    // delete patient

    //check if patient exists
    public boolean exists(int patientId) {
        return patientList.containsKey(patientId);
    }

    public boolean deletePatient(int patientID) {
        if (patientList.containsKey(patientID)) {
            patientList.remove(patientID);
            return true;
        } else {
            return false;
        }
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
