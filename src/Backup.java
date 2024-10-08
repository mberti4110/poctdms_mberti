import java.io.*;

public class Backup {
    public boolean createBackup(String filePath, Object data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object restoreBackup(String backupPath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(backupPath))) {
            return ois.readObject();
        }
    }
}
