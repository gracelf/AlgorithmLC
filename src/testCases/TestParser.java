package testCases;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class TestParser {

    public Map<String, String> parse(String identifier) {

        final String[] splitID = identifier.split(",");
        final Map<String, String> resultMap = new HashMap<String, String>(10);

        resultMap.put("field1", splitID[0]);
        resultMap.put("field2", splitID[1]);
        resultMap.put("field3", splitID[2]);
        resultMap.put("field4", splitID[3]);
        resultMap.put("field5", splitID[4]);
        resultMap.put("field6", splitID[5]);
        resultMap.put("field7", splitID[6]);
        resultMap.put("field8", splitID[7]);
        resultMap.put("field9", splitID[8]);
        resultMap.put("field10", splitID[9]);

        return resultMap;

    }

    public static void main(String[] args) {
        TestParser test = new TestParser();
        test.parse("1,2,3,4,5,6,7");
    }
}
