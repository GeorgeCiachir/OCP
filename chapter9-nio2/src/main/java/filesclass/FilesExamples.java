package filesclass;

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
//        creatingFiles();
//        copyDirs();
//        copyFiles();
//        moveFiles();
        deletingFilesAndFolders();
    }

    private static void existsAndIsSameFile() throws IOException {
        Path thisPathExists = createFileIfNotExists("thisPathExists.txt");
        System.out.println("thisPathExists: " + Files.exists(thisPathExists));

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
    }

    private static void creatingDirs() {
        String anotherDirHere = ROOT_TEST_DIR + "anotherDirHere";
        String aDirHereThenALowerOne = ROOT_TEST_DIR + "aDirHere/aLowerDir";

        try {
            Files.createDirectory(Paths.get(aDirHereThenALowerOne));
        } catch (IOException e) {
            System.out.println("Could not create the lower directory, since the parent does not exist");
            try {
                System.out.println("Chained directory creation");
                Files.createDirectories(Paths.get(aDirHereThenALowerOne));

                Path anotherDir = Paths.get(anotherDirHere);
                Files.createDirectory(anotherDir);
            } catch (IOException ex) {
                System.out.println("The [anotherDirHere] could not be created using createDirectory() because it already exists");
                try {
                    System.out.println("If the [anotherDirHere] already exists, then createDirectories() leaves it like it is (including files that it might contain)");
                    Path anotherDir = Paths.get(anotherDirHere);
                    Files.createDirectories(anotherDir);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    private static void creatingFiles() {
        Path aFile = Paths.get(ROOT_TEST_DIR + "aFile");
        try {
            Files.createFile(aFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyDirs() throws IOException {
        String sourceDir = ROOT_TEST_DIR + "copyDirs/" + "source";
        String sourceFile = "firstFile.txt";

        String targetDir = ROOT_TEST_DIR + "copyDirs/" + "target";

        Files.copy(Paths.get(sourceDir), Paths.get(targetDir), REPLACE_EXISTING);
    }

    private static void copyFiles() throws IOException {
        String sourceDir = ROOT_TEST_DIR + "copyFiles/" + "source";
        String sourceFile = "firstFile.txt";

        String targetDir = ROOT_TEST_DIR + "copyFiles/" + "target";
        String targetFile = "secondFile.txt";

        Files.copy(Paths.get(sourceDir, sourceFile), Paths.get(targetDir, targetFile), REPLACE_EXISTING);
    }

    private static void moveFiles() throws IOException {
        String sourceDir = ROOT_TEST_DIR + "movingFiles/" + "source";
        Files.createDirectories(Paths.get(sourceDir));
        String sourceFile = "sourceFile.txt";
        String moveAndRename = "moveAndRename.txt";
        createFileIfNotExists(sourceDir + "/" + sourceFile);
        createFileIfNotExists(sourceDir + "/" + moveAndRename);

        String targetDir = ROOT_TEST_DIR + "movingFiles/" + "target";
        String keepOriginalName = sourceFile;
        String moveWithNewName = "moveWithNewName.txt";
        Files.move(Paths.get(sourceDir + "/" + sourceFile), Paths.get(targetDir + "/" + keepOriginalName), REPLACE_EXISTING);
        Files.move(Paths.get(sourceDir + "/" + moveAndRename), Paths.get(targetDir + "/" + moveWithNewName), REPLACE_EXISTING);
    }

    private static void deletingFilesAndFolders() throws IOException {
        String aFolder = ROOT_TEST_DIR + "aFolder";
        Files.createDirectories(Paths.get(aFolder));
        String aFile = "aFile.txt";
        createFileIfNotExists(aFolder + "/" + aFile);

        try {
            Files.delete(Paths.get(aFolder));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(Files.deleteIfExists(Paths.get("thisDoesntReallyExist")));
            Files.delete(Paths.get(aFolder, aFile));
            System.out.println(Files.deleteIfExists(Paths.get(aFolder)));
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
