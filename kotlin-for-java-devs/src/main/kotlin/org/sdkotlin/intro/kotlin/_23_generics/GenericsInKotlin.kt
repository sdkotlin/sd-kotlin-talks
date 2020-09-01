package org.sdkotlin.intro.kotlin._23_generics

import org.sdkotlin.intro.kotlin._23_generics.util.itsYourBirthday
import kotlin.math.PI

// Like Java, Kotlin supports type parameters for classes and functions.

// Who doesn't like all different kinds of presents.

class CouldBeAnythingPresent(private val gift: Any) {
	fun unwrap(): Any = gift
}

// The problem here is that when you unwrap the present, you still don't have a
// gift you can use.

fun giveAndReceive() {

	val present = CouldBeAnythingPresent(PI)

	val gift = present.unwrap()

	//val amountOfPie = gift * 36 // `gift` is an `Any` so does not compile.
}

// If we add a type parameter, we can get the desired type when we unwrap the
// present.

class GenericPresent<T>(private val gift: T) {
	fun unwrap(): T = gift
}

fun betterGiveAndReceive() {

	val present = GenericPresent(PI) // Kotlin infers the type parameter

	val gift = present.unwrap()

	val amountOfPie = gift * 36 // `gift` is a `Double` now, so good to go.
}

// We can also have generic functions.

// Say we want to restrict the opening of presents via some externalized logic.

class SurprisePresent<T>(private val gift: T) {
	internal fun unwrap(): T = gift
}

fun <T> unwrapOnYourBirthday(present: SurprisePresent<T>): T? =
	if (itsYourBirthday()) present.unwrap() else null

fun giveAndReceiveOnBirthday() {

	val present = SurprisePresent(PI)

	val gift = unwrapOnYourBirthday(present)

	val amountOfPie = gift?.times(36) ?: 0.0
}

// Generics are often useful for extension functions.

fun <T> T.asPresent() = SurprisePresent(this)

fun giftAnything() {
	val yummyGift = PI
	val generousGift = Long.MAX_VALUE

	val perishableSurprise = yummyGift.asPresent()
	val largeSurprise = generousGift.asPresent()
}
