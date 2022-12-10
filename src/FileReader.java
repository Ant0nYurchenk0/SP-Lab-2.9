import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
  public static String Read(String path){
    try {
      File file = new File(path);
      Scanner reader = new Scanner(file);
      var fileData = new StringBuilder();
      while (reader.hasNextLine()) {
        var newLine = "";
        if (fileData.length() != 0){        
          newLine="\n";
        }
        fileData.append(newLine+reader.nextLine());
      }
      reader.close();
      return fileData.toString();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred: file not found");
      e.printStackTrace();
      System.exit(1);
      return null;
    }
  }   
}
