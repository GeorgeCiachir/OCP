package serializableexamples.customizeserialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomSerialization {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File studentsFile = new File("students.txt");

        if (studentsFile.exists()) {
            studentsFile.delete();
        }
        studentsFile.createNewFile();

        Student michael = new Student("Michael", 26);
        List<Student> students = new ArrayList<>();
        students.add(michael);

        writeStudents(studentsFile, students);
        getStudents(studentsFile);
    }

    private static void writeStudents(File file, List<Student> animals) throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (Student animal : animals) {
                os.writeObject(animal);
            }
        }
    }

    private static void getStudents(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            while (true) {
                Object read = is.readObject();
                System.out.println("Apparently this is a: " + read.getClass());
                System.out.println(read);
            }
        } catch (EOFException eof) {
        }
    }
}
