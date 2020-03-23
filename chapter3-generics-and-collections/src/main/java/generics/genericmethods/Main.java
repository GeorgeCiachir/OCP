package generics.genericmethods;

public class Main {

	public static void main(String[] args) {
		String theObjectStatic1 = SomeClassWithGenericObjects.<String>getTheObjectStatic1("some string");
		System.out.println(theObjectStatic1);

		Crate<Integer> theObjectStatic5 = SomeClassWithGenericObjects.<String, Integer>getTheObjectStatic5("", 12);
		System.out.println(theObjectStatic5.getContent());
	}
}