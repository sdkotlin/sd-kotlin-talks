package org.sdkotlin.typederrors.either

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.raise.either
import arrow.core.right
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

	fun addApple(apple: Apple): Either<Error, FruitBasketBuilder> =
		if (apple.hasWorm) {
			BadFruitError.left()
		} else {
			fruit.add(apple)
			this.right()
		}

	fun addBanana(
		banana: Banana,
		microSievertsLimit: Double,
	): Either<Error, FruitBasketBuilder> =
		if (banana.microSieverts >= microSievertsLimit) {
			BadFruitError.left()
		} else {
			fruit.add(banana)
			this.right()
		}

	fun addGrapes(grapes: Grapes): Either<Error, FruitBasketBuilder> =
		if (grapes.moreLikeRaisins) {
			BadFruitError.left()
		} else {
			fruit.add(grapes)
			this.right()
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

fun goGroceryShoppingWithNestedFlatMap(): Either<Error, FruitBasket> {

	val isTuesday = true
	val microSievertsLimit = 5.0

	val fruitBasketBuilder = FruitBasketBuilder()

	fruitBasketBuilder.addApple(Apple(hasWorm = false))
		.flatMap {
			it.addBanana(Banana(microSieverts = 1.1), microSievertsLimit)
				.flatMap {
					// 'it' is shadowed when nesting flatMap.
					it.addBanana(
						Banana(microSieverts = 2.2),
						microSievertsLimit
					).flatMap {
						// Conditional flatMap
						if (isTuesday) {
							it.addGrapes(Grapes(moreLikeRaisins = false))
						} else {
							it.right()
						}
					}
				}
		}
		.onLeft { return it.left() } // If we forget this, we swallow any errors

	// Iterative adds via breaking the chain.
	repeat(3) {
		fruitBasketBuilder.addOrange(Orange)
	}

	return fruitBasketBuilder.build().right()
}

fun goGroceryShoppingWithChainedFlatMap(): Either<Error, FruitBasket> {

	val isTuesday = true
	val microSievertsLimit = 5.0

	val fruitBasketBuilder = FruitBasketBuilder()

	fruitBasketBuilder
		.addApple(Apple(hasWorm = false))
		.flatMap {
			it.addBanana(Banana(microSieverts = 1.1), microSievertsLimit)
		}.flatMap {
			it.addBanana(Banana(microSieverts = 2.2), microSievertsLimit)
		}.flatMap {
			// Conditional flatMap
			if (isTuesday) {
				it.addGrapes(Grapes(moreLikeRaisins = false))
			} else {
				it.right()
			}
		}
		.onLeft { return it.left() } // If we forget this, we swallow any errors

	// Iterative adds via breaking the chain.
	repeat(3) {
		fruitBasketBuilder.addOrange(Orange)
	}

	return fruitBasketBuilder.build().right()
}

// Using the builder with `.bind()` (i.e. "for-comprehension").
fun goGroceryShopping(): Either<Error, FruitBasket> = either {

	val isTuesday = true
	val microSievertsLimit = 5.0

	val fruitBasketBuilder = FruitBasketBuilder()

	// Can chain with `bind()`.
	fruitBasketBuilder
		.addApple(Apple(hasWorm = false)).bind()
		.addBanana(Banana(microSieverts = 1.1), microSievertsLimit).bind()
		.addBanana(Banana(microSieverts = 2.2), microSievertsLimit).bind()

	// Conditional adds via breaking the chain.
	if (isTuesday) {
		// If we forget the `bind()`, we swallow any errors
		fruitBasketBuilder.addGrapes(Grapes(moreLikeRaisins = false)).bind()
	}

	// Iterative adds via breaking the chain.
	repeat(3) {
		fruitBasketBuilder.addOrange(Orange)
	}

	// Yield the `Either.Right` value.
	fruitBasketBuilder.build()
}

fun main() {

	println(goGroceryShoppingWithNestedFlatMap())
	println(goGroceryShoppingWithChainedFlatMap())
	println(goGroceryShopping())
}
