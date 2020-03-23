package generics.bounds.lowerBoundedWildcards;

import java.util.ArrayList;
import java.util.List;

public class LowerBounded {

	private void addToList(List<? super String> elements, String string) {
		elements.add(string);
	}


	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		List<Object> objects = new ArrayList<>();

		LowerBounded lowerBounded = new LowerBounded();
		lowerBounded.addToList(strings, "on strings");
		lowerBounded.addToList(objects, "on objects");

		System.out.println(strings);
		System.out.println(objects);

		System.out.println("*******************************");

		//ok
		List<? super Dog> dogs = new ArrayList<>();
		Dog dog = new Dog("a dog");
		dogs.add(dog);

		//ok
		List<? super Lion> lions = new ArrayList<>();
		Lion lion = new Lion("a lion");
		lions.add(lion);


		//nok => see explanations on the UpperBounded example
//		List<? extends Animal> animals = new ArrayList<>();
//		animals.add(dog);
//		animals.add(lion);

		System.out.println("*******************************");


		System.out.println("******************************* This is really tricky ******************************* ");

		// we have Dog and Lion extending Animal and Animal extends Creature
		// This works fine because List<? super Animal> could mean List<Animal> or List<Creature> or List<Object>
		// In all cases Animal or creature can be substituted with any type/instance of Animal

		// Of course, we could not write animals.add(new Creature()) or
		// animals.add(new Object()) or  because List<? super Animal> also means List<Animal>
		// and we couldn't add a Creature or an Object to the animals list
		List<? super Animal> animals = new ArrayList<>();
		Animal randomAnimal = new Animal("Some other type of animal");
		animals.add(randomAnimal);
		animals.add(dog);
		animals.add(lion);
		System.out.println(animals);
	}
}
