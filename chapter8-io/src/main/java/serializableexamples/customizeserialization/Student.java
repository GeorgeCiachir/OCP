package serializableexamples.customizeserialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        String thisShouldBeTheObject = "thisShouldBeTheObject";
        out.writeObject(thisShouldBeTheObject);
        System.out.println("Serializing: " + thisShouldBeTheObject.getClass());
        System.out.println("This: " + this);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        Object readValue = in.readObject();
        System.out.println("Deserializing: " + readValue.getClass());
        System.out.println(readValue);
    }

    @Override
    public String toString() {
        return String.format("Student [name=%s , age=%s]", name, age);
    }
}
