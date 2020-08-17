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

// Note that the function identifier naming constraints in Kotlin prevent us
// from defining new symbolic operators.

fun main() {

	val result = 1 plusOrMinus 2

	println("1 ± 2: $result")
}
