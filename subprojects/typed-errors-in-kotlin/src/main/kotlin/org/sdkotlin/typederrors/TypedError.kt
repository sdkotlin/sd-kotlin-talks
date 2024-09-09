package org.sdkotlin.typederrors

sealed interface TypedError {

	sealed interface BadFruitTypedError : TypedError {

		data object BadAppleTypedError : BadFruitTypedError

		data object RadioactiveBananaTypedError : BadFruitTypedError

		data object ShriveledGrapesTypedError : BadFruitTypedError
	}

	data object BadBasketTypedError : TypedError
}
