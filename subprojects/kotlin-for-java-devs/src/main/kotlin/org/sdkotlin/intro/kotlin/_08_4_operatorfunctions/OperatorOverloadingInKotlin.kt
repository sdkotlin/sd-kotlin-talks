package org.sdkotlin.intro.kotlin._08_4_operatorfunctions

// Kotlin supports limited operator overloading via specific member or
// extension functions.

data class Zebra(var name: String = "Zebra") {

	// Operators are overloaded for a type by way of 'operator' funs that
	// have well known names. For '++' it's 'inc()', for '--' it's 'dec()', etc.

	operator fun inc() = Zebra("More $name")

	// See the Kotlin docs for the full list and their contracts:
	// https://kotlinlang.org/docs/reference/operator-overloading.html
}

// Operator overloading is one of the core Kotlin language features that
// can be leveraged for creating internal DSLs. For example, overloading
// 'invoke()' can be used to make a String executable.

operator fun String.invoke() = println("To the moon, $this!")

// Note that not all operators are overloadable, e.g. '=', '===', '!=='.

// There is no mechanism for defining additional first-class operators,
// though this can be simulated with infix functions to be covered next.

// Like extension functions, operator overloads are scoped and must be
// imported to have any effect.

// Member operator overloads shadow extension operator overloads, which
// prevents overriding the base operator implementations for common types.

operator fun Int.minus(other: Int) = this + other

fun main() {

	var zebra = Zebra()

	println(zebra)

	zebra++

	println(zebra)

	"Alice"()

	println("1 - 1: ${1 - 1}")
}
