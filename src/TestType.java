/* DMS Project: Point of Care
 * October 13, 2024
 * CEN 3024C
 * Monica Berti
 * TestType Enum
 * This enum represents different types of point of care tests,
 * and will return a descriptive string which provides the name of the
 * test panel.
 */
public enum TestType {
    GLUCOSE("Glucose"),
    SODIUM("Sodium"),
    POTASSIUM("Potassium"),
    CALCIUM("Calcium"),
    CHLORIDE("Chloride"),
    LACTICACID("Lactic Acid"),
    ELECTROLYTES("Electrolytes"),
    HEMATOLOGY("Hematology");

    private final String testPanel;

    TestType(String testPanel) {
        this.testPanel = testPanel;
    }

    public String getTestPanel() {
        return this.testPanel;
    }
}
