package org.sdkotlin.typederrors.either

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.right
import org.sdkotlin.typederrors.Fruit
import org.sdkotlin.typederrors.Fruit.Apple
import org.sdkotlin.typederrors.Fruit.Banana
import org.sdkotlin.typederrors.Fruit.Grapes
import org.sdkotlin.typederrors.Fruit.Orange
import org.sdkotlin.typederrors.FruitBasket
import org.sdkotlin.typederrors.FruitBasketImpl
import org.sdkotlin.typederrors.TypedError
import org.sdkotlin.typederrors.TypedError.BadBasketTypedError
import org.sdkotlin.typederrors.TypedError.BadFruitTypedError.BadAppleTypedError
import org.sdkotlin.typederrors.TypedError.BadFruitTypedError.RadioactiveBananaTypedError
import org.sdkotlin.typederrors.TypedError.BadFruitTypedError.ShriveledGrapesTypedError

class FruitBasketBuilder {

	private val fruit: MutableList<Fruit> = mutableListOf()

	private var failed: Boolean = false

	fun addApple(apple: Apple): Either<TypedError, FruitBasketBuilder> =
		either {
			ensure(!apple.hasWorm) {
				failed = true
				BadAppleTypedError
			}
			fruit.add(apple)
			this@FruitBasketBuilder
		}

	fun addBanana(
		banana: Banana,
		microSievertsLimit: Double,
	): Either<TypedError, FruitBasketBuilder> = either {
		ensure(banana.microSieverts <= microSievertsLimit) {
			failed = true
			RadioactiveBananaTypedError
		}
		fruit.add(banana)
		this@FruitBasketBuilder
	}

	fun addGrapes(grapes: Grapes): Either<TypedError, FruitBasketBuilder> =
		either {
			ensure(!grapes.moreLikeRaisins) {
				failed = true
				ShriveledGrapesTypedError
			}
			fruit.add(grapes)
			this@FruitBasketBuilder
		}

	fun addOrange(orange: Orange): FruitBasketBuilder {
		fruit.add(orange)
		return this
	}

	fun build(): Either<TypedError, FruitBasket> =
		either {
			// Use a staleness flag to ensure we can't ignore an earlier error
			ensure(!failed) { BadBasketTypedError }
			FruitBasketImpl(fruit)
		}
}

fun goGroceryShoppingWithNestedFlatMap(): Either<TypedError, FruitBasket> {

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

fun goGroceryShoppingWithChainedFlatMap(): Either<TypedError, FruitBasket> {

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
fun goGroceryShopping(): Either<TypedError, FruitBasket> = either {

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
		fruitBasketBuilder.addGrapes(Grapes(moreLikeRaisins = true)).bind()
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
