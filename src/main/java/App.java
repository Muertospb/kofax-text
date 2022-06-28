import mvc.Controller;
import mvc.Model;
import mvc.View;
import service.StateHolder;
import utils.FileLoader;
import utils.FileLoaderImpl;
import utils.PropertiesLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


public class App {

    public static void main(String[] args) {

        Properties properties = null;
        try {
            properties = PropertiesLoader.loadProperties();
        } catch (IOException e) {
            System.out.println("Cannot load \"application.properties\", will use default properties");
        }

        View view = new View();
        view.initFonts(properties);

        FileLoader fileLoader = new FileLoaderImpl();

        List<List<String>> list;
        while (true) {
            System.out.print("Please, enter filepath: ");
            Scanner in = new Scanner(System.in);
            String path = in.nextLine();
            try {
                list = fileLoader.loadFile(path);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please try again.");
            }
        }

        Model model = new Model();
        model.setStateHolder(new StateHolder());
        model.setStringList(list);

        Controller controller = new Controller(view, model);
    }
}