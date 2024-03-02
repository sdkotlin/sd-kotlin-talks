package org.sdkotlin.typederrors

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.either
import arrow.core.right
import org.sdkotlin.typederrors.Error.BadFruitError
import org.sdkotlin.typederrors.Fruit.Apple
import org.sdkotlin.typederrors.Fruit.Banana
import org.sdkotlin.typederrors.Fruit.Grapes
import org.sdkotlin.typederrors.Fruit.Orange

sealed interface Fruit {
	data class Apple(val hasWorm: Boolean) : Fruit
	data class Banana(val microSieverts: Double) : Fruit
	data class Grapes(val moreLikeRaisins: Boolean) : Fruit
	data object Orange : Fruit
}

sealed interface Error {
	data object BadFruitError : Error
}

interface FruitBasketBuilder {
	fun addApple(apple: Apple): Either<Error, FruitBasket>

	fun addBanana(banana: Banana, microSievertsLimit: Double):
		Either<Error, FruitBasket>

	fun addGrapes(grapes: Grapes): Either<Error, FruitBasket>

	fun addOrange(orange: Orange): FruitBasket
}

interface FruitBasket : FruitBasketBuilder {

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

fun goGroceryShopping(): Either<Error, FruitBasket> =
	either {

		val isTuesday = true
		val microSievertsLimit = 5.0
		var fruitBasket: FruitBasket

		fruitBasket = FruitBasket.addApple(Apple(hasWorm = false)).bind()

		fruitBasket = fruitBasket
			.addBanana(Banana(microSieverts = 1.0), microSievertsLimit).bind()
			.addBanana(Banana(microSieverts = 2.2), microSievertsLimit).bind()

		if (isTuesday) {
			fruitBasket =
				fruitBasket.addGrapes(Grapes(moreLikeRaisins = false)).bind()
		}

		repeat(3) {
			fruitBasket = fruitBasket.addOrange(Orange)
		}

		fruitBasket
	}

fun main() {

	println(goGroceryShopping())
}
