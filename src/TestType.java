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
