package generics.bounds.unboundedWildcards;

import java.util.ArrayList;
import java.util.List;

public class Unbounded {

	private void printTheListWorks1(List<?> objects) {
		for (Object o : objects) {
			System.out.println(o);
		}
	}

	private <T> void printTheListWorks3(List<T> objects) {
		for (Object o : objects) {
			System.out.println(o);
		}
	}

	private void printTheListWorks2(List objects) {
		for (Object o : objects) {
			System.out.println(o);
		}
	}


	private void printTheListDoesNotCompile(List<Object> objects) {
		for (Object o : objects) {
			System.out.println(o);
		}
	}

	public static void main(String[] args) {
		List<Animal> animals = new ArrayList<>();
		Animal a1 = new Animal("Gigel");
		Animal a2 = new Animal("Gogu");
		animals.add(a1);
		animals.add(a2);

		Unbounded unbounded = new Unbounded();
		unbounded.printTheListWorks1(animals);
		unbounded.printTheListWorks2(animals);
		unbounded.printTheListWorks3(animals);
//		unbounded.printTheListDoesNotCompile(animals);
	}

}
