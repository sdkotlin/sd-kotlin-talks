package org.sdkotlin.intro.kotlin._24_arrays

// Kotlin supports arrays, with some differences compared to Java.

@Suppress("UnusedVariable", "unused")
fun main() {

	// All arrays are instances of kotlin.Array<T>.

	println(Array<Int>::class)

	// There are no array literals in Kotlin. Factory functions from the
	// standard library are used instead.

	val arrayOfInts = arrayOf(1, 2, 3)

	// Arrays in Kotlin are fixed size and mutable, as in Java.

	// Unlike Java arrays, they have `get(Int)` and `set(Int, T)` functions,
	// which are operator functions for the index operator `[n]`.

	@Suppress("ReplaceGetOrSet")
	println("arrayOfInts.get(0): ${arrayOfInts.get(0)}")
	println("arrayOfInts[0]: ${arrayOfInts[0]}")

	@Suppress("ReplaceGetOrSet")
	arrayOfInts.set(0, 100)
	arrayOfInts[1] = 200
	arrayOfInts[1 + 1] = 300

	// Misuse results in the usual exception.

	try {
		@Suppress("KotlinConstantConditions")
		arrayOfInts[-1]
	} catch (e: ArrayIndexOutOfBoundsException) {
		println(e)
	}

	// You can get the size of an array to help avoid that.

	println("arrayOfInts.size: ${arrayOfInts.size}")

	// As an alternative to the factory function, there is a constructor that
	// takes a size and a value factory function.

	val numerals = Array(size = 10) { index -> index.toString() }

	// The index can be ignored if not needed.

	val randomInts = Array(size = 10) { (1..100).random() }

	// There's a factory function for the empty array, which requires the type
	// prameter since none can be inferred.

	val noInts = emptyArray<Int>()

	val noStrings = emptyArray<String>()

	// We can also get a typed array of nulls of given size.

	val nullInts = arrayOfNulls<Int>(size = 10)

	// For multidimensional arrays just nest the factory invocations.

	val twoDInts = arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6))

	// They can be asymmetrical as in Java.

	val forgotV8 = arrayOf(arrayOf(1, 2), arrayOf(1, 2, 3))

	// You can print arrays with a handy extension function.

	println("arrayOfInts: ${arrayOfInts.contentDeepToString()}")

	// There's also a `contentToString()`, but it's deprecated in 1.4,
	// presumably because it doesn't handle multidimensional arrays.

	println("twoDInts: ${twoDInts.contentDeepToString()}")

	// You can compare arrays for equality with another extension function.

	println(
		"arrayOfInts == arrayOfInts: ${
			arrayOfInts.contentDeepEquals(
				arrayOfInts
			)
		}"
	)

	println("arrayOfInts == twoDInts: ${arrayOfInts.contentDeepEquals(twoDInts)}")

	// While we're at it, there's a deep hash code function too.

	println("forgotV8.contentDeepHashCode: ${forgotV8.contentDeepHashCode()}")

	// The factory functions and type inference work, or don't, as one would
	// expect.

	val arrayOfAnys: Array<Any> = arrayOf(1, 2L, 3.0)

	val arrayOfNullableInts: Array<Int?> = arrayOf(1, null, 3)

	// Explicit type declaration can be used to guard against any funny
	// business.

	//val nope = arrayOf<String>("String", 1) // Does not compile.

	//val alsoNope: Array<String> = arrayOf("String", 1) // Does not compile.

	// For interop with Java, or to avoid the boxing overhead, there are
	// factory functions for arrays of primitive types.

	val intArray: IntArray = intArrayOf(1, 2, 3)
	val booleanArray: BooleanArray = booleanArrayOf(true, false, true)

	// There are multiple options for iterating over arrays.

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
