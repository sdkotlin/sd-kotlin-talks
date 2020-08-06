package org.sdkotlin.intro.kotlin._08_functions

// Functions are first-class types in Kotlin, as we we've seen with them being
// defined top-level.

fun theBoss() = "I ain't got no class"

// They have types and can be assigned to variables.

val theBossFunction: () -> String = ::theBoss

// Function types can be instantiated with a lambda expression.

val addFunction: (Int, Int) -> Int = { a, b -> a + b }

// Functions can take and return other functions, making them "higher-order
// functions".

fun doMath(
		a: Int = 1,
		b: Int = 1,
		operation: (Int, Int) -> Int): Int {

	// You call the passed in function by its parameter name.

	return operation(a, b)
}

fun giveMeAnAddFunction(): (Int, Int) -> Int {
	return addFunction
}

fun main() {

	println(theBossFunction)
	println(theBossFunction())
	println(doMath(2, 2, addFunction))
	println(doMath(2, 3, giveMeAnAddFunction()))

	// If a function takes another function as its last argument, and the
	// argument is given as a lambda, the function call can be made with the
	// lambda placed outside the parenthesis.

	doMath(2, 4) { a, b -> a * b }

	// And if there are no other arguments the parenthesis can be omitted.

	doMath { a, b -> a / b }

	// You can define functions inside functions, i.e. local functions.

	fun garbageInGarbageOut(trash: String) = trash

	println(garbageInGarbageOut("Hey!"))
}
