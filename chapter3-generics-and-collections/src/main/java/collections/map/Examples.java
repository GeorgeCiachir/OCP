package collections.map;

import java.util.HashMap;
import java.util.Map;

public class Examples {

    public static void main(String[] args) {
        Map<String, String> simple = new HashMap<>();
        System.out.println(simple.put("1", "1"));
        System.out.println(simple.put("1", "2"));
        System.out.println(simple.remove("1"));

        Map<Integer, Integer> map = new HashMap<>(3);
    }
}
