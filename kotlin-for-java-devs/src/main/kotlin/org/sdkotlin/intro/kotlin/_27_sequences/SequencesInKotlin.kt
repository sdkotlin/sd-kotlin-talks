package org.sdkotlin.intro.kotlin._27_sequences

// Kotlin has sequences for lazy collection stream operations, very similar to
// Java 8 streams.

fun main() {

	// Let's take a big list of random numbers between 1-100.

	val listOfInts = List(1000) { (1..100).random() }

	// Say we want to find the first even number greater than 50.

	val bigEven = listOfInts.asSequence()
			.filter { println("Filtering $it"); it.isEven() }
			.find { println("Finding $it"); it > 50 }
			?.toString() ?: "Not Found"

	println("First even number over 50: $bigEven")
}

fun Int.isEven() = this % 2 == 0
