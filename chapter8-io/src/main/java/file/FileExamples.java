package file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileExamples {

    public static void main(String[] args) throws IOException {
        operationsWithAFileInMemory();
        operationsWithAnActualFile();
        operationsWithAnActualDirectory();
    }

    private static void operationsWithAFileInMemory() {
        System.out.println();
        System.out.println("********************************** operationsWithAFileInMemory *************");
        System.out.println();

        File aFileInMemory = new File("thisIsAFileInMemory");
        System.out.println("exists: " + aFileInMemory.exists());
        System.out.println("isDirectory: " + aFileInMemory.isDirectory());
        System.out.println("isFile: " + aFileInMemory.isFile());
        System.out.println("length: " + aFileInMemory.length());
        System.out.println("getParent: " + aFileInMemory.getParent());
        System.out.println("getName: " + aFileInMemory.getName());
        System.out.println("getAbsolutePath: " + aFileInMemory.getAbsolutePath());
        System.out.println("lastModified: " + aFileInMemory.lastModified());
        System.out.println("delete: " + aFileInMemory.delete());
        File[] files = aFileInMemory.listFiles();
        if (files != null) {
            Arrays.stream(files).forEach(file -> System.out.print(file.getName() + " "));
        } else {
            System.out.println("No files");
        }
    }

    private static void operationsWithAnActualFile() throws IOException {
        System.out.println();
        System.out.println("********************************** operationsWithAnActualFile *************");
        System.out.println();

        File anActualFile = new File("anActualFile");
        if (anActualFile.exists()) {
            System.out.println("delete: " + anActualFile.delete());
        }
        System.out.println("createNewFile: " + anActualFile.createNewFile());
        System.out.println("exists: " + anActualFile.exists());
        System.out.println("isFile: " + anActualFile.isFile());

        File anotherFile = new File("anotherFile");
        System.out.println("Renaming initial: " + anActualFile + " to: " + anotherFile + " -> operation success " + anActualFile.renameTo(anotherFile));


    }

    private static void operationsWithAnActualDirectory() {
        System.out.println();
        System.out.println("********************************** operationsWithAnActualDirectory *************");
        System.out.println();

        File anActualDirectoryHere = new File("anActualDirectoryHere");
        if (anActualDirectoryHere.exists()) {
            System.out.println("delete: " + anActualDirectoryHere.delete());
        }
        System.out.println("mkdir: " + anActualDirectoryHere.mkdir());
        System.out.println("exists: " + anActualDirectoryHere.exists());
        System.out.println("isFile: " + anActualDirectoryHere.isDirectory());

        File aDirectoryDeeper = new File("parent\\anActualDirectoryDeeper");
        System.out.println("mkdir on a directory that has no parent: " + aDirectoryDeeper.mkdir());
        System.out.println("mkdirs on a directory that has no parent: " + aDirectoryDeeper.mkdirs());

        if (!new File("parent").delete()) {
            System.out.println("Cannot delete parent File(directory) because it has a child directory");
            System.out.println("Delete child: " + aDirectoryDeeper.delete());
            System.out.println("Delete parent: " + new File("parent").delete());
        }

    }
}
