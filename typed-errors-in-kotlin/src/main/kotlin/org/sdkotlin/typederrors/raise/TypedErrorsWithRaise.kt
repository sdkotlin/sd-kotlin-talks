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

interface FruitBasket {

	val fruit: List<Fruit>
}

class FruitBasketBuilder {

	private val fruit: MutableList<Fruit> = mutableListOf()

	context(Raise<Error>)
	fun addApple(apple: Apple): FruitBasketBuilder {

		ensure(!apple.hasWorm) { BadFruitError }
		fruit.add(apple)
		return this
	}

	context(Raise<Error>)
	fun addBanana(
		banana: Banana,
		microSievertsLimit: Double,
	): FruitBasketBuilder {

		ensure(banana.microSieverts < microSievertsLimit) { BadFruitError }
		fruit.add(banana)
		return this
	}

	context(Raise<Error>)
	fun addGrapes(grapes: Grapes): FruitBasketBuilder {

		ensure(!grapes.moreLikeRaisins) { BadFruitError }
		fruit.add(grapes)
		return this
	}

	fun addOrange(orange: Orange): FruitBasketBuilder {

		fruit.add(orange)
		return this
	}

	fun build(): FruitBasket = FruitBasketImpl(fruit)
}

private data class FruitBasketImpl(
	override val fruit: List<Fruit> = emptyList(),
) : FruitBasket

context(Raise<Error>)
fun goGroceryShopping(): FruitBasket {

	val isTuesday = true
	val microSievertsLimit = 5.0

	val fruitBasketBuilder = FruitBasketBuilder()

	// Can chain.
	fruitBasketBuilder
		.addApple(Apple(hasWorm = false))
		.addBanana(Banana(microSieverts = 1.1), microSievertsLimit)
		.addBanana(Banana(microSieverts = 2.2), microSievertsLimit)

	// Conditional adds via breaking the chain.
	if (isTuesday) {
		fruitBasketBuilder.addGrapes(Grapes(moreLikeRaisins = false))
	}

	// Iterative adds via breaking the chain.
	repeat(3) {
		fruitBasketBuilder.addOrange(Orange)
	}

	return fruitBasketBuilder.build()
}

fun main() {

	recover(
		block = { println("Shopping result: ${goGroceryShopping()}") },
		recover = { println("Error: $it") }
	)
}
