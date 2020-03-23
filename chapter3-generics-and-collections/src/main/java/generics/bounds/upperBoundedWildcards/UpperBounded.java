package generics.bounds.upperBoundedWildcards;

import generics.bounds.unboundedWildcards.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UpperBounded {

	private void printTheListWorks1(List<Animal> objects) {
		for (Object o : objects) {
			System.out.println(o);
		}
	}

	private void printTheListWorks2(List<? extends Animal> objects) {
		for (Object o : objects) {
			System.out.println(o);
		}
	}


	private void printTheListWorks3(List<? extends Object> objects) {
		for (Object o : objects) {
			System.out.println(o);
		}
	}


	private long total1(List<? extends Number> numbers) {
		long total = 0;
		for (Number number : numbers) {
			total += number.longValue();
		}

		return total;
	}

	private long total2(List numbers) {
		long total = 0;
		for (Object number : numbers) {
			Number n = (Number) number;
			total += n.longValue();
		}

		return total;
	}

	public static void main(String[] args) {
		List<Animal> animals = new ArrayList<>();
		Animal a1 = new Animal("Gigel");
		Animal a2 = new Animal("Gogu");
		animals.add(a1);
		animals.add(a2);

		UpperBounded upperBounded = new UpperBounded();
		upperBounded.printTheListWorks1(animals);
		upperBounded.printTheListWorks2(animals);
		upperBounded.printTheListWorks3(animals);

		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		System.out.println(upperBounded.total1(numbers));
		System.out.println(upperBounded.total2(numbers));


		List<? extends Bird> logicallyImmutableBirds = new ArrayList<>();
		// The following lines do not compile because from Java's perspective, List<? extends Bird> could be List<Sparrow> and we can't add
		// a random Bird (that could actually be an instance of a Stork), to the Sparrow list
		// Basically, Java prevents a ClassCastException
		// At this point, the birds list has become logically immutable (elements cannot be added and there are no elements to remove),
		// unless we pass a collection of birds at creation, using the constructor (this would mean that we can now remove elements)
//		logicallyImmutableBirds.add(new Sparrow());
//		logicallyImmutableBirds.add(new Stork());
//		logicallyImmutableBirds.add(new Bird());




		List<Sparrow> sparrows = new ArrayList<>();
		sparrows.add(new Sparrow("a sparrow"));

		List<Stork> storks = new ArrayList<>();
		storks.add(new Stork("a stork"));

		// This is actually another bad idea. It is in fact exactly what Java tries to prevent by making the
		// list logically immutable
		List<Bird> randomBirds = new ArrayList<>();
		randomBirds.add(new Bird("random bird"));
		randomBirds.add(new Sparrow("random sparrow"));
		randomBirds.add(new Stork("random stork"));
		randomBirds.addAll(sparrows);
		randomBirds.addAll(storks);

		List<? extends Bird> birds = new ArrayList<>(randomBirds);
		System.out.println(birds);
		birds.remove(4);
		System.out.println(birds);

		System.out.println(birds.get(3).getClass());
		Stork failingStorkCast = (Stork) birds.get(3); //This throws ClassCastException

	}
}
