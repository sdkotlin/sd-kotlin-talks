package org.sdkotlin.intro.kotlin._08_functions

// Functions are first-class types in Kotlin, as we we've seen with them being
// defined top-level.

fun theBoss() = "I ain't got no class"

// They have types and can be assigned to variables.

val theBossFunction: () -> String = ::theBoss

// Function types can be instantiated with a lambda expression.

// A lambda expression is a literal for a function, much like '1' is a
// literal for an Int.

val addFunction: (Int, Int) -> Int = { a: Int, b: Int -> a + b }

// The parameter types are optional if they can be inferred.

val subtractFunction: (Int, Int) -> Int = { a, b -> a + b }

// You may want to use them so the function type can be inferred.

val multiplyFunction = { a: Int, b: Int -> a + b }

// Lambdas can be multiline. The result of the last expression in the lambda is
// its return type. As this is somewhat invisible, it may be better to declare
// function types explicitly.

// In practice you're often writing lambda expressions at function call sites.
// There the function type is already declared for you, so this tends not to
// come up very often.

val divideFunction = { a: Int, b: Int ->
	a / b
	println("Debugging HERE")
} // '(Int, Int) -> Unit'!

// Unlike Java, lambdas in Kotlin must always be enclosed in curly braces.
//val alienFunction: (Int) -> Int = a -> a % a

// If a function takes only one argument the parameter declaration for it,
// including the arrow, can be omitted. Then the compiler gives the lambda an
// implicit 'it' parameter.

val incrementFunction: (Int) -> Int = { it + 1 }

// When

// Lambda's can access variables from the scope where they're declared.

val almostPi = 355 / 113

val getPiApproximation = { almostPi }

// If the outer variable is mutable, the lambda can change it, and the change
// is seen outside the lambda.

var porridge = "Too hot!"

val goldilocksFunction = { porridge = "Just right." }

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

	println("The toString() for a function: \"$theBossFunction\"")
	println("Invoking theBossFunction(): ${theBossFunction()}")
	println("2+2: ${doMath(2, 2, addFunction)}")
	println("2+3: ${doMath(2, 3, giveMeAnAddFunction())}")
	println("2-2: ${doMath(2, 2, subtractFunction)}")
	println("2*2: ${doMath(2, 2, multiplyFunction)}")
	//println("2/2: ${doMath(2, 2, divideFunction)}")
	println("1++: ${incrementFunction(1)}")

	// Omitting the curly braces for a lambda in Kotlin is not allowed at the
	// call site either.
	//doMath( (a, b) -> a / b )

	println("porridge before goldilocksFucntion(): $porridge")
	goldilocksFunction()
	println("porridge after goldilocksFucntion(): $porridge")

	// If a function takes another function as its last argument, and the
	// argument is given as a lambda, the function call can be made with the
	// lambda placed outside the parenthesis. This facilitates internal DSLs
	// and LINQ-style code.

	doMath(2, 4) { a, b -> a * b }

	// And if there are no other arguments the parenthesis can be omitted.

	doMath { a, b -> a / b }
}
