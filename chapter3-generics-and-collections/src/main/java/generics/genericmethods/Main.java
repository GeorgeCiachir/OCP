package generics.genericmethods;

public class Main {

	public static void main(String[] args) {
		String theObjectStatic1 = SomeClassWithGenericMethods.<String>getTheObjectStatic1("some string");
		System.out.println(theObjectStatic1);

		Crate<Integer> theObjectStatic5 = SomeClassWithGenericMethods.<String, Integer>getTheObjectStatic5("", 12);
		System.out.println(theObjectStatic5.getContent());
	}
}
