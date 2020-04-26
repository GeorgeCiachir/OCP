package copyingfilecontents;

import java.io.*;

public class FileReaderAndFileWriterExamples {

    public static void main(String[] args) throws IOException {
        copyAndWriteWithFileReaderAndWriter();
        copyAndWriteWithBufferedReaderAndWriter();
    }

    private static void copyAndWriteWithBufferedReaderAndWriter() throws IOException {
        File source = new File("anotherSource.txt");
        File destination = new File("thirdCopy.txt");

        try (FileReader fr = new FileReader(source);
             BufferedReader br = new BufferedReader(fr);
             FileWriter fw = new FileWriter(destination);
             BufferedWriter bw = new BufferedWriter(fw)) {

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    private static void copyAndWriteWithFileReaderAndWriter() throws IOException {
        File source = new File("anotherSource.txt");
        File destination = new File("thirdCopy.txt");

        try (FileReader fr = new FileReader(source);
             FileWriter fw = new FileWriter(destination)) {

            int value;
            while ((value = fr.read()) > 0) {
                fw.write(value);
            }
        }
    }
}
