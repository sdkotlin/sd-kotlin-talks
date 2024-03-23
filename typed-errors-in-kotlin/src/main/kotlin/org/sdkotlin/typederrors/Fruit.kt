package org.sdkotlin.typederrors

sealed interface Fruit {
	data class Apple(val hasWorm: Boolean) : Fruit
	data class Banana(val microSieverts: Double) : Fruit
	data class Grapes(val moreLikeRaisins: Boolean) : Fruit
	data object Orange : Fruit
}
