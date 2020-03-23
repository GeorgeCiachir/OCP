package generics.extendinggenericinterfaces2;

public class ShippableCrateTakesSomethingThatExtendsAnimal<U extends Animal> implements Shippable<U> {


	@Override
	public void ship(Animal animal) {

	}
}
