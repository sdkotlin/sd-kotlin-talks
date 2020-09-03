package org.sdkotlin.intro.java._24_arrays;

public class ArraysInJava {

	public static void main(final String[] args) {

		// Java has array literals.

		final int[] ints = {1, 2, 3};

		// Array literals can be used with local variable type inference if you
		// also include the initializer.

		//var nope = {1, 2, 3}; // Does not compile.

		final var inferredInts = new int[]{1, 2, 3};

		// Array literals can be used for multidimensional arrays.

		final var twoDInferredInts = new int[][]{{1, 2, 3}, {4, 5, 6}};
	}
}
