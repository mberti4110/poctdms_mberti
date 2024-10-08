import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public List<Patient> readPatients(String filePath) throws IOException, ClassNotFoundException {
        List<Patient> patients = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            patients = (List<Patient>) ois.readObject();
        }
        return patients;
    }

    public List<Test> readTests(String filePath) throws IOException, ClassNotFoundException {
        List<Test> tests = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            tests = (List<Test>) ois.readObject();
        }
        return tests;
    }

    public boolean writePatients(String filePath, List<Patient> patients) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(patients);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean writeTests(String filePath, List<Test> tests) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(tests);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
