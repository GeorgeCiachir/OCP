package generics.extendinggenericinterfaces;

public class ShippableCrateThatTakesAnything<U> implements Shippable<U> {

	@Override
	public void ship(U u) {
	}
}
