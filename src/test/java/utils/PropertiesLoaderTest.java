package utils;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertiesLoaderTest {

    @SneakyThrows
    @Test
    void loadPropertiesTest() {
        //when
        Properties properties = PropertiesLoader.loadProperties();

        //then
        assertEquals(2, properties.size());
        assertEquals("30,40,50", properties.getProperty("fontSizes"));
        assertEquals("15", properties.getProperty("oneSizedWordsInRow"));
    }
}