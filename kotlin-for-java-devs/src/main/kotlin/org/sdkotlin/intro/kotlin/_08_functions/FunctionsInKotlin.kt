package org.sdkotlin.intro.kotlin._08_functions

// Functions are fun in Kotlin

fun function() {}

// As with variables, the return type comes second

fun add(a: Int, b: Int): Int {
	return a + b
}

// One-liners can be defined with "=" instead of curly braces

fun subtract(a: Int, b: Int): Int = a - b

// Return types for one-liners are optional when they can be inferred

fun multiply(a: Int, b: Int) = a * b

// The rough equivalent of void is "Unit".
// Unit is an actual object, a singleton.
// It is the default return type, so it can be omitted from the signature.
// No return statement is required for functions that return Unit.
// Kotlin is terse like that.

fun voidish(): Unit {}

// Function parameters are always val

fun cantTouchThis(i: Int): Int {

	// return ++i // Does not compile

	/* You can assign their value to a mutable var
	var j = i
	return ++j
	*/

	// Or just try another approach
	return i + 1
}

// Parameters can have default values.

fun defaultParams(x: Int = 0): Int {
	return x
}

fun useDefaultParams() {
	println(defaultParams()) // Prints 0
	println(defaultParams(1)) // Prints 1
}

// You can name function arguments to disambiguate defaulted params,
// or just to make function calls with many arguments more clear.
// Default arguments and named arguments eliminate the need for method overloads in most cases!

fun namedParams(
		firstName: String = "John",
		lastName: String = "Doe",
		email: String,
		favoriteProgrammingLanguage: String = "Kotlin"
) {
	println("$firstName $lastName $email $favoriteProgrammingLanguage")
}

fun useNamedParams() {
	namedParams(lastName = "Smith", email = "smith@smithery.com")
}

fun main() {
	function()
	println(add(2, 2))
	println(subtract(3, 1))
	println(multiply(3, 3))
	println(voidish())
	println(cantTouchThis(1))
	useDefaultParams()
	useNamedParams()
}
