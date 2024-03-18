package org.sdkotlin.typederrors.either

import arrow.core.Either
import arrow.core.raise.either
import org.sdkotlin.typederrors.either.ConverterError.ExceptionConverterError

sealed interface ConverterError {

	data class ExceptionConverterError(
		val exception: Exception,
	) : ConverterError

	data class MessageConverterError(
		val message: String,
	) : ConverterError
}

//fun interface Converter<in I, out O> {
//	operator fun invoke(input: I): O
//}
//
//object StringToIntConverter : Converter<String, Error | Int> {
//	override fun invoke(input: String): Error | Int =
//		try {
//			input.toInt()
//		} catch (e: NumberFormatException) {
//			ExceptionError(e)
//		}
//	}
//}
//
//object IntToStringConverter : Converter<Int, String> {
//	override fun invoke(input: Int): String {
//		return input.toString().right()
//	}
//}
//
//// Then we could generalize over Converter<I, O : Error | Any?>
//fun <I, O> withErrorConverter(converter: Converter<I, Error | O>) {
//	converter.convert()
//}

fun interface EitherConverter<out E, in I, out O> {
	operator fun invoke(input: I): Either<E, O>
}

fun interface Converter<in I, out O> : EitherConverter<Nothing, I, O> {
	override operator fun invoke(input: I): Either<Nothing, O>
}

object StringToIntConverter : EitherConverter<ConverterError, String, Int> {
	override fun invoke(input: String): Either<ConverterError, Int> = either {
		try {
			input.toInt()
		} catch (e: NumberFormatException) {
			raise(ExceptionConverterError(e))
		}
	}
}

object IntToStringConverter : Converter<Int, String> {
	override fun invoke(input: Int): Either<Nothing, String> = either {
		input.toString()
	}
}

// We can generalize over the `EitherConverter` supertype...
fun <E, I, O> withEitherConverter(
	input: I,
	converter: EitherConverter<E, I, O>,
): Either<E, O> {

	return converter(input)
}

fun main() {

	println(withEitherConverter("1", StringToIntConverter))
	println(withEitherConverter("Nope", StringToIntConverter))
	// The `Converter` subtype is substitutable...
	println(withEitherConverter(1, IntToStringConverter))
}

//object ConversionEngine {
//
//	val converters: Map<Pair<KType, KType>, Converter<*, *>> =
//		mapOf(
//			(typeOf<Int>() to typeOf<String>()) to IntToStringConverter,
//			(typeOf<String>() to typeOf<Either<Error, Int>>()) to StringToIntConverter,
//		)
//
//	inline fun <reified I, reified O> convert(input: I): Either<Error, O> {
//
//		val converter: Converter<I, O>? =
//			converters[typeOf<I>() to typeOf<O>()]
//				?.let { it as Converter<I, O> }
//
//		if (converter == null) {
//			return MessageError("Unconvertable types").left()
//		}
//
//		val result = converter(input)
//
//		if (result is Either<*, *>) {
//			return (result as Either<Error, O>)
//		}
//		return result.right()
//	}
//}
//
//fun main() {
//
//	val result = ConversionEngine.convert<String, Int>("1")
//
//	println(result)
//}
