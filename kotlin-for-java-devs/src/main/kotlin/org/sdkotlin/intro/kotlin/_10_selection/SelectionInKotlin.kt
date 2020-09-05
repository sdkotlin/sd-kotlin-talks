package org.sdkotlin.intro.kotlin._10_selection

import kotlin.random.Random.Default.nextBoolean as randomBoolean


fun main() {

	// If blocks in Kotlin are as in Java.

	if (true) {
		println(true)
	} else if (false) {
		println(false)
	} else {
		println("Huh?")
	}

	// Except they're expressions!
	// As such, no ternary operator.

	val randomBinaryInt = if (randomBoolean()) 1 else 0

	// Instead of `switch-case` we have the more powerful and elegant `when`.

	when (randomBinaryInt) {

		// The argument is "matched" against the conditions. First match wins.
		100 -> {
			println("x is 100")
		}

		// Multiple conditions can be separated by commas to form "or" logic.
		// Curly braces are optional for one-liners.
		0, 1 -> println("x is 0 or 1")

		// In addition to equality, 'in' and '!in' can be used to match against
		// ranges.
		in 2..10 -> println("x is between 2 and 10 (inclusive)")

		// 'is', and '!is' can be used to match against x's type.
		is Number -> println("x is a number")
	}

	// When can also be used as an expression.

	val mythRating = when (randomBoolean()) {
		true -> "confirmed!"
		false -> "busted!"
		// Used as an expression, `else` is required unless the compiler can
		// tell the conditions are exhaustive (e.g. both booleans, all
		// constants of an Enum, or all types of a sealed class).
	}

	println("The myth is $mythRating")

	// Testing for exhaustiveness is limited.

	val isItANumber = when (randomBinaryInt) {
		// All Ints are Numbers.
		is Number -> true
		// And yet the compiler still requires this `else`.
		else -> false
	}

	// There is a feature request to support sealed `when`s in the case where
	// it's only used for side effects:
	// https://youtrack.jetbrains.com/issue/KT-12380

	// In the meantime we can use a trivial utility function to force handling
	// the `when` as an expression.

	when (randomBoolean()) {
		false -> println("0")
		// Then if it's not exhaustive we'll get the desired compiler error.
		true -> println("1")
	}.sealed()
}

/**
 * A utility extension function intended to be used to trigger the handling of
 * `when` as an expression.
 */
fun Unit.sealed() = this
