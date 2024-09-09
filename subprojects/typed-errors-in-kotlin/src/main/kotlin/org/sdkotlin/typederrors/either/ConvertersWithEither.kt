package org.sdkotlin.typederrors.either

import arrow.core.Either
import arrow.core.raise.either

data class ConverterError(
	val e: Exception,
)

fun interface Converter<in I, out O> {
	fun convert(input: I): O
}

object StringToIntConverter : Converter<String, Either<ConverterError, Int>> {

	override fun convert(input: String): Either<ConverterError, Int> = either {
		try {
			input.toInt()
		} catch (e: NumberFormatException) {
			raise(ConverterError(e))
		}
	}
}

object IntToStringConverter : Converter<Int, String> {
	override fun convert(input: Int): String =
		input.toString()
}

// We can generalize over the `Converter` type
fun <I, O> withConverter(
	input: I,
	converter: Converter<I, O>,
): O =
	converter.convert(input)

fun main() {

	// We can directly call an `Either`-returning `Converter` if we use the
	// `either` builder and `bind()`
	val directStringToIntSuccess = either {
		StringToIntConverter.convert("1").bind()
	}

	println(
		// Is an `Either.Right<Int>`
		"StringToIntConverter.convert(\"1\"): $directStringToIntSuccess"
	)

	val directStringToIntFailure = either {
		StringToIntConverter.convert("Nope").bind()
	}

	println(
		// Is an `Either.Left<ConverterError>`
		"StringToIntConverter.convert(\"Nope\"): $directStringToIntFailure"
	)

	// We can pass an `Either`-returning `Converter` to `withConverter` if we
	// use the `either` builder and `bind()`
	val stringToIntSuccess = either {
		withConverter("1", StringToIntConverter).bind()
	}

	println(
		// Is an `Either.Right<Int>`
		"withConverter(\"1\", StringToIntConverter): $stringToIntSuccess"
	)

	val stringToIntFailure = either {
		withConverter("Nope", StringToIntConverter).bind()
	}

	println(
		// Is an `Either.Left<ConverterError>`
		"withConverter(\"Nope\", StringToIntConverter): $stringToIntFailure"
	)

	// We can directly call `withConverter` with a non-`Either`-returning
	// `Converter`
	val intToStringSuccess =
		withConverter(1, IntToStringConverter)

	println(
		// Is a String.
		"withConverter(1, IntToStringConverter): $intToStringSuccess"
	)

	// We can also directly call a non-`Either`-returning `Converter`
	val directIntToStringSuccess: String = IntToStringConverter.convert(1)

	println(
		// Is a String
		"IntToStringConverter.convert(1): $directIntToStringSuccess"
	)
}
