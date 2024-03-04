package org.sdkotlin.typederrors

sealed interface Error {
	data object BadFruitError : Error
}
