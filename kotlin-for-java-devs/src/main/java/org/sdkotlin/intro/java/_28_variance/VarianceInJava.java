package org.sdkotlin.intro.java._28_variance;

import java.util.ArrayList;
import java.util.List;

public class VarianceInJava {

	public static void main(final String[] args) {

		// Arrays are covariant in Java

		final String[] strings = new String[]{"Hello", "World"};

		final Object[] objects = strings;

		// However, as they are both producers and consumers, that means
		// type-safety is not ensured by the compiler.

		try {
			objects[1] = 1;
		} catch (final ArrayStoreException e) {
			System.out.println(e);
		}

		// In Java collections are invariant.

		final List<String> listOfStrings = new ArrayList<>();

		//final List<Object> nope = listOfStrings; // Does not compile.

		// Unless a usage-site wildcard declaration is used.

		final List<? extends Object> listOfThings = listOfStrings;

		// This is like List<out T> in Kotlin. It makes the list covariant
		// producer.

		// We can read objects from it.

		final Object stringyThing = listOfThings.get(0);

		// But the compiler stops us from writing to it.

		//listOfThings.add("Testing"); // Does not compile.

		// We can declare a contravariant consumer as well.

		final List<? super String> listOfStringyThings = new ArrayList<>();

		// We can write to it.

		listOfStringyThings.add("Testing");

		// But not read from it.

		//final String alsoNope = listOfStringyThings.get(0); // Does not compile.

		// Not Strings at least.

		final Object unknownThing = listOfStringyThings.get(0);
	}
}
