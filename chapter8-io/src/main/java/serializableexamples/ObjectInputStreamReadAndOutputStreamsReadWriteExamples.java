package serializableexamples;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectInputStreamReadAndOutputStreamsReadWriteExamples {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File animalsFile = new File("animalsWritenWithStreams.txt");

        if (animalsFile.exists()) {
            animalsFile.delete();
        }
        animalsFile.createNewFile();

        Animal michael = new Animal("Tiger Michael", 26, 'T', "staticValue michael", " transientValue michael");
        Animal gina = new Animal("Elephant Gina", 21, 'E', "staticValue gina", "transientValue gina");
        List<Animal> written = new ArrayList<>();
        written.add(michael);
        written.add(gina);
        written.add(null);
        writeAnimals(animalsFile, written);

        List<Animal> read = getAnimals(animalsFile);
        System.out.println(read);

    }


    private static List<Animal> getAnimals(File file) throws IOException, ClassNotFoundException {
        List<Animal> animals = new ArrayList<>();

        try (ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            while (true) {
                Object read = is.readObject();
                // Attention here ! null object can also be written, so calling instanceof is essentially filtering out the null objects
                if (read instanceof Animal) {
                    animals.add((Animal) read);
                }
            }
        } catch (EOFException eof) {
            System.out.println("EOF reached; exception caught");
        }

        return animals;
    }


    private static void writeAnimals(File file, List<Animal> animals) throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (Animal animal : animals) {
                os.writeObject(animal);
            }
        }
    }
}
