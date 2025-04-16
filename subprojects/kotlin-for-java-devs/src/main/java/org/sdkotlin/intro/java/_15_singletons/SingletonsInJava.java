package org.sdkotlin.intro.java._15_singletons;

// This is one of the ways to implement the singleton pattern in Java...
class JavaSingletonWithStaticFinalField {

	public static final JavaSingletonWithStaticFinalField
		INSTANCE = new JavaSingletonWithStaticFinalField();

	private JavaSingletonWithStaticFinalField() {
	}

	public void doIt() {
		System.out.println(
			"The most wonderful thing about tiggers is I'm the only one!"
		);
	}
}

// This is another way to implement the singleton pattern in Java...
class JavaSingletonWithSynchronizedMethod {

	private static JavaSingletonWithSynchronizedMethod instance;

	private JavaSingletonWithSynchronizedMethod() {
	}

	public static synchronized JavaSingletonWithSynchronizedMethod getInstance() {
		if (instance == null) {
			instance = new JavaSingletonWithSynchronizedMethod();
		}
		return instance;
	}

	public void doIt() {
		System.out.println(
			"The most wonderful thing about tiggers is I'm the only one!"
		);
	}
}

public class SingletonsInJava {

	public static void main(final String[] args) {

		final JavaSingletonWithStaticFinalField
			javaSingletonWithStaticFinalField;

		// Does not compile...
		//var singleton = new javaSingletonWithStaticFinalField();

		javaSingletonWithStaticFinalField =
			JavaSingletonWithStaticFinalField.INSTANCE;
		javaSingletonWithStaticFinalField.doIt();

		final JavaSingletonWithSynchronizedMethod
			javaSingletonWithSynchronizedMethod;

		// Does not compile...
		//var singleton = new JavaSingletonWithSynchronizedMethod();

		javaSingletonWithSynchronizedMethod =
			JavaSingletonWithSynchronizedMethod.getInstance();
		javaSingletonWithSynchronizedMethod.doIt();
	}
}
