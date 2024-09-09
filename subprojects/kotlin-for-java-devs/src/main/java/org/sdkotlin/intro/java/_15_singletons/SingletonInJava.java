package org.sdkotlin.intro.java._15_singletons;

class Singleton {

	// This is one of the ways to implement the singleton pattern in Java...

	public static final Singleton INSTANCE = new Singleton();

	private Singleton() {
	}

	public void doIt() {
		System.out.println("The most wonderful thing about tiggers is I'm the only one!");
	}
}

public class SingletonInJava {

	public static void main(final String[] args) {

		final Singleton singleton;

		// singleton = new Singleton(); // Does not compile.
		singleton = Singleton.INSTANCE;
		singleton.doIt();
	}
}
