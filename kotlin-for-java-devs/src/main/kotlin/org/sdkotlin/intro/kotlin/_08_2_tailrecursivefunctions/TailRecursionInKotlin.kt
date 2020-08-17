package org.sdkotlin.intro.kotlin._08_2_tailrecursivefunctions

// Kotlin supports tail call elimination.

// Take this standard recursive implementation for the triangle number of 'n':

fun triangleNumber(n: Int): Int =
		if (n == 0) 0
		else n + triangleNumber(
				n - 1)

// Simple, but it will throw a stack overflow error if called with too big a
// number.

// We could fall back on an iterative implementation.

fun iterativeTriangleNumber(n: Int): Int {
	var accumulator = 0
	for (i in 1..n) {
		accumulator += i
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

tailrec fun tailTriangleNumber(i: Int, accumulator: Int = 0): Int =
		if (i <= 0) accumulator
		else tailTriangleNumber(
				i - 1, i + accumulator)

// The 'tailrec' keyword is not required for the compiler to do tail call
// optimization, but it is useful in that it will produce a warning if the
// function can't be tail call optimized, now or in the future.

// Here is a head-recursive implementation with an accumulator. It should stack
// overflow as is, but you can add 'tailrec' to it without a warning because
// the compiler can invert the if block, and then it can be tail call optimized.

//tailrec
fun headTriangleNumber(n: Int, accumulator: Int = 0): Int =
		if (n > 0) headTriangleNumber(
				n - 1, accumulator + n)
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

fun cleanTailTriangleNumber(n: Int): Int {
	tailrec fun recurse(i: Int, accumulator: Int = 0): Int =
			if (i <= 0) accumulator
			else recurse(i - 1, i + accumulator)
	return recurse(n)
}

fun getStackDepth(i: Int = 0): Int =
		try {
			getStackDepth(
					i + 1)
		} catch (e: StackOverflowError) {
			i
		}

fun main() {
	println("T5: ${triangleNumber(
			5)}")
	val stackDepth =
			getStackDepth()
	println("Stack depth: $stackDepth")
	// TODO: Why does stack depth change and why does this not succeed?
	//println("Recursive T${stackDepth - 1}: ${triangleNumber(stackDepth - 1)}")
	try {
		println("Recursive T$stackDepth: ${triangleNumber(
				stackDepth)}")
	} catch (e: StackOverflowError) {
		println("Stack overflow error with recursive T$stackDepth!")
	}
	println("Iterative T$stackDepth: ${iterativeTriangleNumber(
			stackDepth)}")
	try {
		println("Head recursive T$stackDepth: ${headTriangleNumber(
				stackDepth)}")
	} catch (e: StackOverflowError) {
		println("Stack overflow error with head call T$stackDepth!")
	}
	println("Tail recursive T$stackDepth: ${tailTriangleNumber(
			stackDepth)}")
}
