package org.sdkotlin.typederrors.raise

import arrow.core.Either
import arrow.core.raise.Raise
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.fold
import arrow.core.raise.recover
import org.sdkotlin.typederrors.Fruit
import org.sdkotlin.typederrors.Fruit.Apple
import org.sdkotlin.typederrors.Fruit.Banana
import org.sdkotlin.typederrors.Fruit.Grapes
import org.sdkotlin.typederrors.Fruit.Orange
import org.sdkotlin.typederrors.FruitBasket
import org.sdkotlin.typederrors.FruitBasketImpl
import org.sdkotlin.typederrors.TypedError
import org.sdkotlin.typederrors.TypedError.BadFruitTypedError.BadAppleTypedError
import org.sdkotlin.typederrors.TypedError.BadFruitTypedError.RadioactiveBananaTypedError
import org.sdkotlin.typederrors.TypedError.BadFruitTypedError.ShriveledGrapesTypedError

class FruitBasketBuilder {

	private val fruit: MutableList<Fruit> = mutableListOf()

	context(Raise<TypedError>)
	fun addApple(apple: Apple): FruitBasketBuilder {

		ensure(!apple.hasWorm) { BadAppleTypedError }
		fruit.add(apple)
		return this
	}

	context(Raise<TypedError>)
	fun addBanana(
		banana: Banana,
		microSievertsLimit: Double,
	): FruitBasketBuilder {

		ensure(banana.microSieverts <= microSievertsLimit) {
			RadioactiveBananaTypedError
		}
		fruit.add(banana)
		return this
	}

	context(Raise<TypedError>)
	fun addGrapes(grapes: Grapes): FruitBasketBuilder {

		ensure(!grapes.moreLikeRaisins) { ShriveledGrapesTypedError }
		fruit.add(grapes)
		return this
	}

	fun addOrange(orange: Orange): FruitBasketBuilder {

		fruit.add(orange)
		return this
	}

	fun build(): FruitBasket = FruitBasketImpl(fruit)
}

context(Raise<TypedError>)
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

	val eitherResult: Either<TypedError, FruitBasket> =
		either {
			goGroceryShopping()
		}

	eitherResult.fold(
		ifRight = { println("Shopping result: $it") },
		ifLeft = { println("Shopping error: $it") }
	)

	// Or ...

	when (eitherResult) {
		is Either.Right -> {
			println("Shopping result: $eitherResult")
		}
		is Either.Left -> {
			println("Shopping error: $eitherResult")
		}
	}

	// Or...

	// "Not enough information to infer type argument for 'Error'"
	@Suppress("RemoveExplicitTypeArguments")
	recover<TypedError, Unit>(
		block = { println("Shopping result: ${goGroceryShopping()}") },
		recover = { println("Shopping error: $it") }
	)

	// Or...

	// "Not enough information to infer type argument for 'Error'"
	@Suppress("RemoveExplicitTypeArguments")
	fold<TypedError, FruitBasket, Unit>(
		block = { goGroceryShopping() },
		recover = { println("Shopping error: $it") },
		transform = { println("Shopping result: $it") }
	)
}
