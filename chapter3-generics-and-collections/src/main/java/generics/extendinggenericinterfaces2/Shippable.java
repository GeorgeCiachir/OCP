package generics.extendinggenericinterfaces2;

public interface Shippable<T extends Animal> {

	void ship(T t);
}
