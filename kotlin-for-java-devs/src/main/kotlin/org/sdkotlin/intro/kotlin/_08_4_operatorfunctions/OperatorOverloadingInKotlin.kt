package org.sdkotlin.intro.kotlin._08_4_operatorfunctions

// Kotlin supports limited operator overloading via specific member or
// extension functions.

data class Zebra(var name: String = "Zebra") {

	// Operators are overloaded for a type by way of 'operator' funs that
	// have well known names.

	operator fun inc() = Zebra("More $name")


}

fun main() {

	var zebra = Zebra()

	println(zebra)

	zebra++

	println(zebra)
}
