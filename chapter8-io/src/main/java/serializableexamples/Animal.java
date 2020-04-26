package serializableexamples;

import java.io.Serializable;

public class Animal extends Creature implements Serializable {

    private static final long serialVersionUID = 1L;

    private static String staticValue;
    private final transient String transientValue;
    private final String name;
    private final int age;

    public Animal(String name, int age, String type, String staticValue, String transientValue) {
        super(type);
        this.name = name;
        this.age = age;
        this.staticValue = staticValue;
        this.transientValue = transientValue;
    }

    @Override
    public String toString() {
        return String.format(
                "Animal [name=%s , age=%s, creatureType=%s, staticValue=%s, transientValue=%s]",
                name, age, getCreatureType(), staticValue, transientValue);
    }
}
