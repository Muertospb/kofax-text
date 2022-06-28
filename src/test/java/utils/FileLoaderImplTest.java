package utils;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileLoaderImplTest {

    @SneakyThrows
    @Test
    void loadFileTest() {
        //when
        List<List<String>> stringList = new FileLoaderImpl().loadFile("src/test/resources/text");

        //then
        assertEquals(4, stringList.size());
        assertEquals(2, stringList.get(0).size());
        assertEquals(1, stringList.get(1).size());
    }

    @Test()
    void loadMissingFileTest() {
        //when
        Exception exception = assertThrows(FileNotFoundException.class, () -> new FileLoaderImpl().loadFile("src/test/resources/faketext"));

        //then
        assertTrue(exception.getMessage().contains("faketext"));
    }
}