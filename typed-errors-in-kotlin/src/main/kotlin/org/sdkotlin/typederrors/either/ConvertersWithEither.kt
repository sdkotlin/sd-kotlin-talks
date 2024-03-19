package org.sdkotlin.typederrors.either

import arrow.core.Either
import arrow.core.Either.Right
import arrow.core.getOrElse
import arrow.core.raise.either

data class ConverterError(
	val e: Exception,
)

fun interface EitherConverter<out E, in I, out O> {
	operator fun invoke(input: I): Either<E, O>
}

// A `Converter` is an `EitherConverter` that's always a `Right`
fun interface Converter<in I, out O> : EitherConverter<Nothing, I, O> {
	override operator fun invoke(input: I): Right<O>
}

object StringToIntConverter :
	EitherConverter<ConverterError, String, Int> {

	override fun invoke(input: String): Either<ConverterError, Int> = either {
		try {
			input.toInt()
		} catch (e: NumberFormatException) {
			raise(ConverterError(e))
		}
	}
}

object IntToStringConverter : Converter<Int, String> {
	override fun invoke(input: Int): Right<String> =
		Right(input.toString())
}

// We can generalize over the `EitherConverter` supertype
fun <E, I, O> withEitherConverter(
	input: I,
	converter: EitherConverter<E, I, O>,
): Either<E, O> =
	converter(input)

fun main() {

	val stringToIntSuccess = either {
		withEitherConverter("1", StringToIntConverter).bind()
	}

	println(
		// Is an Either.Right<Int>
		"withContextConverter(\"1\", StringToIntConverter): $stringToIntSuccess"
	)

	val stringToIntFailure = either {
		withEitherConverter("Nope", StringToIntConverter).bind()
	}

	println(
		// Is an Either.Left<ConverterError>
		"withContextConverter(\"Nope\", StringToIntConverter): $stringToIntFailure"
	)

	val intToStringSuccess = either<ConverterError, String> {
		// The `Converter` subtype is substitutable
		withEitherConverter(1, IntToStringConverter).bind()
	}

	println(
		// Is an Either.Right<String>.
		"withContextConverter(1, IntToStringConverter): $intToStringSuccess"
	)

	// To definitively get the String we still need to resolve the `Either`
	val intToStringDirect: String = IntToStringConverter(1)
		.getOrElse { throw RuntimeException("This should never happen.") }

	println(
		// Is a String
		"direct IntToStringConverter(1): $intToStringDirect"
	)
}
