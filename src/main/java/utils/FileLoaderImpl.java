package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileLoaderImpl implements FileLoader {

    public List<List<String>> loadFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(path.trim())));
        List<List<String>> stringList = new ArrayList<>();
        while (scanner.hasNext()) {
            stringList.add(Arrays.asList((scanner.nextLine()).split(" ")));
        }
        return stringList;
    }
}
