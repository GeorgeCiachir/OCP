package generics.extendinggenericinterfaces;

public class Main {

	public static void main(String[] args) {
		ShippableCrateThatOnlyTakesARobot shippableCrateThatOnlyTakesARobot = new ShippableCrateThatOnlyTakesARobot();
		shippableCrateThatOnlyTakesARobot.ship(new Robot());

		ShippableCrateThatTakesAnything shippableCrateThatTakesAnything1 = new ShippableCrateThatTakesAnything(); //without using the param type
		shippableCrateThatTakesAnything1.ship(new Object()); //compiler warning -> unchecked call to ship(U)


		ShippableCrateThatTakesAnything<String> shippableCrateThatTakesAnything2 = new ShippableCrateThatTakesAnything<>();
		shippableCrateThatTakesAnything2.ship("an object of the correct type");

		ShippableCrateThatTakesSomethingThatExtendsARobot<Robot> shippableCrateThatTakesSomethingThatExtendsARobot = new ShippableCrateThatTakesSomethingThatExtendsARobot<>();
		shippableCrateThatTakesSomethingThatExtendsARobot.ship(new Robot());
		class ExtendedRobot extends Robot {
		}
		shippableCrateThatTakesSomethingThatExtendsARobot.ship(new ExtendedRobot());

		ShippableRawTypeTakesObject rawTypeTakesObject = new ShippableRawTypeTakesObject();
		rawTypeTakesObject.ship("");
	}
}

