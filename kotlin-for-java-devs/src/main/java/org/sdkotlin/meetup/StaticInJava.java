package org.sdkotlin.meetup;

public class StaticInJava {

	public static final String CONSTANT = "Death & Taxes";

	public static void saySomething() {
		System.out.println(CONSTANT);
	}

	public static void main(final String[] args) {
		StaticInJava.saySomething();
		StaticInKotlin.saySomething();

		new StaticInJava().saySomething();
		new StaticInKotlin().saySomething();
	}
}
