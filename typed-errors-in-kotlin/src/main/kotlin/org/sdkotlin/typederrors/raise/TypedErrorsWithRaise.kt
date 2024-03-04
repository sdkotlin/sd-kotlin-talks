package org.sdkotlin.typederrors.raise

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import arrow.core.raise.recover
import org.sdkotlin.typederrors.Error
import org.sdkotlin.typederrors.Error.BadFruitError
import org.sdkotlin.typederrors.Fruit
import org.sdkotlin.typederrors.Fruit.Apple
import org.sdkotlin.typederrors.Fruit.Banana
import org.sdkotlin.typederrors.Fruit.Grapes
import org.sdkotlin.typederrors.Fruit.Orange

/**
 * A fluent, mutation-free builder where some add methods may raise errors.
 */
interface FruitBasketBuilder {

	context(Raise<Error>)
	fun addApple(apple: Apple): FruitBasket

	context(Raise<Error>)
	fun addBanana(banana: Banana, microSievertsLimit: Double): FruitBasket

	context(Raise<Error>)
	fun addGrapes(grapes: Grapes): FruitBasket

	fun addOrange(orange: Orange): FruitBasket
}

/**
 * Also its own builder.
 */
interface FruitBasket : FruitBasketBuilder {

	/**
	 * The companion object implements the builder interface to bootstrap
	 * the fluent add chain.
	 */
	companion object : FruitBasketBuilder {

		context(Raise<Error>)
		override fun addApple(apple: Apple): FruitBasket =
			FruitBasketImpl().addApple(apple)

		context(Raise<Error>)
		override fun addBanana(
			banana: Banana,
			microSievertsLimit: Double,
		): FruitBasket =
			FruitBasketImpl().addBanana(banana, microSievertsLimit)

		context(Raise<Error>)
		override fun addGrapes(grapes: Grapes): FruitBasket =
			FruitBasketImpl().addGrapes(grapes)

		override fun addOrange(orange: Orange): FruitBasket =
			FruitBasketImpl().addOrange(orange)
	}

	val fruit: List<Fruit>

	private data class FruitBasketImpl(
		override val fruit: List<Fruit> = emptyList(),
	) : FruitBasket {

		context(Raise<Error>)
		override fun addApple(apple: Apple): FruitBasket {

			ensure(!apple.hasWorm) { BadFruitError }

			// All adds return an appended copy rather than mutate the
			// original instance.
			return FruitBasketImpl(fruit + apple)
		}

		context(Raise<Error>)
		override fun addBanana(
			banana: Banana,
			microSievertsLimit: Double,
		): FruitBasket {

			ensure(banana.microSieverts < microSievertsLimit) { BadFruitError }

			return FruitBasketImpl(fruit + banana)
		}

		context(Raise<Error>)
		override fun addGrapes(grapes: Grapes): FruitBasket {

			ensure(!grapes.moreLikeRaisins) { BadFruitError }

			return FruitBasketImpl(fruit + grapes)
		}

		override fun addOrange(orange: Orange): FruitBasket =
			FruitBasketImpl(fruit + orange)
	}
}

context(Raise<Error>)
fun goGroceryShopping(): FruitBasket {

	val isTuesday = true
	val microSievertsLimit = 5.0

	// Needs to be a `var` since we can't chain iteration, and it seems to
	// be the most natural way to do conditional adds.
	var fruitBasket: FruitBasket

	// Can chain.
	fruitBasket = FruitBasket
		.addApple(Apple(hasWorm = false))
		.addBanana(Banana(microSieverts = 1.0), microSievertsLimit)
		.addBanana(Banana(microSieverts = 2.2), microSievertsLimit)

	// Conditional adds via breaking the chain.
	if (isTuesday) {
		fruitBasket =
			fruitBasket.addGrapes(Grapes(moreLikeRaisins = false))
	}

	// Iterative adds via breaking the chain.
	repeat(3) {
		fruitBasket = fruitBasket.addOrange(Orange)
	}

	return fruitBasket
}

fun main() {

	recover(
		block = { println("Shopping result: ${goGroceryShopping()}") },
		recover = { println("Error: $it") }
	)
}
