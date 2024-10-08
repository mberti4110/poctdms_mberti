import java.util.Scanner;

public class Menu {
    public void displayMenu() {
        System.out.println("1. Add Patient");
        System.out.println("2. Add Test Result");
        System.out.println("3. View Patients");
        System.out.println("4. View Test Results");
        System.out.println("5. Exit");
    }

    public int selectOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        return choice;
    }
}
