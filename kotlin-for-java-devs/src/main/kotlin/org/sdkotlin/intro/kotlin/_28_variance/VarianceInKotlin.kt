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

fun `with array and collection variance`() {

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


fun `with consumers`() {

	// Let's look at the opposite case, classes that only consume but not
	// produce instances of a generic type parameter:

	class NaiveTester<T> {
		fun test(input: T): Boolean = input.hashCode() > 5
	}

	// It's not mutable, so is a `NaiveTester<String>` a subclass of
	// `NaiveTester<Any>`?

	val naiveStringTester = NaiveTester<String>()

	//val nope: NaiveTester<Any> = naiveStringTester // Does not compile.

	// Not automatically, but Kotlin has an `in` type annotation that's like
	// `out` for when a type parameter is only used in "in" position.

	abstract class Tester<in T> {
		abstract fun test(input: T): Boolean
	}

	val stringTester = object: Tester<String>() {
		override fun test(input: String) = input.trim() == "Hello"
	}

	//val nope: Tester<Any> = stringTester // Does not compile.

	// Uh oh, what happened? If `Any` <- `String`, why isn't an immutable
	// `Tester<Any>` <- `Tester<String>`? To see why we can look at its
	// use.
	//
	// If a `Tester<String>` "is a" `Tester<Any>`, then we should be able to
	// use a `Tester<String>` wherever a `Tester<Any>` is expected, still
	// having a correct program. Let's try it.

	val anyTester = object: Tester<Any>() {
		override fun test(input: Any) = input.hashCode() > 5
	}

	val any: Any = 1

	val anyTest = anyTester.test(any)

	//val otherAnyTest = stringTester.test(any) // Does not compile.

	// Ah ha! Makes sense, an `Any` doesn't necessarily have all the API that
	// a tester expecting a `String` would use.

	// What about the other way around? Couldn't something expecting a
	// `Tester<String>` also accept a `Tester<Any>`?

	val string = "Hello"

	val stringTest = stringTester.test(string)

	val otherStringTest = anyTester.test(string)

	// That works. Because a `String` "is an" `Any`, a `String` can be taken in
	// and tested by a `Tester<String>` and a `Tester<Any>`.

	// What we've seen here is that while a `Tester<String>` "is not a"
	// `Tester<Any>`, a `Tester<Any>` "is a" `Tester<String>`.
	//
	// This is "contravariance". For a type parameter in "in" position, there
	// is an inverse inheritance relationship for a consumer compared to the
	// relationship of what it's consuming.
	//
	// `Any` <- `String`
	// `Consumer<Any>` -> `Consumer<String>`

	// To summarize, both arrays and collections in Kotlin are by default
	// invariant. In fact, arrays aren't even special types in Kotlin, they're
	// just a particular generic collection, much like a `MutableList`.
	//
	// Producers can safely be covariant, and Kotlin supports declaring them as
	// such by annotating type parameters at the declaration site with `out`.
	// They call this declaration-site variance.
	//
	// Similarly, consumers can safely be contravariant, and declared with
	// `in`.
	//
	// The compiler will prevent you from adding API that violates producer or
	// consumer contracts when annotating the type parameter with `out` or `in`
	// respectively.

	// We can distill these traits of covariance and contravariance down to a
	// simple pair of rules:
	//
	// For a type to be a subtype it must:
	//   1. Take in _at least_ the same set of types as its supertype
	//   2. Output _at most_ the same set of types as its supertype

	// How is any of this better than how variance is handled in Java?
	// See: VarianceInJava.java...
}

fun `with use-site variance`() {

	// Kotlin does also support use-site declaration of type parameter variance.
	// This is useful for making an otherwise invariant type covariant or
	// contravariant on a case-by-case basis.

	// This is equivalent to
	// `<T> void copy(Array<? extends T> src, Array<? super T> dest)`
	// in Java, where `src` is covariant and `dest` is contravariant:

	fun <T> copy(src: Array<out T>, dest: Array<in T>) {
		for (i in src.indices) {
			dest[i] = src[i]
		}
	}

	val strings = arrayOf("Hello", "World")
	val anythings = arrayOf<Any>("Howdy", "Planet")

	copy(strings, anythings)

	//copy(anythings, strings) // Does not compile.

	println("anythings: ${anythings.contentDeepToString()}")

	// Kotlin refers to this as "type projection". An invariant class can be
	// "projected" to a covariant class with `out` at the use site so long as
	// you only read from it there. Similarly, it can be projected to be
	// contravariant at the use site with `in` given you only write to it there.
}

fun `with star projections`() {

	// Sometimes you don't care what a type parameter is.
	//
	// For example, let's say we want to get a list of indices for an array.

	// We could define it with a declaration-site out projection like:

	fun indicesOfAny(array: Array<out Any?>) = 0 until array.size

	// Or introduce a type parameter for the function:

	fun <T> indicesOf(array: Array<T>) = 0 until array.size

	// Either will work:

	val arrayOfMaybeAny = arrayOf<Any?>(null, "Planet")
	val arrayOfAny = arrayOf<Any>("Howdy", "Planet")
	val arrayOfString = arrayOf("Hello", "World")

	val indicesOfMaybeAnyAnythings = indicesOfAny(arrayOfMaybeAny)
	val indicesOfAnyAnythings = indicesOfAny(arrayOfAny)
	val indicesOfAnyStrings = indicesOfAny(arrayOfString)

	val indicesOfMaybeEAnythings = indicesOf(arrayOfMaybeAny)
	val indicesOfEAnythings = indicesOf(arrayOfAny)
	val indicesOfEStrings = indicesOf(arrayOfString)

	// Kotlin offers another option called a star projection. It's the
	// idiomatic equivalent to the unbounded wildcard `?` in Java (e.g.
	// `Array<?>`).

	fun indices(array: Array<*>) = 0 until array.size

	val indicesOfAnythings = indices(arrayOfAny)
	val indicesOfStrings = indices(arrayOfString)

	// It's a bit more concise way of saying "any Array".
}

fun main() {
	`with substitution`()
	`with simple container variance`()
	`with array and collection variance`()
	`with producers`()
	`with consumers`()
	`with use-site variance`()
	`with star projections`()
}
