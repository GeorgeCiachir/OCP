package singleton;

public class SingletonLazy2 {

	private static SingletonLazy2 instance;

	private SingletonLazy2() {
	}

	/*
	 * Not ok because sync is performed each time the method is called
	 * Only the first sync is really required
	 */
	public static synchronized SingletonLazy2 getInstance() {
		if (instance == null) {
			instance = new SingletonLazy2();
		}

		return instance;
	}
}

