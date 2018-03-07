package org.sdkotlin.meetup;

class Singleton {

	public static Singleton INSTANCE = new Singleton();

	private Singleton() {
	}

	public void doIt() {
		System.out.println("The most wonderful thing about tiggers is I'm the only one!");
	}
}

public class SingletonInJava {
	public static void main(String[] args) {

		Singleton singleton;

		// singleton = new Singleton(); // Does not compile.
		singleton = Singleton.INSTANCE;
		singleton.doIt();
	}
}
