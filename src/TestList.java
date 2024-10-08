import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TestList {
    private Map<Integer, Test> testList = new HashMap<>();
    private static AtomicInteger nextTestId = new AtomicInteger(1);

    public static int getNextTestId() {
        return nextTestId.getAndIncrement();
    }

    public void addTest(Test test) {
        testList.put(test.getTestID(), test);
    }

    public void removeTest(int testID) {
        testList.remove(testID);
    }

    public void updateTest(int testID, Test test) {
        testList.put(testID, test);
    }

    public Test getTest(int testID) {
        return testList.get(testID);
    }

    public Map<Integer, Test> listTests() {
        return testList;
    }

    public boolean hasDuplicateID(int testID) {
        return testList.containsKey(testID);
    }
}
