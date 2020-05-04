package serializableexamples.customizeserialization2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomSerialization2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File studentsFile = new File("students.txt");

        if (studentsFile.exists()) {
            studentsFile.delete();
        }
        studentsFile.createNewFile();

        Student michael = new Student("Michael", 26, new Address("Bucharest"));
        List<Student> students = new ArrayList<>();
        students.add(michael);

        writeStudents(studentsFile, students);
        getStudents(studentsFile);
    }

    private static void writeStudents(File file, List<Student> students) throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (Student animal : students) {
                os.writeObject(animal);
            }
        }
    }

    private static void getStudents(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            while (true) {
                Object read = is.readObject();
                System.out.println("Read: " + read);
            }
        } catch (EOFException eof) {
        }
    }
}
