package org.sdkotlin.typederrors

interface FruitBasket {

	val fruit: List<Fruit>
}

internal data class FruitBasketImpl(
	override val fruit: List<Fruit> = emptyList(),
) : FruitBasket
