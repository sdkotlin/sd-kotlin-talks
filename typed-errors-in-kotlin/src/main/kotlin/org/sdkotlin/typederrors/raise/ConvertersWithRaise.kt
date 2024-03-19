package org.sdkotlin.typederrors.raise

import arrow.core.raise.Raise
import arrow.core.raise.either

data class ConverterError(
	val e: Exception,
)

fun interface ContextConverter<C, in I, out O> {
	context(C)
	operator fun invoke(input: I): O
}

// A `Converter` is `ContextConverter` with anything in context.
fun interface Converter<in I, out O> : ContextConverter<Any?, I, O>

object StringToIntConverter :
	ContextConverter<Raise<ConverterError>, String, Int> {

	context(Raise<ConverterError>)
	override fun invoke(input: String): Int =
		try {
			input.toInt()
		} catch (e: NumberFormatException) {
			raise(ConverterError(e))
		}
}

object IntToStringConverter : Converter<Int, String> {
	context(Any?)
	override fun invoke(input: Int): String = input.toString()
}

// We can generalize over the `ContextConverter` supertype...
context(C)
fun <C, I, O> withContextConverter(
	input: I,
	converter: ContextConverter<C, I, O>,
): O {

	return converter(input)
}

fun main() {

	val stringToIntSuccess = either {
		withContextConverter("1", StringToIntConverter)
	}

	println(
		"withContextConverter(\"1\", StringToIntConverter): $stringToIntSuccess"
	)

	val stringToIntFailure = either {
		withContextConverter("Nope", StringToIntConverter)
	}

	println(
		"withContextConverter(\"Nope\", StringToIntConverter): $stringToIntFailure"
	)

	val intToStringSuccess = either<ConverterError, String> {
		// The `Converter` subtype is substitutable...
		withContextConverter(1, IntToStringConverter)
	}

	println(
		"withContextConverter(1, IntToStringConverter): $intToStringSuccess"
	)

	// We can call the `Converter` subtype with anything being in context.
	with(Unit) {
		println("direct IntToStringConverter(1): " + IntToStringConverter(1))
	}
}
