package org.sdkotlin.intro.kotlin._XX_selection

fun main() {

	// If blocks in Kotlin are as in Java
	if (true) {
		println(true)
	} else if (false) {
		println(false)
	} else {
		println("Huh?")
	}

	// Except they're expressions!
	// As such, no ternary operator
	val x = if (true) 1 else 0

	// Instead of `switch-case` we have the more powerful and elegant `when`
	when (x) {
		0, 1 -> println("x is 0 or 1")
		in 2..10 -> println("x between 2 and 10 (inclusive)")
		is Number -> println("x is a number")
		else -> { // Note the block
			println("x is neither 1 nor 2")
		}
	}
}
