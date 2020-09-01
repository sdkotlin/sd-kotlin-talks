package org.sdkotlin.intro.kotlin._23_generics

import org.sdkotlin.intro.kotlin._23_generics.util.itsYourBirthday
import kotlin.math.PI

// Like Java, Kotlin supports type parameters for classes and functions.

// Who doesn't like all different kinds of presents.

class Present(private val gift: Any) {
	fun unwrap(): Any = gift
}

// The problem here is that when you unwrap the present, you still don't have a
// gift you can use.

fun giveAndReceive() {

	val present = Present(PI)

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

class SecurePresent<T>(private val gift: T) {
	internal fun unwrap(): T = gift
}

fun <T> unwrapOnYourBirthday(present: SecurePresent<T>): T? =
	if (itsYourBirthday()) present.unwrap() else null

fun giveAndReceiveOnBirthday() {

	val present = SecurePresent(PI)

	val gift = unwrapOnYourBirthday(present)

	val amountOfPie = gift?.times(36) ?: 0.0
}
