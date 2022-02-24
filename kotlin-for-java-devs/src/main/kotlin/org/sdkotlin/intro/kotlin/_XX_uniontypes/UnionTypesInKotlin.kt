package org.sdkotlin.intro.kotlin._XX_uniontypes

import arrow.core.Either
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.Random.Default.nextInt

fun withMulticatch() {

	// Unfortunately, Kotlin doesn't have support for union types in
	// multicatch clauses, yet: https://youtrack.jetbrains.com/issue/KT-7128.

	// They may resolve this with full support for union and intersection types:
	// https://youtrack.jetbrains.com/issue/KT-13108.
}

fun withNullableTypes() {

	// Kotlin has built in support for every type to be treated as a union type
	// of a value of that type or `null`.

	val maybeAwesome: String? = if (nextBoolean()) "Awesome" else null

	// The compiler forces us to check which at compile-time if we want to
	// use any of the API for that type.

	val maybeReallyAwesome: String? = maybeAwesome?.uppercase()
	val alsoMaybeReallyAwesome: String = maybeAwesome ?: "Awesome default"

	// It enforces exhaustive pattern matching in `when` expressions with
	// smart casting.

	val theUtmostAwesomeness = when (maybeAwesome) {
		is String -> maybeAwesome.uppercase()
		else -> "Utmost awesome default"
	}

	// This is much more concise and robust than Java's `Optional<T>`.

	println(maybeReallyAwesome)
	println(alsoMaybeReallyAwesome)
	println(theUtmostAwesomeness)
}

// We can model union types of arbitrary arity with sealed type hierarchies in
// Kotlin.

// These can be implemented with classes, data classes, and objects.

sealed interface GarbageOrFoodOrStuffedAnimal // No API

open class StuffedAnimal : GarbageOrFoodOrStuffedAnimal {
	open fun play() = "Squeek"
}

class Elmo : StuffedAnimal() {
	override fun play() = "Elmo!"
}

data class Food(val type: String) : GarbageOrFoodOrStuffedAnimal {
	fun eat() = type
}

object Garbage : GarbageOrFoodOrStuffedAnimal {
	fun throwAway() = "Yuk"
}

fun withSealedClassesAndInterfaces() {

	val thing: GarbageOrFoodOrStuffedAnimal =
		when (nextInt() % 4) {
			0 -> StuffedAnimal()
			1 -> Elmo()
			2 -> Food("Banana")
			else -> Garbage
		}

	// We can exhaustively pattern match with a `when` expression (and a `when`
	// statement in Kotlin 1.7).

	val whatToDo = when (thing) {
		is StuffedAnimal -> thing.play()
		is Food -> thing.eat()
		is Garbage -> thing.throwAway()
	}

	println(whatToDo)
}

// A killer use case for union types is error handling without exceptions.

// They allow us to create "honest" function signatures that enforce error
// handling at compile-time. Also, we can use plain old classes for error
// types, which are far more efficient to construct than exceptions.

object Wrench {
	fun strike() = "Ouch!"
}

object Dodgeball {
	fun hit() = "Darn"
}

fun withEither() {

	// ArrowKt offers a functional programming companion to the Kotlin standard
	// library. It includes `Either<L, R>`, which is a generic "this or that"
	// union type commonly used for error handling. By convention the "left"
	// value is the erroneous result.

	// Here we declare a function that returns _either_ a `Wrench` or a
	// `Dodgeball`. This is an alternative to returning a `Dodgeball` or
	// throwing an undeclared and unchecked `WrenchException`.

	fun duck(): Either<Wrench, Dodgeball> =
		if (nextBoolean()) {
			Either.Left(Wrench)
		} else {
			Either.Right(Dodgeball)
		}

	val whatToDo: Either<Wrench, Dodgeball> = duck()

	// We can't unwrap the value without checking its type at compile time.
	//val value = whatToDo.value // Does not compile

	// Again, exhaustive pattern matching can (must) be used to handle the
	// result.

	val result = when (whatToDo) {
		is Either.Left -> whatToDo.value.strike()
		is Either.Right -> whatToDo.value.hit()
	}

	println(result)
}

fun main() {

	withNullableTypes()
	withMulticatch()
	withSealedClassesAndInterfaces()
	withEither()
}
