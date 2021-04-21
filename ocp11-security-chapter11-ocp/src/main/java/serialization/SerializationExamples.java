package serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationExamples {

    public static void main(String[] args) throws Exception {
        SerializationExamples client = new SerializationExamples();
        client.runExamples();
    }

    private void runExamples() throws Exception {
        first();
    }

    private void first() throws Exception {
        Employee alex = new Employee("Alex", 22, 1000, "1");
        write(alex);
        Employee read = read(Employee.class);
        System.out.println("Read: " + read);

        Employee alexUpdated = new Employee("Alex", 22, 2000, "1");
        write(alexUpdated);
        Employee readAgain = read(Employee.class);
        System.out.println("Read again: " + readAgain);

        System.out.println("Pool: " + EmployeePool.getEmployees());
    }

    private void write(Object o) throws Exception {
        try (FileOutputStream os = new FileOutputStream("employees");
             BufferedOutputStream bis = new BufferedOutputStream(os);
             ObjectOutputStream oos = new ObjectOutputStream(bis)) {
            oos.writeObject(o);
        }
    }

    private <T> T read(Class<T> clazz) throws Exception {
        try (FileInputStream fis = new FileInputStream("employees");
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            Object read = ois.readObject();
            if (read != null && clazz.isAssignableFrom(read.getClass())) {
                return clazz.cast(read);
            }

        } catch (EOFException e) {
            System.out.println("finished reading");
        }
        return null;
    }
}
