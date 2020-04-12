package creatingpaths;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExamples {

    public static void main(String[] args) {
//        toStringGetNameCountGetName();
//        getFileNameGetParentGetRoot();
//        subPath();
//        relativize();
//        joinPaths();
//        normalize();
        toRealPath();
    }

    private static void toRealPath() {
        Path currentDirPath = Paths.get(".");
        Path doesNotExist = Paths.get("../thisfiledoesnotexist.txt");
        Path thisPathsExists = Paths.get("../OCP/somefile.txt");

        try {
            System.out.println(currentDirPath.toAbsolutePath());
            System.out.println(currentDirPath.toAbsolutePath().normalize());
            System.out.println(currentDirPath.toRealPath());
            System.out.println();
            System.out.println();
            System.out.println(thisPathsExists.toAbsolutePath());
            System.out.println(thisPathsExists.toAbsolutePath().normalize());
            System.out.println(thisPathsExists.toRealPath());
            System.out.println();
            System.out.println();
            System.out.println(doesNotExist.toAbsolutePath());
            System.out.println(doesNotExist.toAbsolutePath().normalize());
            System.out.println(doesNotExist.toRealPath()); //throws IOE if it doesn't exist
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void normalize() {
        Path smellyPath = Paths.get("../../../smells.jpg");
        Path fishyPath = Paths.get("../path/to/a/fish.txt");
        Path rel = smellyPath.relativize(fishyPath);

        System.out.println("From the smells to the fish: " + rel);
        System.out.println("Resolved from smells to fish: " + smellyPath.resolve(rel));
        System.out.println("Normalized: " + smellyPath.resolve(rel).normalize());

        System.out.println();
        System.out.println("**************************************************************");
        System.out.println();

        Path first = Paths.get("c:/data");
        Path second = Paths.get("c:/user/home");
        Path relativizeFromFirstToSecond = first.relativize(second);

        System.out.println("Relative from first to second: " + relativizeFromFirstToSecond);
        System.out.println("Resolved from first to second: " + first.resolve(relativizeFromFirstToSecond));
        System.out.println("Normalized: " + first.resolve(relativizeFromFirstToSecond).normalize());
    }

    private static void joinPaths() {
        Path first = Paths.get("/cats/../panther");
        Path second = Paths.get("food");
        System.out.println(first.resolve(second));

        first = Paths.get("c:/cats/legs");
        second = Paths.get("c:/birds/wings");
        System.out.println(first.resolve(second));
    }

    private static void relativize() {
        Path fishyPath = Paths.get("../path/to/a/fish.txt");
        Path smellyPath = Paths.get("../../../smells.jpg");
        System.out.println("From the fish to the smells: " + fishyPath.relativize(smellyPath));
        System.out.println("From the smells to the fish: " + smellyPath.relativize(fishyPath));

        Path bird1 = Paths.get("bird1.txt");
        Path bird2 = Paths.get("bird2.txt");
        System.out.println("From bird1 to bird2: " + bird1.relativize(bird2));
        System.out.println("From bird2 to bird1: " + bird2.relativize(bird1));
    }

    private static void subPath() {
        Path path = Paths.get("/mammal/carnivore/racoon.jpg");
        System.out.println(path.getName(0));
        System.out.println(path.getName(1));
        System.out.println(path.getName(2));
        System.out.println(path.subpath(0, 3));
        System.out.println(path.subpath(1, 3));
        System.out.println(path.subpath(1, 2));
    }

    private static void getFileNameGetParentGetRoot() {
        Path relativePath1 = Paths.get("/land/hippo/harry.happy");
        System.out.println("Starting with: " + "/land/hippo/harry.happy");
        System.out.println("Is absolute path: " + relativePath1.isAbsolute());
        System.out.println("Absolute path: " + relativePath1.toAbsolutePath());
        System.out.println("Filename: " + relativePath1.getFileName());
        System.out.println("Parent: " + relativePath1.getParent());
        System.out.println("Root: " + relativePath1.getRoot());

        System.out.println();

        Path relativePath2 = Paths.get("land/hippo/harry.happy");
        System.out.println("Starting with: " + "land/hippo/harry.happy");
        System.out.println("Is absolute path: " + relativePath2.isAbsolute());
        System.out.println("Absolute path: " + relativePath2.toAbsolutePath());
        System.out.println("Filename: " + relativePath2.getFileName());
        System.out.println("Parent: " + relativePath2.getParent());
        System.out.println("Root: " + relativePath2.getRoot());

        System.out.println();

        Path absPath = Paths.get("c:/land/hippo/harry.happy");
        System.out.println("Starting with: " + "c:/land/hippo/harry.happy");
        System.out.println("Is absolute path: " + absPath.isAbsolute());
        System.out.println("Absolute path: " + absPath.toAbsolutePath());
        System.out.println("Filename: " + absPath.getFileName());
        System.out.println("Parent: " + absPath.getParent());
        System.out.println("Root: " + absPath.getRoot());

    }

    private static void toStringGetNameCountGetName() {
        System.out.println();
        System.out.println("************************");
        System.out.println();
        Path path = Paths.get("/land/hippo/harry.happy");
        System.out.println("Path is: " + path);

        for (int i = 0; i < path.getNameCount(); i++) {
            Path pathName = path.getName(i);
            System.out.println("    Element " + i + " is: " + pathName);
        }
    }
}
