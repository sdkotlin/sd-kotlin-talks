package org.sdkotlin.typederrors.union

data class ConverterError(
	val e: Exception,
)

fun interface Converter<in I, out O> {
	operator fun invoke(input: I): O
}

/*
object StringToIntConverter : Converter<String, ConverterError | Int> {

	override fun invoke(input: String): ConverterError | Int =
		try {
			input.toInt()
		} catch (e: NumberFormatException) {
			ConverterError(e)
		}
}

object IntToStringConverter : Converter<Int, String> {
	override fun invoke(input: Int): String =
		input.toString()
}

// We can generalize over the `EitherConverter` supertype...
fun <I, E, O> withUnionConverter(
	input: I,
	converter: Converter<I, E | O>,
): E | O =
	converter(input)

fun main() {

	val stringToIntSuccess =
		withUnionConverter("1", StringToIntConverter)

	println(
		"withUnionConverter(\"1\", StringToIntConverter): $stringToIntSuccess"
	)

	val stringToIntFailure =
		withUnionConverter("Nope", StringToIntConverter)

	println(
		"withUnionConverter(\"Nope\", StringToIntConverter): $stringToIntFailure"
	)

	val intToStringSuccess =
		// The `Converter` subtype is substitutable...
		withUnionConverter(1, IntToStringConverter)

	println(
		"withUnionConverter(1, IntToStringConverter): $intToStringSuccess"
	)

	// We can call the union `Converter` directly.
	val intToStringDirect = IntToStringConverter(1)

	println("direct IntToStringConverter(1): $intToStringDirect")
}
*/
