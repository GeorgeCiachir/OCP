import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReaderAndFileWriterExamples {

    public static void main(String[] args) throws IOException {
        // with this approach we are keeping the entire file in memory, in a list of strings
        // given that the file is very small in this case, it is not an issue
        // with large files this approach is not the best and we should write after each line read
        List<String> lines = copyFromFile();
        writeToFile(lines);
    }

    private static List<String> copyFromFile() throws IOException {
        File file = new File("anotherSource.txt");
        List<String> fileLines = new ArrayList<>();

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {

            String fileLine;

            while ((fileLine = br.readLine()) != null) {
                fileLines.add(fileLine);
            }
        }

        return fileLines;
    }

    private static void writeToFile(List<String> lines) throws IOException {
        File file = new File("thirdCopy.txt");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)) {

            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }

        }

    }

}
