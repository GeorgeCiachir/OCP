package serializableexamples.customizeserialization2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Student implements Serializable {

//    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
//    private transient Address address;

    public Student(String name, int age, Address address) {
        this.name = name;
        this.age = age;
//        this.address = address;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("Serializing: " + this);
        out.writeObject(this);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

//        Student student = (Student) in.readObject();
//        this.name = student.name;
//        this.age = student.age;
//        System.out.println("Deserializing: " + this);
    }

    @Override
    public String toString() {
        return String.format("Student [name=%s , age=%s]", name, age);
    }
}
