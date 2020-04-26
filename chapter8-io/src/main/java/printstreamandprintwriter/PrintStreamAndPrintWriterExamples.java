package printstreamandprintwriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PrintStreamAndPrintWriterExamples {

    public static void main(String[] args) throws IOException {
        File animalsFile = new File("animalsWritenWithPrinters.txt");

        if (animalsFile.exists()) {
            animalsFile.delete();
        }
        animalsFile.createNewFile();

        Animal michael = new Animal("Tiger Michael", 26, 'T');
        Animal gina = new Animal("Elephant Gina", 21, 'E');
        List<Animal> written = new ArrayList<>();
        written.add(michael);
        written.add(gina);
        written.add(null);

//        writeAnimalsWithPrintWriterOverAWriter(animalsFile, written);
//        writeAnimalsWithPrintWriterOverAnOutputStream(animalsFile, written);
//        writeAnimalsWithPrintWriter(animalsFile, written);

//        writeAnimalsWithPrintStreamOverAnOutputStream(animalsFile, written);
        writeAnimalsWithPrintStream(animalsFile, written);
    }

    private static void writeAnimalsWithPrintWriterOverAWriter(File file, List<Animal> animals) throws IOException {
        try (FileWriter fr = new FileWriter(file);
             BufferedWriter br = new BufferedWriter(fr);
             PrintWriter pw = new PrintWriter(br)) {
            for (Animal animal : animals) {
                pw.print(animal);
                pw.println();
            }
        }
    }

    private static void writeAnimalsWithPrintWriterOverAnOutputStream(File file, List<Animal> animals) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             PrintWriter pw = new PrintWriter(bos)) {
            for (Animal animal : animals) {
                pw.print(animal);
                pw.println();
            }
        }
    }

    private static void writeAnimalsWithPrintWriter(File file, List<Animal> animals) throws IOException {
        try (PrintWriter pw = new PrintWriter(file)) {
            for (Animal animal : animals) {
                pw.print(animal);
                pw.println();
            }
        }
    }

    private static void writeAnimalsWithPrintStreamOverAnOutputStream(File file, List<Animal> animals) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             PrintStream ps = new PrintStream(bos)) {
            for (Animal animal : animals) {
                ps.print(animal);
                ps.println();
            }
        }
    }

    private static void writeAnimalsWithPrintStream(File file, List<Animal> animals) throws IOException {
        try (PrintStream ps = new PrintStream(file)) {
            for (Animal animal : animals) {
                ps.print(animal);
                ps.println();
            }
        }
    }
}
