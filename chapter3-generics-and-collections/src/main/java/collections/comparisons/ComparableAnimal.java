package collections.comparisons;

public class ComparableAnimal implements Comparable {

    private final int id;
    private final String name;
    private final int age;

    public ComparableAnimal(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    //Because Comparable hasn't been parametrized Object is passed to the compareTo method
    //In order to have a ComparableAnimal as the method's argument, I have to parametrize the Comparable interface
    @Override
    public int compareTo(Object o) {
        //check for null, verify instanceof etc...
        ComparableAnimal other = (ComparableAnimal) o;
        return this.id - other.id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "id: " + this.id +
                ", name: " + this.name +
                ", age: " + this.age;
    }
}
