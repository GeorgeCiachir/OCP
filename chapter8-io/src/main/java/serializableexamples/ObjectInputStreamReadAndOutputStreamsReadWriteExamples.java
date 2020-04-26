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

        Animal michael = new Animal("Tiger Michael", 26, "fast creature", "staticValue michael", " transientValue michael");
        Animal gina = new Animal("Elephant Gina", 21, "slowCreature", "staticValue gina", "transientValue gina");
        List<Animal> written = new ArrayList<>();
        written.add(michael);
        written.add(gina);
        written.add(null);

        writeAnimals(animalsFile, written);
        getAnimals(animalsFile).forEach(System.out::println);
    }

    private static void writeAnimals(File file, List<Animal> animals) throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (Animal animal : animals) {
                os.writeObject(animal);
            }
        }
    }

    private static List<Animal> getAnimals(File file) throws IOException, ClassNotFoundException {
        List<Animal> animals = new ArrayList<>();

        try (ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            while (true) {
                Object read = is.readObject();
                // Attention here ! null object can also be written, so calling instanceof is basically filtering out the null objects
                if (read instanceof Animal) {
                    animals.add((Animal) read);
                }
            }
        } catch (EOFException eof) {
            System.out.println("EOF reached; exception caught");
        }

        return animals;
    }
}
