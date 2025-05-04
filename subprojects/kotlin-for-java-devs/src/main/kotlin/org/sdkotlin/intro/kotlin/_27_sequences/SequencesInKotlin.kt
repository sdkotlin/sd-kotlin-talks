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

	println(
		"$collectionBigEven found with $collectionFilters filter and " +
			"$collectionFinds find collection operations."
	)

	// Now let's try with a sequence instead.

	var sequenceFilters = 0
	var sequenceFinds = 0

	val sequenceBigEven = listOfRandomInts.asSequence()
		.filter { sequenceFilters++; it.isEven() }
		.find { sequenceFinds++; it > 50 }

	println(
		"$sequenceBigEven found with $sequenceFilters filter and " +
			"$sequenceFinds find sequence operations."
	)

	// If you're not starting with a collection, sequences can be created
	// directly via a factory function.

	val sequenceOfFlightlessBirds =
		sequenceOf("Penguin", "Ostrich", "Kiwi", "Emu")

	println("Flightless birds: ${sequenceOfFlightlessBirds.sorted().toList()}")

	// You can create a sequence from an existing function or lambda. The
	// sequence ends if and when the function returns `null`.

	val moreRandomInts = generateSequence {
		(0..10).random().takeIf { it != 0 }
	}

	println("Some random positive ints: ${moreRandomInts.toList()}")

	// You can seed the generator, and then the function gets the previous
	// value as an argument.

	val infiniteInts = generateSequence(0) { it + 1 }

	// This can result in an infinite loop, so be sure to use a limiting
	// operation on such a sequence.

	println("Will it ever end? ${infiniteInts.take(5).toList()}")

	// We haven't covered coroutines yet, but they're like lightweight threads.
	// There is a `sequence` generator/`yield()` suspending function for
	// creating non-blocking sequence generators.

	val fibonacciSequence = sequence {
		var terms = Pair(0, 1)
		while (true) {
			yield(terms.first)
			terms = Pair(terms.second, terms.first + terms.second)
		}
	}

	println("The Fibonacci sequence: ${fibonacciSequence.take(10).toList()}...")

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

	// Java 8 streams do have a couple advantages over Kotlin sequences:
	//     * Kotlin doesn't have parallel sequences as would be analogous to
	//       Java's parallel streams.
	//
	//       Parallel Flow processing is being considered for this use case:
	//       https://github.com/Kotlin/kotlinx.coroutines/issues/1147
	//
	//     * Java has primitive type streams to potentially minimize boxing.
	//
	// In practice, both parallel streams and primitive type streams have
	// caveats, and sequences have some mitigating options. These may reduce
	// or nullify any performance advantages streams might seem to possess over
	// Kotlin sequences. For highly performance sensitive code, neither streams
	// nor sequences may be an effective choice. The usual "always benchmark
	// your use case" warning applies.
}

fun Int.isEven() = this % 2 == 0
