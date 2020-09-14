package org.sdkotlin.intro.kotlin._28_variance

// Kotlin has different facilities for type generic type variance than Java.

// We say a `String` 'is an' `Any`. Is an `Array<String>` also an
// `Array<Any>`? Is a `List<String>` a `List<Any>`? Let's find out...

fun `with substitution`() {

	// First, let's just demonstrate that a `String` is an `Any`.

	val string = "Hello"

	// Per the Liskov Substitution Principle, if `String` is a subtype of `Any`,
	// we should be able to substitute an instance of type `String` wherever an
	// instance of type `Any` is expected, while still having a correct program.

	// The compiler is fine with us assigning the string to a variable of type
	// `Any`.

	val any: Any = string

	// We can also call a function that takes an `Any` with either.

	fun printAny(any: Any) = println(any.toString())

	print("printAny(string): "); printAny(string)
	print("printAny(any): "); printAny(any)

	// So far so good. Both have the `toString()` function as it's declared by
	// `Any`.

	// Note that the opposite is not possible. There is no guarantee that an
	// instance referenced via the supertype `Any` has the functions defined by
	// the subtype `String`. For example, an `Any` doesn't necessarily have a
	// `trim()` function.

	fun printString(string: String) = println(string.trim())

	print("printString(string): "); printString(string) // Fine.
	//printString(any) // Does not compile.
}

// Moving on to generics.

// Before we look at collections like `Array` and `List`, let's start with a
// simple, generic, single-element container.

class Container<T>(var contents: T)

fun `with simple container variance`() {

	// Let's put a string in the container. That makes it a `Container<String>`
	// via type inference.

	val stringContainer = Container("Testing")

	// Now, if a `Container<String>` 'is a' `Container<Any>`, as a `String` 'is
	// an' `Any`, we should be able to substitute an instance of a
	// `Container<String>` wherever a `Container<Any>` is expected, still
	// having a correct program.

	// Right away the compiler isn't letting us assign our string container to
	// a `Container<Any>` reference.

	//val anyContainer: Container<Any> = stringContainer // Does not compile.

	// No luck calling a function with it that expects a `Container<Any>`
	// either.

	fun printContents(container: Container<Any>) =
		println(container.contents.toString())

	//printContents(stringContainer) // Does not compile.

	// Why is that when the contents of both will have the requisite
	// `toString()` API from `Any`? To see why, let's force the issue with an
	// unsafe cast.

	val anyContainer = stringContainer as Container<Any>

	// Uh oh, we can put an `Int` in what's originally a `String` container!
	// That doesn't smell right.

	anyContainer.contents = 1

	// We can cast our container with an `Int` in it back to a `String`
	// container. Even more fishy.

	val backToStringContainer = anyContainer as Container<String>

	// And here is the problem, if we try to access the contents of our string
	// container that now contains an integer we fail the user with a
	// ClassCastException... :(

	try {
		val KABLAMO: String = backToStringContainer.contents
	} catch (e: ClassCastException) {
		println(e)
	}
}

fun main() {
	`with substitution`()
	`with simple container variance`()
}
