package org.sdkotlin.typederrors.raise

import arrow.core.raise.Raise
import arrow.core.raise.either

data class ConverterError(
	val e: Exception,
)

fun interface ContextConverter<in C, in I, out O> {
	context(C)
	fun convert(input: I): O
}

// A `Converter` is `ContextConverter` with anything in context
fun interface Converter<in I, out O> : ContextConverter<Any?, I, O>

object StringToIntConverter :
	ContextConverter<Raise<ConverterError>, String, Int> {

	context(Raise<ConverterError>)
	override fun convert(input: String): Int =
		try {
			input.toInt()
		} catch (e: NumberFormatException) {
			raise(ConverterError(e))
		}
}

object IntToStringConverter : Converter<Int, String> {
	context(Any?)
	override fun convert(input: Int): String = input.toString()
}

// We can generalize over the `ContextConverter` supertype
context(C)
fun <C, I, O> withContextConverter(
	input: I,
	converter: ContextConverter<C, I, O>,
): O = converter.convert(input)

// Only compiles with K1 currently...

//fun main() {
//
//	val stringToIntSuccess = either {
//		withContextConverter("1", StringToIntConverter)
//	}
//
//	println(
//		"withContextConverter(\"1\", StringToIntConverter): $stringToIntSuccess"
//	)
//
//	val stringToIntFailure = either {
//		withContextConverter("Nope", StringToIntConverter)
//	}
//
//	println(
//		"withContextConverter(\"Nope\", StringToIntConverter): $stringToIntFailure"
//	)
//
//	// "Not enough information to infer type variable Error"...
//	val intToStringSuccess = either<ConverterError, String> {
//		// The `Converter` subtype is substitutable
//		withContextConverter(1, IntToStringConverter)
//	}
//
//	println(
//		"withContextConverter(1, IntToStringConverter): $intToStringSuccess"
//	)
//
//	// Direct call to the `Converter` subtype seems to require a `with` for
//	// `null` or any object (e.g `Unit`). Otherwise, "No required context
//	// receiver found".
//	with(null) {
//		val directIntToStringSuccess = IntToStringConverter.convert(1)
//		println("direct IntToStringConverter(1): $directIntToStringSuccess")
//	}
//}
