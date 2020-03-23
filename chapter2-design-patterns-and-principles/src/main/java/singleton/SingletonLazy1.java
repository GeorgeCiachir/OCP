package singleton;

public class SingletonLazy1 {

	private static volatile SingletonLazy1 instance;

	private SingletonLazy1() {
	}

	/*
	 * Sync happens only once, at the object creation
	 */
	public static SingletonLazy1 getInstance() {
		if (instance == null) {
			synchronized (SingletonLazy1.class) {
				if (instance == null) {
					instance = new SingletonLazy1();
				}
			}
		}

		return instance;
	}
}
