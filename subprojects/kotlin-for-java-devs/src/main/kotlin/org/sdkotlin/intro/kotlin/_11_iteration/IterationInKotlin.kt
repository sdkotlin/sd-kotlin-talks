package org.sdkotlin.intro.kotlin._11_iteration

import kotlin.random.Random.Default.nextBoolean as randomBoolean

fun main() {

	// Kotlin lacks the C-style for loop, preferring the for-each loop with
	// ranges.

	for (i in 1..5) {
		println("Counting... $i")
	}

	//for (var i = 0; i <= 10; i++) {
	//
	//}

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

	// You can enumerate the indices of a list, and then access the list
	// elements by index.

	for (i in alphabet.indices) {
		println("Letter ${i + 1} of the alphabet is ${alphabet[i]}")
	}

	// You can also enumerate the destructured index and value from a list.

	for ((index, character) in alphabet.withIndex()) {
		println("Letter $index of the alphabet is $character")
	}

	// As of Kotlin 2.2, 'break' and 'continue' work from inside a lambda
	// passed to an inline function such as 'forEach'. A label targets a
	// specific enclosing loop.

	grid@ for (row in 1..3) {
		(1..3).forEach { column ->
			// Abandons the rest of the row, not just this element.
			if (column > row) continue@grid
			// Leaves the outer loop entirely.
			if (row * column > 4) break@grid
			println("Cell $row,$column")
		}
	}

	// Inlining puts the lambda body inside the loop, so there is a loop to
	// jump to. A crossinline or non-inline lambda has no such loop.

	// Java has no equivalent: a lambda cannot break or continue an enclosing
	// loop. The workaround is a plain nested loop or a boolean flag.

	// Kotlin also has while and do-while loops.

	var keepGoing = true

	while (keepGoing) {
		println("Taking my chances!")
		keepGoing = randomBoolean()
	}

	do {
		println("Will it ever end?")
	} while (randomBoolean())
}
