package traversingdirectories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.function.Predicate;

import static java.time.temporal.ChronoUnit.DAYS;

public class TraversingExamples {

    private static final Path LOCAL_ROOT = Paths.get("");

    public static void main(String[] args) throws IOException {
        readFileContents();
//        listCurrentDirContents();
//        walkDir();
//        findPaths();
    }

    private static void readFileContents() throws IOException {
        Path thisActualFile = Files.walk(LOCAL_ROOT, 20)
                .filter(p -> extractLast(p).toString().equals(TraversingExamples.class.getSimpleName() + ".java"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("You are not doing it right :)"));

        Files.lines(thisActualFile)
                .forEach(System.out::println);
        Files.readAllLines(thisActualFile)
                .forEach(System.out::println);

    }

    private static void listCurrentDirContents() throws IOException {
        Files.list(LOCAL_ROOT)
                .filter(p -> !Files.isDirectory(p))
                .map(Path::toAbsolutePath)
                .forEach(System.out::println);
    }

    private static void walkDir() {
        try {
            Files.walk(LOCAL_ROOT, 20)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findPaths() throws IOException {
        Predicate<Path> pathPredicate = p -> {
            Path last = extractLast(p);
            return last.toString().endsWith(".java") && last.toString().contains("r");
        };
        Instant yesterday = Instant.now().minus(1, DAYS);
        Predicate<BasicFileAttributes> basicFileAttributesPredicate = a -> a.lastModifiedTime().toInstant().isAfter(yesterday);
        Files.find(LOCAL_ROOT, 20, (p, a) -> pathPredicate.test(p) && basicFileAttributesPredicate.test(a))
                .forEach(System.out::println);
    }

    private static Path extractLast(Path initial) {
        int nameCount = initial.getNameCount();
        return initial.getName(nameCount - 1);
    }
}
