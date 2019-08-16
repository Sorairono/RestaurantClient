package singleton;

public class Singleton_Object {
	private static Singleton_Object instance;

	private Singleton_Object() {

	}

	public static Singleton_Object getInstance() {
		if (instance == null) {
			synchronized (Singleton_Object.class) {
				if (instance == null) {
					instance = new Singleton_Object();
				}
			}
		}
		return instance;
	}

}
