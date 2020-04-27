package filesclass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class ReadingFileContentsAndAttributes {

    public static void main(String[] args) throws IOException {
//        usingBufferedReaderAndBufferedWriter();
//        readAllLines();
        readingFileAttributes();

    }

    private static void readingFileAttributes() throws IOException {
        Path someFile = Paths.get("somefile.txt");
        BasicFileAttributes basicFileAttributes = Files.readAttributes(someFile, BasicFileAttributes.class);
        System.out.println(basicFileAttributes.isRegularFile());
        BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(someFile, BasicFileAttributeView.class);
        System.out.println(basicFileAttributeView.readAttributes().isOther());
    }

    /*
     * Careful with this one because it stores all the contents of the file in memory
     * An OutOfMemoryError might be thrown
     */
    private static void readAllLines() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("....."));
    }

    /*
     * Identical to using BufferedReader and Writer, Input and OutputStream
     * with the mention that in case kof using the BufferedWriter on a path, it also creates
     * it if it doesn't already exist
     * Also, additional options can be passed to the method (overwrite existing, append to the existing contents etc...)
     */
    private static void usingBufferedReaderAndBufferedWriter() throws IOException {
        Files.newBufferedReader(Paths.get("....."));
        Files.newBufferedWriter(Paths.get("....."));
        Files.newInputStream(Paths.get("....."));
        Files.newOutputStream(Paths.get("....."));
    }
}
