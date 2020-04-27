package paths;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathOperations {

    public static void main(String[] args) {
        subPath();
        relativize();
        resolve();
        normalize();
    }

    // Works just like substring
    private static void subPath() {
        System.out.println();
        System.out.println("************** subPath ******************");
        System.out.println();

        Path path = Paths.get("/mammal/../carnivore/racoon.jpg");
        System.out.println("The initial path: " + path);
        System.out.println("subpath(0, 3): " + path.subpath(0, 3));
        System.out.println("subpath(1, 3): " + path.subpath(1, 3));
        System.out.println("subpath(1, 2): " + path.subpath(1, 2));
    }

    // Cannot relativize between relative and absolute paths
    // the paths have to have a folder in common (to which they are both relative)
    // -> relative paths: current folder
    // -> absolute paths: the root
    private static void relativize() {
        System.out.println();
        System.out.println("************** relativize ******************");
        System.out.println();

        Path fish = Paths.get("../path/to/a/fish");
        Path shark = Paths.get("../../../shark");
        System.out.println("Fish location: " + fish);
        System.out.println("Shark location: " + shark);
        System.out.println("From fish to shark: " + fish.relativize(shark));
        System.out.println("From shark to fish: " + shark.relativize(fish));

        System.out.println();

        Path bird = Paths.get("bird");
        Path tree = Paths.get("tree");
        System.out.println("Bird location: " + bird);
        System.out.println("Tree location: " + tree);
        System.out.println("From bird to tree: " + bird.relativize(tree));
        System.out.println("From tree to bird: " + tree.relativize(bird));

        System.out.println();

        bird = Paths.get("foo/foo/bird").toAbsolutePath();
        tree = Paths.get("tree").toAbsolutePath();
        System.out.println("Bird location: " + bird);
        System.out.println("Tree location: " + tree);
        System.out.println("From bird to tree: " + bird.relativize(tree));
        System.out.println("From tree to bird: " + tree.relativize(bird));
    }

    // It basically concatenates 2 paths
    // If the second path is absolute, it is returned as the result
    private static void resolve() {
        System.out.println();
        System.out.println("************** resolve ******************");
        System.out.println();

        Path current = Paths.get("current");
        Path shark = Paths.get("../../../shark");
        System.out.println("Current location: " + current);
        System.out.println("Shark location: " + shark);
        System.out.println(current.resolve(shark));

        Path absolutePath = Paths.get("c:/come/absolute/path");
        System.out.println("AbsolutePath: " + absolutePath);
        System.out.println(current.resolve(absolutePath));
    }

    private static void normalize() {
        System.out.println();
        System.out.println("************** normalize relative paths ******************");
        System.out.println();

        Path relativePath = Paths.get("../some/../some/relative/path");
        System.out.println(relativePath.normalize());

        Path fish = Paths.get("../path/to/a/fish");
        Path shark = Paths.get("../../../shark");
        Path relativeFromFishToShark = fish.relativize(shark);
        System.out.println("relativeFromFishToShark: " + relativeFromFishToShark);
        System.out.println("fish.resolve(relativeFromFishToShark): " + fish.resolve(relativeFromFishToShark));
        System.out.println("fish.resolve(relativeFromFishToShark).normalize(): " + fish.resolve(relativeFromFishToShark).normalize());

        System.out.println();
        System.out.println("**************************************************************");
        System.out.println();

        Path data = Paths.get("c:/data");
        Path home = Paths.get("c:/user/home");
        Path fromDataToHome = data.relativize(home);

        System.out.println("Relative from data to home: " + fromDataToHome);
        System.out.println("Resolved from data to home: " + data.resolve(fromDataToHome));
        System.out.println("Normalized: " + data.resolve(fromDataToHome).normalize());
    }
}
