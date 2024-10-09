import java.util.Date;
import java.util.Map;

public class TestFactory {
    public static Test createTest(TestType type, Map<String, Object> parameters) {
        String testPanel = type.getTestPanel();
        switch (type) {
            case GLUCOSE:
                return new GlucoseTest(
                        (Integer) parameters.get("testID"),
                        (Integer) parameters.get("patientID"),
                        testPanel,
                        (String) parameters.get("deviceType"),
                        (String) parameters.get("deviceID"),
                        (Integer) parameters.get("operatorID"),
                        (Date) parameters.get("testDateTime"),
                        (Integer) parameters.get("glucoseResult")
                );
            case SODIUM:
                return new SodiumTest(
                        (Integer) parameters.get("testID"),
                        (Integer) parameters.get("patientID"),
                        testPanel,
                        (String) parameters.get("deviceType"),
                        (String) parameters.get("deviceID"),
                        (Integer) parameters.get("operatorID"),
                        (Date) parameters.get("testDateTime"),
                        (Integer) parameters.get("sodiumResult")
                );
            // Implement cases for other test types
            case POTASSIUM:
                return new PotassiumTest(
                        (Integer) parameters.get("testID"),
                        (Integer) parameters.get("patientID"),
                        testPanel,
                        (String) parameters.get("deviceType"),
                        (String) parameters.get("deviceID"),
                        (Integer) parameters.get("operatorID"),
                        (Date) parameters.get("testDateTime"),
                        (Float) parameters.get("potassiumResult")
                );
            case CALCIUM:
                return new CalciumTest(
                        (Integer) parameters.get("testID"),
                        (Integer) parameters.get("patientID"),
                        testPanel,
                        (String) parameters.get("deviceType"),
                        (String) parameters.get("deviceID"),
                        (Integer) parameters.get("operatorID"),
                        (Date) parameters.get("testDateTime"),
                        (Float) parameters.get("calciumResult")
                );
            case CHLORIDE:
                return new ChlorideTest(
                        (Integer) parameters.get("testID"),
                        (Integer) parameters.get("patientID"),
                        testPanel,
                        (String) parameters.get("deviceType"),
                        (String) parameters.get("deviceID"),
                        (Integer) parameters.get("operatorID"),
                        (Date) parameters.get("testDateTime"),
                        (Integer) parameters.get("chlorideResult")
                );
            case LACTICACID:
                return new LacticAcidTest(
                        (Integer) parameters.get("testID"),
                        (Integer) parameters.get("patientID"),
                        testPanel,
                        (String) parameters.get("deviceType"),
                        (String) parameters.get("deviceID"),
                        (Integer) parameters.get("operatorID"),
                        (Date) parameters.get("testDateTime"),
                        (Float) parameters.get("lacticAcidResult")
                );
            case ELECTROLYTES:
                return new ElectrolyteTest(
                        (Integer) parameters.get("testID"),
                        (Integer) parameters.get("patientID"),
                        testPanel,
                        (String) parameters.get("deviceType"),
                        (String) parameters.get("deviceID"),
                        (Integer) parameters.get("operatorID"),
                        (Date) parameters.get("testDateTime"),
                        (Integer) parameters.get("sodiumResult"),
                        (Float) parameters.get("potassiumResult"),
                        (Float) parameters.get("calciumResult"),
                        (Integer) parameters.get("chlorideResult")
                );
            case HEMATOLOGY:
                return new HematologyTest(
                        (Integer) parameters.get("testID"),
                        (Integer) parameters.get("patientID"),
                        testPanel,
                        (String) parameters.get("deviceType"),
                        (String) parameters.get("deviceID"),
                        (Integer) parameters.get("operatorID"),
                        (Date) parameters.get("testDateTime"),
                        (Integer) parameters.get("hematocritResult"),
                        (Float) parameters.get("hemoglobinResult")
                );
            default:
                throw new IllegalArgumentException("Unknown test type: " + type);
        }
    }
}
