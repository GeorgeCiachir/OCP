public class OverrideExample {

	public static void main(String[] args) {
		Cat cat = new Lion();
		cat.makeSound();

		Lion lion = new Lion();
		lion.makeSound();

		BiggerLion biggerLion = new BiggerLion();
		biggerLion.makeSound();
	}
}


class Cat {

	static void makeSound() {
		System.out.println("miau");
	}
}

class Lion extends Cat {

	static void makeSound() {
		System.out.println("Rarrrrr");
	}
}

class BiggerLion extends Lion {

}