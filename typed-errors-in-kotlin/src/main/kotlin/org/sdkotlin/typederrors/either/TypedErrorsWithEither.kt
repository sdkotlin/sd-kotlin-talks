package org.sdkotlin.typederrors.either

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.right
import org.sdkotlin.typederrors.Error
import org.sdkotlin.typederrors.Error.BadBasketError
import org.sdkotlin.typederrors.Error.BadFruitError.BadAppleError
import org.sdkotlin.typederrors.Error.BadFruitError.RadioactiveBananaError
import org.sdkotlin.typederrors.Error.BadFruitError.ShriveledGrapesError
import org.sdkotlin.typederrors.Fruit
import org.sdkotlin.typederrors.Fruit.Apple
import org.sdkotlin.typederrors.Fruit.Banana
import org.sdkotlin.typederrors.Fruit.Grapes
import org.sdkotlin.typederrors.Fruit.Orange
import org.sdkotlin.typederrors.FruitBasket
import org.sdkotlin.typederrors.FruitBasketImpl

class FruitBasketBuilder {

	private val fruit: MutableList<Fruit> = mutableListOf()

	private var failed: Boolean = false

	fun addApple(apple: Apple): Either<Error, FruitBasketBuilder> = either {
		ensure(!apple.hasWorm) {
			failed = true
			BadAppleError
		}
		fruit.add(apple)
		this@FruitBasketBuilder
	}

	fun addBanana(
		banana: Banana,
		microSievertsLimit: Double,
	): Either<Error, FruitBasketBuilder> = either {
		ensure(banana.microSieverts <= microSievertsLimit) {
			failed = true
			RadioactiveBananaError
		}
		fruit.add(banana)
		this@FruitBasketBuilder
	}

	fun addGrapes(grapes: Grapes): Either<Error, FruitBasketBuilder> = either {
		ensure(!grapes.moreLikeRaisins) {
			failed = true
			ShriveledGrapesError
		}
		fruit.add(grapes)
		this@FruitBasketBuilder
	}

	fun addOrange(orange: Orange): FruitBasketBuilder {
		fruit.add(orange)
		return this
	}

	fun build(): Either<Error, FruitBasket> = either {
		// Use a staleness flag to ensure we can't ignore an earlier error
		ensure(!failed) { BadBasketError }
		FruitBasketImpl(fruit)
	}
}

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
		// If we forget this, we may swallow or continue past any errors
		.onLeft { return it.left() }

	// Iterative adds via breaking the chain.
	repeat(3) {
		fruitBasketBuilder.addOrange(Orange)
	}

	return fruitBasketBuilder.build()
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
		// If we forget this, we may swallow or continue past any errors
		.onLeft { return it.left() }

	// Iterative adds via breaking the chain.
	repeat(3) {
		fruitBasketBuilder.addOrange(Orange)
	}

	return fruitBasketBuilder.build()
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
		// If we forget to `bind()` here, we may swallow or continue past any
		// errors
		fruitBasketBuilder.addGrapes(Grapes(moreLikeRaisins = false)).bind()
	}

	// Iterative adds via breaking the chain.
	repeat(3) {
		fruitBasketBuilder.addOrange(Orange)
	}

	// Yield instead of return the value.
	fruitBasketBuilder.build().bind()
}

fun main() {

	println(goGroceryShoppingWithNestedFlatMap())
	println(goGroceryShoppingWithChainedFlatMap())
	println(goGroceryShopping())
}
