package generics.typeerasure;

import java.util.ArrayList;
import java.util.List;

public class TypeErasure {

	public static void main(String[] args) {
		//The type works only for compile time
		List<String> strings = new ArrayList<>();
		strings.add("sss");
		System.out.println(strings);

		//At runtime, the type of the elements in the list is Object, so a cast like this one is possible,
		//although, a very bad idea
		List badIdea = strings;
		badIdea.add(new Object());
		badIdea.add(2);
		System.out.println(badIdea);

		//This type of cast is not possible because the types are different and the compiler prevents it
		//List<Integer> badIdea = strings;
	}
}
