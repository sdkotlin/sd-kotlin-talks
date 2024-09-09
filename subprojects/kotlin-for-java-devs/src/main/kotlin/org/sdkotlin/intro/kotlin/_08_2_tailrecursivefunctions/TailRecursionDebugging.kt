package org.sdkotlin.intro.kotlin._08_2_tailrecursivefunctions

/**
 * Debugger doesn't show varaible changes in tailrec functions per:
 * [KT-47203](https://youtrack.jetbrains.com/issue/KT-47203).
 */
tailrec fun tailrecFun(number: Int): Int = if (number < 10) {
	number
} else {
	tailrecFun(number - 1) // breakpoint here
}

fun main() {
	tailrecFun(15)
}
