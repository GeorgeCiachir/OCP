package serializableexamples;

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

        Animal michael = new Animal("Tiger Michael", 26, 'T', "staticValue michael", " transientValue michael");
        Animal gina = new Animal("Elephant Gina", 21, 'E', "staticValue gina", "transientValue gina");
        List<Animal> written = new ArrayList<>();
        written.add(michael);
        written.add(gina);

//        writeAnimalsWithPrintWriterOverAnOutputStream(animalsFile, written);
//        writeAnimalsWithPrintWriter(animalsFile, written);
        writeAnimalsWithPrintStream(animalsFile, written);
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

    private static void writeAnimalsWithPrintStream(File file, List<Animal> animals) throws IOException {
        try (PrintStream pw = new PrintStream(file)) {
            for (Animal animal : animals) {
                pw.print(animal);
                pw.println();
            }
        }
    }


}
