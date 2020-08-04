package org.sdkotlin.intro.java._16_static;

import org.sdkotlin.intro.kotlin._16_static.StaticInKotlin;

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
