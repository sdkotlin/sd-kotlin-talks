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

	val stringContainer = Container("Hello")

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

fun `with collection variance`() {

	// Given the above, we might be able to guess that arrays in Kotlin are
	// "invariant", meaning an `Array<Any>` is not a supertype of
	// `Array<String>`.

	val stringArray = arrayOf("Hello", "World")

	//val nope: Array<Any> = stringArray // Does not compile

	// Same for lists then, right?

	val stringList = listOf("Hello", "World")

	val anyList: List<Any> = stringList

	// Whoa! Why does that work? Well, notice that all the trouble above seemed
	// to come about because our string container was mutable, and hence
	// through some ill-advised casting we were able to store an integer in it.
	//
	// The compiler did try to discourage us from doing that by preventing
	// reassignment to an `Any` container unless we used the unsafe cast
	// operator, aptly named given the trouble we got into with it.
	//
	// So why isn't it doing the same for our `List` here?
	//
	// A somewhat oversimplified simplified answer is because `List` in Kotlin
	// is immutable, and `Array` is not. (We'll get to a more complete answer
	// shortly.)
	//
	// Let's try the experiment again with a mutable list.

	val mutableStringList = mutableListOf("Hello", "World")

	//val mutableAnyList: MutableList<Any> = mutableStringList // Does not compile.

	// Okay, that's more like it. Apples for apples, mutable arrays and lists
	// are both invariant. But how did the compiler know? Did it exhaustively
	// analyze the `List` API and determine there was no way to change change
	// its contents after being instantiated?

	// Well, let's see if it will do that for our simple container class if we
	// make it immutable.

	class ImmutableContainer<T>(val contents: T)

	val immutableStringContainer = ImmutableContainer("Hello")

	//val immutableAnyContainer: ImmutableContainer<Any> =
	//	immutableStringContainer // Does not compile.

	// Hmm, no such luck. Read on...
}

fun `with producers`() {

	// It turns out the answer is that Kotlin has a type parameter annotation
	// `out`, which we can use to say that a type is only every read, never
	// written. To say it differently, we can declare when a type parameter is
	// used for something that is only every output, never taken in.

	class Producer<out T>(val contents: T)

	val stringProducer = Producer("Hello")

	val anyProducer: Producer<Any> = stringProducer

	// Huzzah! An immutable container ("producer") of `String` can safely be a
	// subtype of an immutable container of `Any`, as `String` is a subtype of
	// `Any`. We just have to let the compiler know the container is
	// exclusively a producer via an `out` annotation.

	// We say such producers are "covariant" because their inheritance
	// relationship mirrors that of the types they produce:
	//
	// `Producer<Any>` <- `Producer<String>`
	// `Any` <- `String`

	// The compiler will prevent us from making a producer read-write, for
	// example by making the contents `var`.

	//class InvalidProducer<out T>(var contents: T) // Does not compile.

	// It'll also catch function arguments.

	class ConsumingProducer<out T> {
		//fun nope(input: T) = println(input) // Does not compile.
	}
}

fun main() {
	`with substitution`()
	`with simple container variance`()
	`with collection variance`()
	`with producers`()
}
