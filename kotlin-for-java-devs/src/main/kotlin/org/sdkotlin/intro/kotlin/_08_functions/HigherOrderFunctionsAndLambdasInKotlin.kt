package org.sdkotlin.intro.kotlin._08_functions

// Functions are first-class types in Kotlin,
// as we've seen with the ability for them to be top-level

fun theBoss() = "I ain't got no class"

// They have types and can be assigned to variables.

val theBossFunction: () -> String = ::theBoss

// Function types can be instantiated with a lambda expression.

val addFunction: (Int, Int) -> Int = { a, b -> a + b }

// Functions can take and return other functions,
// making them "higher-order functions".

fun doMath(operation: (Int, Int) -> Int): Int {
	return operation(1, 1)
}

fun giveMeAnAddFunction(): (Int, Int) -> Int {
	return addFunction
}

fun main() {

	println(theBossFunction)
	println(theBossFunction())
	println(doMath(addFunction))
	println(doMath(giveMeAnAddFunction()))

	// If a function takes another function as its last argument,
	// and the argument is given as a lambda, the function call can
	// be made with the lambda placed outside the parenthesis.

	doMath() { a, b -> a * b }

	// And if there are no other arguments the parenthesis can be omitted.

	doMath { a, b -> a / b }

	// You can define functions inside functions, i.e. local functions.

	fun garbageInGarbageOut(trash: String) = trash

	println(garbageInGarbageOut("Hey!"))
}
