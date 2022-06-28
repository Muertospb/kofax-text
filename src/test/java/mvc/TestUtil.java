package mvc;

import service.FrameLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class TestUtil {
    public static List<FrameLine> getTestFrame() {
        List<FrameLine> frame = new ArrayList<>();
        frame.add(new FrameLine(Arrays.asList(10, 10, 20), Arrays.asList("First", "test", "line"), Arrays.asList(50, 60, 50)));
        frame.add(new FrameLine(Arrays.asList(20, 10, 10), Arrays.asList("Second", "test", "line"), Arrays.asList(50, 60, 50)));
        frame.add(new FrameLine(Arrays.asList(20, 20, 10), Arrays.asList("Third", "test", "line"), Arrays.asList(50, 60, 50)));
        frame.add(new FrameLine(Arrays.asList(10, 20, 20), Arrays.asList("Fourth", "test", "line"), Arrays.asList(50, 60, 50)));
        return frame;
    }

    public static List<List<String>> getList() {
        List<List<String>> stringList = new ArrayList<>();
        String testString = "This string was made for test purposes only!";
        for (int i = 0; i < 50; i++) {
            stringList.add(Arrays.asList(testString.split(" ")));
        }
        return stringList;
    }

    public static Properties getTestProperties() {
        Properties properties = new Properties();
        properties.put("oneSizedWordsInRow", "5");
        properties.put("fontSizes", "5,10,15");
        return properties;
    }
}
