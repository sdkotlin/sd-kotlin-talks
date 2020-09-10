package org.sdkotlin.intro.kotlin._27_sequences

// Kotlin has sequences for lazy collection stream operations, very similar to
// Java 8 streams.

fun main() {

	// Let's take a big list of random numbers between 1-100.

	val listOfRandomInts = List(1000) { (1..100).random() }

	// Say we want to find the first even number greater than 50.

	// Let's try this using collection operations first.

	// We'll keep track of the number of operations.
	var collectionFilters = 0
	var collectionFinds = 0

	val collectionBigEven = listOfRandomInts
			.filter { collectionFilters++; it.isEven() }
			.find { collectionFinds++; it > 50 }

	println("$collectionBigEven found with $collectionFilters filter and " +
			"$collectionFinds find collection operations.")

	// Now let's try with a sequence instead.

	var sequenceFilters = 0
	var sequenceFinds = 0

	val sequenceBigEven = listOfRandomInts.asSequence()
			.filter { sequenceFilters++; it.isEven() }
			.find { sequenceFinds++; it > 50 }

	println("$sequenceBigEven found with $sequenceFilters filter and " +
			"$sequenceFinds find sequence operations.")

	// There are some benefits to using the Kotlin sequence APIs over Java 8
	// streams:
	//     * Additional null safety
	//     * Leaner, more Kotlin-like API
	//     * Multiplatform
	//     * Sequences can be reused, even the intermediaries

	val streamBigEven = listOfRandomInts.stream()
			.filter { it.isEven() }
			// Additional intermediate operation instead of Kotlin's terminal
			// `.find(Predicate)`
			.filter { it > 100 }
			.findFirst() // Returns an `Optional`
			// The compiler doesn't force us to default the value with
			// `orElse()` instead of `get()`
			.orElse(null)

	// And even when using `Optional.orElse()`, we still end up with a platform
	// type.

	try {
		val nope = streamBigEven + 1
	} catch (e: NullPointerException) {
		println("streamBigEven + 1: KABLAMO!")
	}

	val sequenceOfRandomInts = listOfRandomInts.asSequence()

	// We can reuse a sequence, even after calling a terminal operation on it.

	println("sequenceOfRandomInts.average() ${sequenceOfRandomInts.average()}")
	println("sequenceOfRandomInts.sum() ${sequenceOfRandomInts.sum()}")

	// We can reuse intermediary sequences.

	val sequenceOfRandomEvens = sequenceOfRandomInts.filter { it.isEven() }

	val sequenceOfRandomOdds = sequenceOfRandomInts.filter { !it.isEven() }

	println("sequenceOfRandomEvens.average() ${sequenceOfRandomEvens.average()}")
	println("sequenceOfRandomOdds.average() ${sequenceOfRandomOdds.average()}")

	// Kotlin doesn't have parallel sequences as would be analogous to Java's
	// parallel streams.
	//
	// Parallel Flow processing is being considered for this use case:
	// https://github.com/Kotlin/kotlinx.coroutines/issues/1147
}

fun Int.isEven() = this % 2 == 0
