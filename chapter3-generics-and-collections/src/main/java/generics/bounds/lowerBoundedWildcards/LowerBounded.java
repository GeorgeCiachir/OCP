package generics.bounds.lowerBoundedWildcards;

import java.util.ArrayList;
import java.util.List;

public class LowerBounded {


    public static void main(String[] args) {
        passListAsParam();

        List<? super Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("a dog"));

        List<? super Lion> lions = new ArrayList<>();
        lions.add(new Lion("a lion"));

        // We can't add a Creature or an Object because List<? super Animal> also means List<Animal> and list of any of the descendants of Animal
        // Any element of the list must be of type Animal
        // Other objects that inherit from Object or Creature might not be castable to an Animal
        List<? super Animal> animals = new ArrayList<>();
        animals.add(new Animal("Some other type of animal"));
        animals.add(new Dog("a dog"));
        animals.add(new Lion("a lion"));
//        animals.add(new Creature("a creature")); // does not compile
//        animals.add(new Object()); // does not compile
        System.out.println(animals);
    }

    private static void passListAsParam() {
        List<String> strings = new ArrayList<>();
        List<Object> objects = new ArrayList<>();

        addToList(strings, "on strings");
        addToList(objects, "on objects");

        System.out.println(strings);
        System.out.println(objects);
    }


    private static void addToList(List<? super String> elements, String string) {
        elements.add(string);
    }
}
