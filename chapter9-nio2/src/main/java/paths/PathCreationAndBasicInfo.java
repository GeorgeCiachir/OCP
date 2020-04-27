package paths;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class PathCreationAndBasicInfo {


    public static void main(String[] args) {
        relativeToCurrentDirectory();
        createAndDisplayBasicInfoForPaths();
        comparePaths();
    }

    private static void relativeToCurrentDirectory() {
        System.out.println();
        System.out.println("********************** relativeToCurrentDirectory ****************************");
        System.out.println();

        System.out.println(Paths.get("..").toAbsolutePath());
        System.out.println(Paths.get("../").toAbsolutePath());
        List<String> first = Arrays.stream(Paths.get("..").toAbsolutePath().toFile().list()).sorted().collect(toList());
        List<String> second = Arrays.stream(Paths.get("../").toAbsolutePath().toFile().list()).sorted().collect(toList());
        System.out.println("The contents are the same: " + first.equals(second));

        System.out.println(Paths.get("").toAbsolutePath());
        System.out.println(Paths.get("./").toAbsolutePath());
        System.out.println(Paths.get(".").toAbsolutePath());
        List<String> third = Arrays.stream(Paths.get("").toAbsolutePath().toFile().list()).sorted().collect(toList());
        List<String> fourth = Arrays.stream(Paths.get(".").toAbsolutePath().toFile().list()).sorted().collect(toList());
        List<String> fifth = Arrays.stream(Paths.get("./").toAbsolutePath().toFile().list()).sorted().collect(toList());
        System.out.println("The contents are the same: " + (third.equals(fourth) && third.equals(fifth)));
    }

    private static void createAndDisplayBasicInfoForPaths() {
        System.out.println();
        System.out.println("********************** createAndDisplayBasicInfoForPaths ****************************");
        System.out.println();

        Path firstPath = Paths.get("..", "OCP", "somefile.txt");
        Path secondPath = Paths.get(firstPath.toAbsolutePath().toString());
        Path thirdPath = Paths.get("somefile.txt");
        Path fourthPath = Paths.get("/somefile.txt");

        Stream.of(firstPath, secondPath, thirdPath, fourthPath)
                .forEach(PathCreationAndBasicInfo::printInfo);
    }

    private static void printInfo(Path path) {
        if (Files.exists(path)) {
            path.toFile().delete();
        }

        System.out.println("toString: " + path);
        System.out.println("toUri: " + path.toUri());
        System.out.println("isAbsolute: " + path.isAbsolute());
        System.out.println("toAbsolutePath: " + path.toAbsolutePath());
        System.out.println("getFileName: " + path.getFileName());
        System.out.println("getParent: " + path.getParent());
        System.out.println("getRoot: " + path.getRoot());
        System.out.println("names: " + IntStream.range(0, path.getNameCount()).mapToObj(index -> path.getName(index).toString()).collect(joining(" ")));

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
    }

    private static void comparePaths() {
        System.out.println();
        System.out.println("********************** createAndDisplayBasicInfoForPaths ****************************");
        System.out.println();

        Path firstPath = Paths.get("..", "OCP", "somefile.txt");
        Path secondPath = Paths.get(firstPath.toAbsolutePath().toString());
        Path thirdPath = Paths.get("..", "OCP", "somefile.txt");

        System.out.println("[" + firstPath + "] equals [" + secondPath + "] : " + firstPath.equals(secondPath));
        System.out.println("[" + firstPath + "] equals [" + thirdPath + "] : " + firstPath.equals(thirdPath));
    }
}
