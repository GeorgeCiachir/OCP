package generics.typeerasure;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) {

		Function<Integer, Integer> goodFunction = (a) -> 3;
		System.out.println(aVeryBadMethod(goodFunction, 12));
		System.out.println(aOkMethod(goodFunction, 12));

		// Just like declaring a method with a non parameterized function,
		// passing a non parameterized function to a good method can cause ClassCastException
		// This time, the returned value of the function (a String) cannot be cast to an Integer
		// The compiler also displays a warning
		Function badFunction = (a) -> "String";
		System.out.println(aOkMethod(badFunction, 12));

		List numbers = new ArrayList();
		numbers.add(5);
		Integer result = (Integer) numbers.get(0); //the get method returns an Object, not an Integer
	}


	/*
	 * Very bad idea to have a non parameterized Function as an argument, because of type erasure =>
	 * => the received function can be cast to the non parameterized argument
	 *
	 * If the received function is parameterized and I call inside the method with a wrong argument, a
	 * ClassCastException will be thrown
	 *
	 * The compiler also displays a warning
	 */
	private static Object aVeryBadMethod(Function f, Integer a) {
		return f.apply("some string");
	}

	private static Integer aOkMethod(Function<Integer, Integer> f, Integer a) {
		return f.apply(a);
	}
}