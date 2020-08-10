package org.sdkotlin.intro.kotlin._08_functions

// Kotlin supports tail call elimination.

// Take this standard recursive implementation of factorial(n):

fun factorial(n: Long): Long =
		if (n == 0L) 1
		else n * factorial(n - 1)

// Simple, but it will throw a stack overflow error if called with too big a
// number.

// We could fall back on an iterative implementation.

fun iterativeFactorial(n: Long): Long {
	var accumulator = 1L
	for (i in 1..n + 1) {
		accumulator *= i
	}
	return accumulator
}

// We had to introduce a mutable variable for this. Even though it's a local
// variable, that's still considered bad form in functional programming.

// Kotlin offers us another option that is both stack efficient and functional
// programming friendly. If we can write the recursion such that the return is
// calculated from a call to the function itself and nothing else, called
// "tail recursion", then the compiler can rewrite the recursion as a loop for
// us (tail call elimination).

tailrec fun tailFactorial(i: Long, accumulator: Long = 1): Long =
		if (i <= 0) accumulator
		else tailFactorial(i - 1, i * accumulator)

// For reference, here is a head-recursive implementation with an accumulator.
// Kotlin can't do "head-call elimination", so the original version without the
// accumulator is probably better.

fun headFactorial(n: Long, accumulator: Long = 1): Long =
		if (n > 0) headFactorial(n - 1, accumulator * n)
		else accumulator

// Note that in the original implementation the recursive call was not fully in
// tail position because control had to be returned back up the stack for the
// 'n * ...' calculation. The accumulator moves the calculation to before the
// recursive call.

// The required 'accumulator' parameter defaulted to 1 has dirtied up our
// public API for this function. It's an implementation detail, and the caller
// is never intended to specify any value for it other than 1.
// Kotlin supports local functions, i.e. functions defined inside other
// functions, which allows us to clean this up.

fun cleanTailFactorial(n: Long): Long {
	tailrec fun recurse(i: Long, accumulator: Long = 1): Long =
			if (i <= 0) accumulator
			else recurse(i - 1, i * accumulator)
	return recurse(n)
}

fun getStackDepth(i: Int = 0): Int =
		try {
			getStackDepth(i + 1)
		} catch (e: StackOverflowError) {
			i
		}

fun main() {
	println("Recursive 5!: ${factorial(5)}")
	val stackDepth = getStackDepth()
	println("Stack depth: $stackDepth")
	// TODO: Why does stack depth change and why does this not succeed?
	// println("Recursive ${stackDepth - 1})!: ${factorial(stackDepth - 1)}")
	val maxShort = Short.MAX_VALUE.toLong()
	try {
		factorial(maxShort)
	} catch (e: StackOverflowError) {
		println("Stack overflow error with factorial($maxShort)!")
	}
	// Zero due to overflow, but, it doesn't overflow the stack!
	println("Iterative factorial($maxShort): ${iterativeFactorial(maxShort+2)}")
	try {
		headFactorial(maxShort)
	} catch (e: StackOverflowError) {
		println("Stack overflow error with headFactorial($maxShort)!")
	}
	// Zero due to overflow, but, it doesn't overflow the stack!
	println("Tail recursive factorial($maxShort): ${tailFactorial(maxShort)}")
}
