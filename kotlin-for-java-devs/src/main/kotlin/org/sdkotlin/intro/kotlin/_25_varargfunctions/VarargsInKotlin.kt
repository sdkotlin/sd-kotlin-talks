package org.sdkotlin.intro.kotlin._25_varargfunctions

fun main() {

	// Kotlin supports a variable number arguments (`vararg`) for a single
	// function parameter.

	fun zeroOrMoreThings(vararg things: String) {

		// The are passed as an array of the declared parameter type.

		println("things: ${things.contentDeepToString()}")
	}

	zeroOrMoreThings() // The passed array could be size 0.
	zeroOrMoreThings("one thing")
	zeroOrMoreThings("one thing", "two thing")

	// There can be only one vararg parameter, even if the types are different.

	//fun doesNotCompile(vararg someThings: String, vararg otherThings: Int) {}

	// Arrays can be passed as the vararg argument via the spread operator `*`.

	val arrayOfThings = arrayOf("one thing", "two thing")

	zeroOrMoreThings(*arrayOfThings)

	// A vararg parameter can be combined with other non-vararg parameters, in
	// which case it usually comes last in the parameter list.

	fun thingAndMaybeMore(oneThing: String, vararg moreThings: String) {
		println("oneThing: $oneThing")
		println("moreThings: ${moreThings.contentDeepToString()}")
	}

	thingAndMaybeMore("one thing")
	thingAndMaybeMore("one thing", "two thing")
	thingAndMaybeMore("one thing", "two thing", "three thing")

	// It doesn't have to come last if named arguments are used.

	fun varargAndInt(vararg things: String, howManyThings: Int) {
		println("things: ${things.contentDeepToString()}")
		println("howManyThings: $howManyThings")
	}

	varargAndInt(howManyThings = 0)
	varargAndInt("one thing", howManyThings = 1)
	varargAndInt("one thing", "two thing", howManyThings = 2)

	// Or if the last argument is a function.

	fun higherOrderVararg(vararg things: String, f: (String) -> Unit) {
		things.forEach { f(it) }
	}

	higherOrderVararg("one thing", "two thing", "red thing", "blue thing") {
		println(it)
	}
}
