package org.sdkotlin.intro.kotlin._08_2_tailrecursivefunctions

/**
 * Debugger didn't used to show variable changes in tailrec functions per
 * [KT-47203](https://youtrack.jetbrains.com/issue/KT-47203), but now it does.
 */
tailrec fun tailrecFun(number: Int): Int =
	if (number < 10) {
		number
	} else {
		tailrecFun(number - 1) // breakpoint here
	}

fun main() {
	tailrecFun(15)
}
