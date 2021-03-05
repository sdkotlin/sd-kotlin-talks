package org.sdkotlin.intro.kotlin._23_generics

import org.sdkotlin.intro.kotlin._23_generics.util.itsYourBirthday
import kotlin.math.PI

// Like Java, Kotlin supports type parameters for classes and functions.

// Who doesn't like all different kinds of presents.

class LamePresent(private val gift: Any) {
	fun unwrap(): Any = gift
}

// The problem here is that when you unwrap the present you don't have a gift
// you can use.

fun `give and receive FAIL`() {

	val present = LamePresent(PI)

	val gift = present.unwrap()

	//val amountOfPie = gift * 36 // `gift` is an `Any`, so does not compile.
}

// If we add a type parameter, we can get the desired type when we unwrap the
// present.

class BetterPresent<T>(private val gift: T) {
	fun unwrap(): T = gift
}

fun `better give and receive`() {

	val present = BetterPresent(PI) // Kotlin infers the type parameter

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

fun `only open on your birthday`() {

	val present = SurprisePresent(PI)

	val gift = unwrapOnYourBirthday(present)

	val amountOfPie = gift?.times(36) ?: 0.0
}

// Generics are often useful for extension functions.

fun <T> T.asPresent() = SurprisePresent(this)

fun `gift anything`() {
	val yummyGift = PI
	val generousGift = Long.MAX_VALUE

	val perishableSurprise = yummyGift.asPresent()
	val largeSurprise = generousGift.asPresent()
}

// To delve deeper into generics let's enrich our model a bit. Presents can be
// from and to someone, but then so can phone calls. Following the Interface
// Segregation Principle to the extreme, let's make separate interfaces to
// cover both.

interface Fromable {
	val from: String
}

interface Toable {
	val to: String
}

class TaggedPresent<T>(
	override val from: String,
	override val to: String,
	private val gift: T,
) : Fromable, Toable {
	fun unwrap(): T = gift
}

class PhoneCall(
	override val from: String,
	override val to: String,
	val message: String,
) : Fromable, Toable

// Let's say we only want to answer calls or open presents from Mom today. We
// can use an upper type bound to generically handle anything that is
// `Fromable`.

fun <F : Fromable> onlyFrom(from: String, thing: F): F? =
	if (from == thing.from) thing else null

// Whatever specific type `F` is, we know it has a `from` property because it
// "is a" `Fromable`.

fun `only from Mom`() {
	val present = TaggedPresent(from = "Mom", to = "Me", gift = PI)
	val otherPresent = TaggedPresent(from = "Sis", to = "Me", gift = "Rocks")

	val call = PhoneCall(from = "Mom", to = "Me", message = "Hi sweetie!")
	val otherCall =
		PhoneCall(from = "Sis", to = "Me", message = "You stink!")

	val firstPresent = onlyFrom("Mom", present)?.unwrap() ?: "Didn't open"
	val secondPresent = onlyFrom("Mom", otherPresent)?.unwrap() ?: "Didn't open"

	val firstCall = onlyFrom("Mom", call)?.message ?: "Hung up"
	val secondCall = onlyFrom("Mom", otherCall)?.message ?: "Hung up"

	println("firstPresent: $firstPresent")
	println("secondPresent: $secondPresent")

	println("firstCall: $firstCall")
	println("secondCall: $secondCall")
}

// Let's say we only want to answer calls or open presents based on both who
// they're from and to. We can specify multiple type bounds with a `where`
// clause.

fun <FT> onlyFromTo(from: String, to: String, thing: FT): FT?
		where FT : Fromable,
		      FT : Toable =
	if (from == thing.from && to == thing.to) thing else null

// Here we know `FT` has a `from` and `to` property regardless of whatever else
// it may be.

// As for why the `where` clause, see:
// https://discuss.kotlinlang.org/t/why-the-scattered-generic-types/1917/2

fun main() {
	`only from Mom`()
}
