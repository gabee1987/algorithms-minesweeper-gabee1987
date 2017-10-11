import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/** <h2> Reads the file given in the argument, return a string list. </h2> */
public class FileReader {
    public static String[] readFile(String fileName) {
        List<String> readed = new ArrayList<>();
        try {
            Scanner data = new Scanner(new File(fileName));
            while (data.hasNextLine()) {
                readed.add(data.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
        String[] result = readed.stream().toArray(String[]::new);

        return result;
    }
}