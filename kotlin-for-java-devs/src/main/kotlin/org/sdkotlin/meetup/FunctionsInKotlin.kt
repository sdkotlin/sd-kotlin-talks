package org.sdkotlin.meetup

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
// As seen above, it is the inferred default return type.
// It can be omitted from the signature,
// and no return statement for it is required.
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

// Parameters can have default values!
fun defaultParams(x: Int = 0): Int {
	return x
}

fun useDefaultParams() {
	println(defaultParams()) // Prints 0
	println(defaultParams(1)) // Prints 1
}

fun namedParams(
		firstName: String = "John",
		lastName: String = "Doe",
		email: String,
		favoriteProgrammingLanguage: String = "Kotlin"
) {
	println("$firstName $lastName $email $favoriteProgrammingLanguage")
}

// You can name function parameters to disambiguate default params,
// or just to make function calls with large numbers of params more clear
fun useNamedParams() {
	namedParams(lastName = "Smith", email = "smith@smithery.com")
}

fun main(args: Array<String>) {

	function()
	println(add(2, 2))
	println(subtract(3, 1))
	println(multiply(3, 3))
	println(voidish())
	println(cantTouchThis(1))
	useDefaultParams()
	useNamedParams()
}
