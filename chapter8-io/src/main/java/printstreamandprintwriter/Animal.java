package printstreamandprintwriter;

public class Animal {

    private final String name;
    private final int age;
    private final char type;

    public Animal(String name, int age, char type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Animal [name=%s , age=%s, type=%s]", name, age, type);
    }
}
