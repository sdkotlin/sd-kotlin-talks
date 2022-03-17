package org.sdkotlin.intro.kotlin._14_1_inlineclasses

import java.math.BigDecimal

// Kotlin 1.5 adds initial stable support for value classes, f.k.a.
// inline classes.

// They can help us avoid the primitive obsession smell by efficiently
// declaring new user-defined types for single-value basic and primitive types.

@JvmInline
value class FirstName(val first: String)

@JvmInline
value class LastName(val last: String) {

	// They can have init blocks.
	init {
		require(last.isNotBlank()) {
			"Last name must not be blank."
		}
	}

	// And computed properties.
	val length: Int
		get() = last.length

	// But not properties with backing fields.
	//val uppercased = last.uppercase() // Does not compile.

	// They can have functions
	fun greet() = "Hello, $last"
}

// They allow for strongly-typed function signatures.

fun fullName(firstName: FirstName, lastName: LastName) =
	"$firstName $lastName"

// As opposed to "stringly"-typed function signatures.
fun mixedUpFullName(firstName: String, lastName: String) =
	"$firstName $lastName"

// Value classes compile down to their wrapped types (see bytecode).

fun withValueClasses() {

	val firstNameAsString = "SD"
	val lastNameAsString = "Kotlin"

	val firstName = FirstName("SD")
	val lastName = LastName("Kotlin")

	// We can only call fullName(...) with the arguments in the correct order.

	fullName(firstName, lastName)

	//fullName(lastName, firstName) // Does not compile.

	// Not so with mixedUpName(...).

	println(mixedUpFullName(firstNameAsString, lastNameAsString))
	println(mixedUpFullName(lastNameAsString, firstNameAsString)) // Oops

	// We can't substitute the unwrapped types.

	//fullName(firstNameAsString, lastNameAsString) // Does not compile.

	// We can access and use the API of the value class and the wrapped types.

	println(lastName.greet())
	println(lastName.last.isNotBlank())

	// And see the init block in action.

	try {
		LastName("")
	} catch (e: IllegalArgumentException) {
		println(e)
	}

	// Local value classes are not supported.
	//@JvmInline value class Local(val nope: String) // Does not compile.
}

// You can make value classes for any type, including generic collections,
// other value classes, etc.

@JvmInline
value class Foo(val ey: List<Int>)

@JvmInline
value class Bar(val foo: Foo)

@JvmInline
value class Baz(val bar: Bar) // It's turtles all the way down.

fun withValueClassesOfAnyType() {

	val foo = Foo(listOf(1, 2, 3))
	val bar = Bar(foo)
	val baz = Baz(bar)

	println("$foo, $bar, $baz")
}

// Value classes require immutable properties.
//@JvmInline value class NameChange(var value: String) // Does not compile.

// Multiple properties aren't supported, yet.
//@JvmInline value class FullName(val first: String, val last: String) // Does not compile.

// Value classes can have private properties.

@JvmInline
value class Shh(private val secret: String) {

	val catsOutOfTheBag: String
		get() = secret
}

fun withValueClassEncapsulation() {

	val shh = Shh("Don't tell.")

	//println(shh.secret) // Does not compile.

	println(shh.catsOutOfTheBag)
}

// Value classes can implement interfaces

@JvmInline
value class Gold(val oz: Int) : Comparable<Gold> {
	override fun compareTo(other: Gold): Int = oz.compareTo(other.oz)
}

fun withValueClassComparability() {

	val lessGold = Gold(1)
	val moreGold = Gold(100)

	println(moreGold > lessGold)
}

// Value classes delegate their equals, hashCode, and toString
// implementations to the inlined type.

interface Equatable {
	override fun equals(other: Any?): Boolean
	override fun hashCode(): Int
}

interface Stringable {
	override fun toString(): String
}

@JvmInline
value class Money(val amount: BigDecimal) : Equatable, Stringable {

	// You can't override equals(...), which can be an issue for certain types.
	//override fun equals(other: Any?): Boolean { // Does not compile.
	//	if (other !is Money) return false
	//	return amount.compareTo(other.amount) == 0
	//}
}

fun withValueClassEquality() {

	val oneDollars = Money(BigDecimal("1.00"))
	val twoDollars = Money(BigDecimal("2.00"))

	println(oneDollars)
	println(twoDollars)

	println(oneDollars == twoDollars)

	// Value classes don't have identity.
	//println(oneDollars === oneDollars) // Does not compile.
}

// For more, see:
// * https://github.com/Kotlin/KEEP/blob/master/proposals/inline-classes.md
// * https://github.com/Kotlin/KEEP/blob/master/notes/value-classes.md

fun main() {
	withValueClasses()
	withValueClassesOfAnyType()
	withValueClassEncapsulation()
	withValueClassComparability()
	withValueClassEquality()
}
