package generics.typeerasure;

import java.util.ArrayList;
import java.util.List;

public class TypeErasure {

    public static void main(String[] args) {
//        addingWrongElementsInAList();
//        instanceOfOnGenericLists();
        passAWrongListToAGenericMethod();
    }

    private static void passAWrongListToAGenericMethod() {
        List list = new ArrayList();
        list.add("a string");
        list.add(2);
        printElements(list);

    }

    private static void printElements(List<String> elements) {
        for (String e : elements) {
            System.out.println(e);
        }
    }


    private static void instanceOfOnGenericLists() {
        List<String> elements = new ArrayList<>();
        System.out.println(elements instanceof List); //true
        // System.out.println(elements instanceof List<String>); //does not compile due to type erasure. It may also be List<Integer>

    }

    private static void addingWrongElementsInAList() {
        //The type works only for compile time
        List<String> strings = new ArrayList<>();
        strings.add("a string element");
        System.out.println(strings);

        //At runtime, the type of the elements in the list is Object, so a cast like this one is possible,
        //although, a very bad idea
        List badIdea = strings;
        badIdea.add(new Object());
        badIdea.add(2);
        System.out.println(badIdea);
    }

}
