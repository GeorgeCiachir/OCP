package paths;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.stream.StreamSupport;

public class SomeFileSystemStuff {

    public static void main(String[] args) {
        someFileSystemStuff();
    }

    private static void someFileSystemStuff() {
        System.out.println();
        System.out.println("********************** someFileSystemStuff ****************************");
        System.out.println();

        FileSystem fs = FileSystems.getDefault();

        System.out.println("File separator: " + fs.getSeparator());

        System.out.print("Root directories: ");
        StreamSupport.stream(fs.getRootDirectories().spliterator(), false)
                .forEach(System.out::println);

        System.out.print("File stores: ");
        StreamSupport.stream(fs.getFileStores().spliterator(), false)
                .forEach(System.out::println);
    }
}
