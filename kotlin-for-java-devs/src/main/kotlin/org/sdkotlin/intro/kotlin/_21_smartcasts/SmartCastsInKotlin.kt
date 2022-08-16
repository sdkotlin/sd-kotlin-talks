package org.sdkotlin.intro.kotlin._21_smartcasts

var variableProperty: Any = 1 // To be used later.

fun main() {

	// `Any` is the base type in Kotlin, similar to `Object` in Java.

	val thing: Any = "String"

	// Kotlin supports type checks with the `is` operator.
	// Combine with the boolean not operator, `!`, to negate the check.

	if (thing is String) {
		println("I'm a $thing!")
	} else if (thing !is Int) {
		println("I'm not an Int!")
	}

	// When a type is verified via a conditional, Kotlin automatically casts
	// the reference to that type.

	//thing.trim() // No `trim()` function on `Any`.

	if (thing is String) {
		println("The ${thing.trim()} API is available here!")
	}

	// This works for any provable control flow.

	if (thing !is String) return

	println("I'm a ${thing.trim()} from now on!")

	// Including with `when`.

	when (val otherThing: Any = "Other String") {
		is String -> println("otherThing: ${otherThing.trim()}")
		is Int -> println("otherThing + 1: ${otherThing.plus(1)}")
	}

	// Smart casts don't work for variable properties, as they could be changed
	// by another thread in between the check and the usage.

	if (variableProperty is Int) {
		// Some other thread could `variableProperty = "Foo"` at this point.
		//variableProperty.plus(1) // Does not compile
	}

	// See the Kotlin docs for other scenarios not eligible for smart casting:
	// https://kotlinlang.org/docs/reference/typecasts.html#smart-casts

	// Unsafe (i.e. runtime checked) casts can be done with the `as` operator.

	try {
		val integer = thing as Int
	} catch (e: ClassCastException) {
		println("${thing}s aren't Ints!")
	}

	// A preferred alternative is to use the safe cast operator, `as?`, which
	// returns null if the cast fails at runtime instead of throwing an
	// exception.

	val maybeAnInteger: Int? = thing as? Int

	println("maybeAnInteger: $maybeAnInteger")
}
