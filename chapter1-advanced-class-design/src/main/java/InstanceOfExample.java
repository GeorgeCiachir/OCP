public class InstanceOfExample {

	public static void main(String[] args) {
		firstExample();
	}

	private static void firstExample() {
		HeavyAnimal heavyAnimal = new Hippo();
		System.out.println(heavyAnimal instanceof HeavyAnimal); //true
		System.out.println(heavyAnimal instanceof Hippo); //true

		Hippo hippo = new Hippo();
		System.out.println(hippo instanceof HeavyAnimal); //true
		System.out.println(hippo instanceof Hippo); //true
		System.out.println(hippo instanceof Object); //true

		HeavyAnimal animal = null;
		System.out.println(animal instanceof Hippo); //false

		Hippo another = new Hippo();
//		System.out.println(another instanceof Elephant); // nu compileaza deoarece Java stie ca Hippo nu poate fi instanta de Elephant (pagina 8 in carte)
		System.out.println((HeavyAnimal)another instanceof Elephant); // false


	}

}

class HeavyAnimal {
}

class Hippo extends HeavyAnimal {
}

class Elephant extends HeavyAnimal {
}