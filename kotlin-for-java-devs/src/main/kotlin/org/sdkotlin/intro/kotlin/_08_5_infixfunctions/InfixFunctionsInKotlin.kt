package org.sdkotlin.intro.kotlin._08_5_infixfunctions

import kotlin.random.Random

// Kotlin supports defining member and extension functions that can be called
// using infix notation.

infix fun Int.plusOrMinus(other: Int) =
		if (Random.nextBoolean()) {
			this + other
		} else {
			this - other
		}

// Backticks can be used to escape nonstandard function names. This could be
// combined with infix functions to simulate new operators, but backticks would
// again be required at the call site.

infix fun Int.`+-`(other: Int) = this.plusOrMinus(other)

fun main() {

	val result = 1 plusOrMinus 2

	val alternateResult = 1 `+-` 2

	println("1 plusOrMinus 2: $result")
	println("1 `+-` 2: $alternateResult")
}
