package creatingpaths;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.StreamSupport;

public class CreatingPathsExamples {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        if (!Paths.get("somefile.txt").toFile().exists()) {
            Paths.get("somefile.txt").toFile().createNewFile();
        }

        Path path = Paths.get("..", "OCP", "somefile.txt");
        boolean isFile = path.toFile().isFile();
        System.out.println(isFile);
        System.out.println(path.toUri());
        System.out.println();
        System.out.println(path.toString());
        System.out.println(path.toUri());
        System.out.println(Paths.get("somefile.txt").toUri());

        System.out.println("Print the files");
//        Arrays.stream(Paths.get("./").toFile().listFiles())
//                .forEach(f -> System.out.println(f));
        System.out.println("Print the files as strings(names)");
//        Arrays.stream(Paths.get("./").toFile().list())
//                .forEach(f -> System.out.println(f));


        System.out.println();
        System.out.println("************Operate with files that don't exist");
        System.out.println();
        System.out.println("Parent: " + Paths.get("c:/thisfiledoesnotexist").getParent());
        System.out.println("Root: " + Paths.get("c:\\thisfiledoesnotexist").getRoot());
        System.out.println("File name: " + Paths.get("c:/thisfiledoesnotexist").getFileName());
        System.out.println("Name count: " + Paths.get("c:/xx/thisfiledoesnotexist").getNameCount());
        System.out.println("Name[0]: " + Paths.get("c:/xx/thisfiledoesnotexist").getName(0));
        System.out.println("Name[1]: " + Paths.get("c:/xx/thisfiledoesnotexist").getName(1));
        System.out.println("Exists: " + Paths.get("c:/xx/../thisfiledoesnotexist").toFile().exists());

        System.out.println(Paths.get("c:/xx/../thisfiledoesnotexist").toAbsolutePath().toUri());
        System.out.println(Paths.get("c:/xx/../thisfiledoesnotexist").toUri());
        System.out.println(Paths.get("c:/xx/../thisfiledoesnotexist").getFileName().toUri());
        System.out.println(Paths.get("c:/xx/../thisfiledoesnotexist").getFileName().toAbsolutePath().toUri());
        System.out.println(Paths.get("c:/xx/../thisfiledoesnotexist").getFileName().toAbsolutePath().getNameCount());
        System.out.println();
        System.out.println("***************");
        System.out.println();

        FileSystem fs = FileSystems.getDefault();
        System.out.println(fs.getPath("somefile.txt").toUri());
        System.out.println("File separator: " + fs.getSeparator());
        StreamSupport.stream(fs.getRootDirectories().spliterator(), false)
                .forEach(System.out::println);
        StreamSupport.stream(fs.getFileStores().spliterator(), false)
                .forEach(System.out::println);

        System.out.println(Paths.get("./").toFile().exists());
        System.out.println(Paths.get("./").toFile().isDirectory());
    }
}
