package collections.list;

import java.util.ArrayList;
import java.util.List;

public class ListExamples {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("asd");
        list.add(1);

        // Does not compile
        // Because an unparametrized list is used, the compiler expets to loop through Objects,
        // not through Strings
//        for (String s: list) {
//            System.out.println(s);
//        }
    }
}
