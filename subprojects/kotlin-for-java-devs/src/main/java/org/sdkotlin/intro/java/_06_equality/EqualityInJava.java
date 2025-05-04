package org.sdkotlin.intro.java._06_equality;

import java.util.Objects;

public class EqualityInJava {

	public static void main(final String[] args) {

		// False.
		System.out.println("\"foo\"==new String(\"foo\"): "
			+ ("foo" == new String("foo")));

		// True only because of the string pool.
		System.out.println("\"foo\"==\"foo\": "
			+ ("foo" == "foo"));

		// True because of the integer pool.
		System.out.println("127==127: "
			+ (Integer.valueOf(Byte.MAX_VALUE) == Integer.valueOf(Byte.MAX_VALUE)));

		// Likely false, but may be true depending on VM and its settings for the integer pool.
		System.out.println("128==128: "
			+ (Integer.valueOf(Byte.MAX_VALUE + 1) == Integer.valueOf(Byte.MAX_VALUE + 1)));

		// Correct logical comparison. Null safe only when the left side is known to not be null.
		System.out.println("\"foo\".equals(new String(\"foo\")): "
			+ "foo".equals(new String("foo")));

		// Null safe.
		System.out.println("Objects.equals(null, null): "
			+ Objects.equals(null, null));
	}
}
