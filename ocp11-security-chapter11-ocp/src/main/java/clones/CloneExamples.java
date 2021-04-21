package clones;

import java.util.ArrayList;

public class CloneExamples {

    public static void main(String[] args) {
        ArrayList<String> values = new ArrayList<>();
        values.add("first");
        values.add("second");

        ArrayList<String> clone = (ArrayList<String>) values.clone();

        System.out.println(clone == values);
    }
}
