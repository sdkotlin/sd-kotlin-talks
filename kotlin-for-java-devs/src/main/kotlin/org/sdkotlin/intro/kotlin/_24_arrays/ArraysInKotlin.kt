package org.sdkotlin.intro.kotlin._24_arrays

// Kotlin supports arrays, with some differences compared to Java.

fun main() {

	// All arrays are instances of kotlin.Array<T>.

	println(Array<Int>::class)

	// There are no array literals in Kotlin. Factory functions from the
	// standard library are used instead.

	val arrayOfInts = arrayOf(1, 2, 3)

	// Alternatively, there is a constructor that takes a size and a factory
	// function.

	val numerals = Array(size = 10) { index -> index.toString() }

	// The index can be ignored if not needed.

	val randomInts = Array(size = 10) { (1..100).random() }

	// There's a factory function for the empty array.

	val noInts = emptyArray<Int>()

	val noStrings = emptyArray<String>()

	// And for arrays of Nulls.

	val nullInts = arrayOfNulls<Int>(size = 10)

	// For multidimensional arrays just nest the factory invocations.

	val twoDInts = arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6))

	// They can be asymmetrical as in Java.

	val forgotV8 = arrayOf(arrayOf(1, 2), arrayOf(1, 2, 3))

	// You can get the size of an array.

	println("arrayOfInts.size: ${arrayOfInts.size}")

	// You can print arrays with a handy extension function.

	println("arrayOfInts: ${arrayOfInts.contentDeepToString()}")

	// There's also a `contentToString()`, but it's deprecated in 1.4,
	// presumably because it doesn't handle multidimensional arrays.

	println("twoDInts: ${twoDInts.contentDeepToString()}")

	// You can compare arrays for equality with another extension function.

	println("arrayOfInts == arrayOfInts: ${
		arrayOfInts.contentDeepEquals(arrayOfInts)
	}")

	println("arrayOfInts == twoDInts: ${
		arrayOfInts.contentDeepEquals(twoDInts)
	}")

	// While we're at it, there's a deep hash code function too.

	println("forgotV8.contentDeepHashCode: ${forgotV8.contentDeepHashCode()}")

	// The factory functions and type inference work, or don't, as one would
	// expect.

	val arrayOfAnys = arrayOf(1, 2L, 3.0)

	// Explicit type declaration can be used to guard against any funny
	// business.

	//val nope = arrayOf<String>("String", 1) // Does not compile.

	//val alsoNope: Array<String> = arrayOf("String", 1) // Does not compile.

	// Arrays have `get(Int)` and `set(Int, T)` functions, which are operator
	// overloaded with the index operator `[]`.

	arrayOfInts.set(0, 100)
	arrayOfInts[1] = 200
	arrayOfInts[1 + 1] = 300

	println("arrayOfInts.get(0): ${arrayOfInts.get(0)}")
	println("arrayOfInts[0]: ${arrayOfInts[0]}")

	// Misuse garners the usual exception.

	try {
		arrayOfInts[-1]
	} catch (e: ArrayIndexOutOfBoundsException) {
		e.printStackTrace()
	}

	// For interop with Java, or for performance sensitive code, there are
	// factory functions for arrays of underlying primitive types.

	val intArray = intArrayOf(1, 2, 3)
	val booleanArray = booleanArrayOf(true, false, true)

	// There multiple options for iterating over arrays.

	for (value in arrayOfInts) println("arrayOfInts: $value")

	arrayOfInts.forEach { println("arrayOfInts: $it") }

	for (index in arrayOfAnys.indices) {
		println("arrayOfInts[$index]: ${arrayOfInts[index]}")
	}

	arrayOfInts.forEachIndexed { index, value ->
		println("arrayOfInts[$index]: $value")
	}

	val iterator = arrayOfInts.iterator()

	while (iterator.hasNext()) println("arrayOfInts: ${iterator.next()}")

	// We can in-place reverse and sort arrays.

	arrayOfInts.reverse()

	println("arrayOfInts reversed: ${arrayOfInts.contentDeepToString()}")

	arrayOfInts.sort()

	println("arrayOfInts sorted: ${arrayOfInts.contentDeepToString()}")

	// Or out of place if we want to avoid side effects.

	val reversedArrayOfInts = arrayOfInts.reversedArray()

	val sortedArrayOfInts = reversedArrayOfInts.sortedArray()

	// We can search arrays.

	if (sortedArrayOfInts.binarySearch(element = 200) >= 0) {
		println("Found it!")
	}

	// And copy, fill, map, group, zip, slice, dice, and all the things...

	//arrayOfInts.
}
