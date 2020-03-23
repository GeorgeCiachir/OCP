package generics.extendinggenericinterfaces;

public class ShippableCrateThatTakesSomethingThatExtendsARobot<U extends Robot> implements Shippable<U>{

	@Override
	public void ship(U u) {

	}
}
