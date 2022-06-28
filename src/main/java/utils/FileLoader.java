package utils;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileLoader {
    List<List<String>> loadFile(String path) throws FileNotFoundException;
}
