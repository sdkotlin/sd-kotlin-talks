package org.sdkotlin.typederrors

sealed interface Error {

	sealed interface BadFruitError : Error {

		data object BadAppleError : BadFruitError

		data object RadioactiveBananaError : BadFruitError

		data object ShriveledGrapesError : BadFruitError
	}

	data object BadBasketError : Error
}
