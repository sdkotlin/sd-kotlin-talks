package org.sdkotlin.intro.kotlin._27_sequences

// Kotlin has sequences for lazy collection stream operations, very similar to
// Java 8 streams.

fun main() {

	// Let's take a big list of random numbers between 1-100.

	val listOfInts = List(1000) { (1..100).random() }

	// Say we want to find the first even number greater than 50.

	// Let's try this using collection operations first.

	// We'll keep track of the number of operations.
	var collectionFilters = 0
	var collectionFinds = 0

	val collectionBigEven = listOfInts
			.filter { collectionFilters++; it.isEven() }
			.find { collectionFinds++; it > 50 }

	println("$collectionBigEven found with $collectionFilters filter and " +
			"$collectionFinds find collection operations.")

	// Now let's try with a sequence instead.

	var sequenceFilters = 0
	var sequenceFinds = 0

	val sequenceBigEven = listOfInts.asSequence()
			.filter { sequenceFilters++; it.isEven() }
			.find { sequenceFinds++; it > 50 }

	println("$sequenceBigEven found with $sequenceFilters filter and " +
			"$sequenceFinds find sequence operations.")
}

fun Int.isEven() = this % 2 == 0
