/* DMS Project: Point of Care
 * October 13, 2024
 * CEN 3024C
 * Monica Berti
 * User Class
 * This class creates a user class and sets data requirements.
 */

public class User {
    private int operatorID;
    private String operatorLastName;
    private String operatorFirstName;
    private String operatorPassword;

    public User(int operatorID, String operatorLastName, String operatorFirstName, String operatorPassword) {
        this.operatorID = operatorID;
        this.operatorLastName = operatorLastName;
        this.operatorFirstName = operatorFirstName;
        this.operatorPassword = operatorPassword;
    }

    public int getOperatorID() {
        return operatorID;
    }

    public String getOperatorLastName() {
        return operatorLastName;
    }

    public String getOperatorFirstName() {
        return operatorFirstName;
    }

    public String getOperatorPassword() {
        return operatorPassword;
    }

    @Override
    public String toString() {
        return "User ID: " + operatorID +
                "\nLast Name: " + operatorLastName +
                "\nFirst Name: " + operatorFirstName;
    }
}
