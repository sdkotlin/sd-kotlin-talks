package org.sdkotlin.typederrors.either

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.raise.either
import arrow.core.right
import org.sdkotlin.typederrors.Error
import org.sdkotlin.typederrors.Fruit
import org.sdkotlin.typederrors.Error.BadFruitError
import org.sdkotlin.typederrors.Fruit.Apple
import org.sdkotlin.typederrors.Fruit.Banana
import org.sdkotlin.typederrors.Fruit.Grapes
import org.sdkotlin.typederrors.Fruit.Orange

/**
 * A fluent, mutation-free builder where some add methods may return errors.
 */
interface FruitBasketBuilder {

	fun addApple(apple: Apple): Either<Error, FruitBasket>

	fun addBanana(banana: Banana, microSievertsLimit: Double):
		Either<Error, FruitBasket>

	fun addGrapes(grapes: Grapes): Either<Error, FruitBasket>

	fun addOrange(orange: Orange): FruitBasket
}

/**
 * The type is also its own builder.
 */
interface FruitBasket : FruitBasketBuilder {

	/**
	 * The companion object implements the builder interface to bootstrap
	 * the fluent add chain.
	 */
	companion object : FruitBasketBuilder {

		override fun addApple(apple: Apple): Either<Error, FruitBasket> =
			FruitBasketImpl().addApple(apple)

		override fun addBanana(
			banana: Banana,
			microSievertsLimit: Double,
		): Either<Error, FruitBasket> =
			FruitBasketImpl().addBanana(banana, microSievertsLimit)

		override fun addGrapes(grapes: Grapes): Either<Error, FruitBasket> =
			FruitBasketImpl().addGrapes(grapes)

		override fun addOrange(orange: Orange): FruitBasket =
			FruitBasketImpl().addOrange(orange)
	}

	val fruit: List<Fruit>

	private data class FruitBasketImpl(
		override val fruit: List<Fruit> = emptyList(),
	) : FruitBasket {

		override fun addApple(apple: Apple): Either<Error, FruitBasket> =
			if (apple.hasWorm) {
				BadFruitError.left()
			} else {
				// All adds return an appended copy rather than mutate the
				// original instance.
				FruitBasketImpl(fruit + apple).right()
			}

		override fun addBanana(
			banana: Banana,
			microSievertsLimit: Double,
		): Either<Error, FruitBasket> =
			if (banana.microSieverts >= microSievertsLimit) {
				BadFruitError.left()
			} else {
				FruitBasketImpl(fruit + banana).right()
			}

		override fun addGrapes(grapes: Grapes): Either<Error, FruitBasket> =
			if (grapes.moreLikeRaisins) {
				BadFruitError.left()
			} else {
				FruitBasketImpl(fruit + grapes).right()
			}

		override fun addOrange(orange: Orange): FruitBasket =
			FruitBasketImpl(fruit + orange)
	}
}

fun goGroceryShoppingWithNestedFlatMap(): Either<Error, FruitBasket> {

	val isTuesday = true
	val microSievertsLimit = 5.0

	// Needs to be a `var` since we can't chain iteration.
	var fruitBasket: Either<Error, FruitBasket>

	fruitBasket = FruitBasket.addApple(Apple(hasWorm = false))
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

	// Iterative flatMap.
	// There doesn't seem to be any way to perform iterative nested flatMaps,
	// so we break out of the nesting and chain instead.
	repeat(3) {
		fruitBasket = fruitBasket.flatMap {
			it.addOrange(Orange).right()
		}
	}

	return fruitBasket
}

fun goGroceryShoppingWithChainedFlatMap(): Either<Error, FruitBasket> {

	val isTuesday = true
	val microSievertsLimit = 5.0

	// Needs to be a `var` since we can't chain iteration.
	var fruitBasket: Either<Error, FruitBasket>

	fruitBasket = FruitBasket.addApple(Apple(hasWorm = false))
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

	// Iterative flatMap.
	// There doesn't seem to be any way to perform iterative flatMaps without
	// breaking the chain.
	repeat(3) {
		fruitBasket = fruitBasket.flatMap {
			it.addOrange(Orange).right()
		}
	}

	return fruitBasket
}

fun goGroceryShopping(): Either<Error, FruitBasket> =
	either { // Using the builder with `.bind()` (i.e. "for-comprehension").

		val isTuesday = true
		val microSievertsLimit = 5.0

		// Needs to be a `var` since we can't chain iteration, and it seems to
		// be the most natural way to do conditional adds.
		var fruitBasket: FruitBasket

		// Can chain with `bind`.
		fruitBasket = FruitBasket.addApple(Apple(hasWorm = false)).bind()
			.addBanana(Banana(microSieverts = 1.0), microSievertsLimit).bind()
			.addBanana(Banana(microSieverts = 2.2), microSievertsLimit).bind()

		// Conditional adds via breaking the chain.
		if (isTuesday) {
			fruitBasket =
				fruitBasket.addGrapes(Grapes(moreLikeRaisins = false)).bind()
		}

		// Iterative adds via breaking the chain.
		repeat(3) {
			fruitBasket = fruitBasket.addOrange(Orange)
		}

		// Yield the "Right" value.
		fruitBasket
	}

fun main() {

	println(goGroceryShoppingWithNestedFlatMap())
	println(goGroceryShoppingWithChainedFlatMap())
	println(goGroceryShopping())
}
