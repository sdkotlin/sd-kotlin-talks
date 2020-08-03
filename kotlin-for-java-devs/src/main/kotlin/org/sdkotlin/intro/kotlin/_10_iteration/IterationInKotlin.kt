package org.sdkotlin.intro.kotlin._10_iteration

fun main() {

	// Kotlin lacks the C-style for loop, preferring the for each loop with ranges.

	for (i in 1..5) {
		println("Counting... $i")
	}

	// We can also use the functional approach.

	(1..5).forEach { i -> println("Again... $i") }

	// Ranges are inclusive of the end. Use 'until' for exclusive.

	(1 until 5).forEach { i -> println("And again... $i") }

	// The 'repeat' higher-order function is another option.

	repeat(5) { i -> println("Yet again... $i") }

	// You can "i--".

	for (i in 5 downTo 1) {
		println("Down... $i")
	}

	// You can skip a few.

	for (i in 1..5 step 2) {
		println("Odds... #$i")
	}

	// You can define ranges for characters and then get them as a list.

	val alphabet = ('a'..'z').toList()

	// You can for-each that list.

	for (l in alphabet) {
		println("Letters... $l")
	}

	// You can enumerate the indices of a list and then access the list
	// elements by index.

	for (i in alphabet.indices) {
		println("Letter ${i + 1} of the alphabet is ${alphabet[i]}")
	}

	// You can save a range to a variable.

	val oneToFive = 1..5

	// Ranges have other useful properties.

	var i = oneToFive.first

	while (i in oneToFive) {

		// Unlike 'for' loops, the index in 'while' loops is mutable.

		println("Counting between ${oneToFive.first} and ${oneToFive.last}: ${i++}")
	}
}
