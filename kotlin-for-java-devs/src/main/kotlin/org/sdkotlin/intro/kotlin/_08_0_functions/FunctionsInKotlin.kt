package org.sdkotlin.intro.kotlin._08_0_functions

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

fun voidish(): Unit {}

// Function parameters are always val

fun cantTouchThis(i: Int): Int {

	// Val cannot be reassigned
	//return ++i

	//You can assign their value to a mutable var
	//var j = i
	//return ++j

	// Or just try another approach
	return i + 1
}

// Parameters can have default values.

fun defaultParams(x: Int = 0): Int {
	return x
}

fun useDefaultParams() {
	println("Using default: ${defaultParams()}") // Prints 0
	println("Overriding default: ${defaultParams(1)}") // Prints 1
}

fun moreDefaultedParams(
		firstName: String,
		lastName: String,
		favoriteProgrammingLanguage: String = "Kotlin",
		domain: String = "example.com",

		// A default can refer to earlier arguments, whether they're defaulted
		// or not.
		email: String = "$firstName.$lastName@$domain") {

	println("$firstName $lastName $email $favoriteProgrammingLanguage")
}

fun useNamedParams() {

	// You can name function call arguments to disambiguate defaulted params,
	// or just to make function calls with many arguments more clear.

	moreDefaultedParams(
			firstName = "John",
			lastName = "Smith",
			domain = "smithery.com")

	// You can mix positional and named arguments, but the positional ones all
	// need to come first.

	moreDefaultedParams(
			"John",
			lastName = "Smith")

	// Default and named arguments eliminate the need for method overloads in
	// many cases!
}

fun main() {
	function() // no-op
	println("add(2, 2): ${add(2, 2)}")
	println("subtract(3, 1): ${subtract(3, 1)}")
	println("multiply(3, 3): ${multiply(3, 3)}")
	println("voidish(): ${voidish()}")
	println("cantTouchThis(1): ${cantTouchThis(1)}")
	useDefaultParams()
	useNamedParams()
}
