package generics.genericmethods;

public class SomeClassWithGenericMethods {

	public <T> T getTheObject1(T t) {
		return t;
	}

	public static <T> T getTheObjectStatic1(T t) {
		return t;
	}

	public <T> Object getTheObject2(T t) {
		return new Crate<>(t);
	}

	public <T> Crate<T> getTheObject3(T t) {
		return new Crate<>(t);
	}

	public <T> String getTheObject4(T t) {
		return "";
	}

	public <T, R> Crate<R> getTheObject5(T t, R r) {
		return new Crate<>(r);
	}

	public static <T, R> Crate<R> getTheObjectStatic5(T t, R r) {
		return new Crate<>(r);
	}

	public <T, R, U, X, Y, Z> R getTheObject6(T t, R r) {
		return r;
	}

	public <T extends Animal> Animal doStuff(T t){
		return new Animal();
	}

	public <T extends Number> T doStuff2(Crate<T> crate) {
		return crate.getContent();
	}

    public <T extends Number> T doStuff3(Crate<? extends T> crate) {
        return crate.getContent();
    }

    <T> void foo(){}
}
