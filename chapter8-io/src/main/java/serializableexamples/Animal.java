package serializableexamples;

import java.io.Serializable;

public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    private static String staticValue;
    private transient String transientValue;

    private String name;
    private int age;
    private char type;

    public Animal(String name, int age, char type, String staticValue, String transientValue) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.staticValue = staticValue;
        this.transientValue = transientValue;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Animal [name=%s , age=%s, type=%s, staticValue=%s, transientValue=%s]", name, age, type, staticValue, transientValue);
    }
}
