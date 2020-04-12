package usingfilesclass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FilesExamples {

    private static final String ROOT_TEST_DIR = "rootTestDir/";

    public static void main(String[] args) throws IOException {
//        existsAndIsSameFile();
//        creatingDirs();
//        copyFromDirToDir();
        moveFiles();
    }

    private static void moveFiles() throws IOException {
        String sourceDir = ROOT_TEST_DIR + "movingFiles/" + "sourceDir";
        String sourceFile = "sourceFile.txt";
        createDirIfNotExists(sourceDir);
        createFileIfNotExists(sourceDir + "/" + sourceFile);

        String targetDir = ROOT_TEST_DIR + "movingFiles/" + "targetDir";
        String targetFile = sourceFile; //keep the original name
//        Files.createDirectories(Paths.get(targetDir));
        Files.move(Paths.get(sourceDir + "/" + sourceFile), Paths.get(targetDir + "/" + targetFile), REPLACE_EXISTING);

    }

    private static void copyFromDirToDir() throws IOException {
        String firstDir = ROOT_TEST_DIR + "firstDir";
        String firstFile = "firstFile.txt";
        createDirIfNotExists(firstDir);
        createFileIfNotExists(firstDir + "/" + firstFile);

        String secondDir = ROOT_TEST_DIR + "secondDir";
        String secondFile = "secondFile.txt";
        Files.createDirectories(Paths.get(secondDir));


        Files.copy(Paths.get(firstDir + "/" + firstFile), Paths.get(secondDir + "/" + secondFile), REPLACE_EXISTING);
    }

    private static void creatingDirs() {
        String anotherDirHere = ROOT_TEST_DIR + "anotherDirHere";
        String aDirHereThenALowerOne = ROOT_TEST_DIR + "aDirHere/aLowerDir";

        try {
            Files.createDirectory(Paths.get(aDirHereThenALowerOne));
        } catch (IOException e) {
            System.out.println("First block exception caught");
            try {
                Files.createDirectories(Paths.get(aDirHereThenALowerOne));
                Path anotherDir = Paths.get(anotherDirHere);
                if (Files.exists(anotherDir)) {
                    Files.delete(anotherDir);
                }
                Files.createDirectory(anotherDir);
            } catch (IOException ex) {
                System.out.println("Second block exception caught");
            }
        }
    }

    private static void existsAndIsSameFile() throws IOException {
        Path thisPathExists = Paths.get("./someFile.txt");
        System.out.println(Files.exists(thisPathExists));

        Path first = Paths.get("../someRandomFile.txt");
        Path second = Paths.get("../someRandomFile.txt");
        System.out.println("Equals using reference: " + (first == second));
        System.out.println("Equals using equals: " + first.equals(second));
        System.out.println("The same using Files: " + Files.isSameFile(first, second));
        try {
            System.out.println("The same using Files: " + Files.isSameFile(first, thisPathExists)); // it throws NoSuchFileException because one of the files does not exist
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path firstFile = createFileIfNotExists("firstFile.txt");
        Path secondFile = createFileIfNotExists("secondFile.txt");
        Path link = createFileIfNotExists("link.txt");


        Files.createSymbolicLink(link, firstFile);
        Files.createSymbolicLink(link, secondFile);

        System.out.println(Files.isSameFile(firstFile, secondFile));

    }

    private static void createDirIfNotExists(String dirName) throws IOException {
        Path path = Paths.get(dirName);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    private static Path createFileIfNotExists(String name) throws IOException {
        Path path = Paths.get(name);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        return path;
    }

}
