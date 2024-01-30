import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String FILE_NAME = "src\\goalsDB.txt";
        Path pathToFile = Path.of(System.getProperty("user.dir"), FILE_NAME);
        try{
            List<String> lines = Files.readAllLines(pathToFile);
            for (String line : lines){
                System.out.println(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}