package org.sdkotlin.intro.kotlin._XX_returnvalues

// The unused return value checker is experimental. This module's own build
// file enables it with the `-Xreturn-value-checker=full` compiler flag.

// There are two modes. `check` only reports calls to declarations marked
// `@MustUseReturnValues`. `full`, the mode used here, treats every
// declaration in the module as marked.

fun double(value: Int) = value * 2

fun withDroppedResult() {

	// Dropping the result of a pure function accomplishes nothing. The
	// call below warns.

	double(21)

	// An intentional drop is spelled with the unnamed variable `_`.

	val _ = double(21)
}

// Functions whose result is usually irrelevant, such as builders and
// `MutableList.add`, are marked `@IgnorableReturnValue`. Calls to them are
// never reported.

@IgnorableReturnValue
fun StringBuilder.appendGreeting(): StringBuilder = append("Hello")

// `@MustUseReturnValues` marks a file or class as non-ignorable, never a
// single function, so that authors design a whole API that way. That is
// what opts a declaration in under `check` mode.

// The checker is only about dropped function results. It does not make a
// non-exhaustive `when` statement an error. That remains open as
// https://youtrack.jetbrains.com/issue/KT-12380, and the `Unit.sealed()`
// utility in `_10_selection` is still the workaround.

// Java has no compiler equivalent. `javac` says nothing about a dropped
// result. Java developers get this from ErrorProne's `@CheckReturnValue`
// and `@CanIgnoreReturnValue`, or from another static analyzer.

fun main() {

	withDroppedResult()

	StringBuilder().appendGreeting()
}
