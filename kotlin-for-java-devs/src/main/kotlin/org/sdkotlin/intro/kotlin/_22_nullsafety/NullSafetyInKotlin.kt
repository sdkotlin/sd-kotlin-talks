package org.sdkotlin.intro.kotlin._22_nullsafety

import org.sdkotlin.intro.java._22_nullsafety.NullSafetyInJava

fun main() {

	// Kotlin has separate nullable and non-nullable types.
	// For example `String` is non-nullable, where `String?` is nullable.

	// All literals--String, Int, Float, etc.--are of the non-nullable type.

	var name = "Ezra"
	//name = null // Does not compile

	var age = 16
	//age = null // Same

	// To create a nullable variable, declare the type explicitly.

	var nullableName: String? = null

	// The initializer for nullable types is optional.

	var nullableAge: Int?

	// Nullable types apply to included and user-defined types.

	class GreatGazoo

	val definitelyGreatGazoo = GreatGazoo()
	val maybeGreatGazoo: GreatGazoo? = null

	// The nullable type is always the supertype of the non-nullable equivalent.

	val anything = Any()
	val maybeAnything: Any? = null

	if (anything is Any && anything is Any?) {
		println("anything is an `Any` and an `Any?`")
	}

	if (maybeAnything is Any? && maybeAnything !is Any) {
		println("maybeAnything is an `Any?` but not an `Any`")
	}

	// It's an error if you try to dereference a property or function of a
	// nullable type.

	println("Non-nullable name length: ${name.length}") // OK

	//println("Non-nullable name length: ${nullableName.length}") // Does not compile

	// The safe call operator can be used to work around this.
	// If the receiver is null, the expression evaluates to null.

	println("Safe nullable name length: ${nullableName?.length}")

	// Smart casting works for nullable vs. non-nullable types as it does for
	// standard type checks.

	if (nullableName != null) {
		println("Smart casted nullable name length: ${nullableName.length}")
	}

	// The "Elvis operator, `?:`, can be used to create default value
	// expressions for any null value.

	val temp: String? = null

	val rockStar = temp ?: "Elvis"

	println("rockStar: ${rockStar.trim()}")

	// The Elvis operator can be used to throw an exception for null values.

	try {
		val requiredName =
			nullableName ?: throw IllegalArgumentException(
					"More specific than a NPE.")
	} catch (e: IllegalArgumentException) {
		println(e.message)
	}

	// If you really want, there is the unsafe dereference operator `!!.`
	// It defers the null safety check from compile time to runtime.

	try {
		nullableName!!.length
	} catch (e: NullPointerException) {
		println("Or we could just really love NullPointerExceptions!!")
	}

	// Another way Kotlin defends against NPEs is that `Any?` and `String?`
	// have a couple useful extension functions defined. No safe call operator
	// is required.

	val nullAnything: Any? = null

	println("nullAnything.toString: ${nullAnything.toString()}")
	println("nullAnything.hashCode: ${nullAnything.hashCode()}")

	val nullString: String? = null

	println("nullString.orEmpty(): \"${nullString.orEmpty()}\"")

	// In order to pragmatically support interop with Java, which lacks
	// non-nullable types, Kotlin has "platform types".
	// Platform types are represented in type hints, runtime exception
	// messages, etc. as the type name follow by `!`, for example `String!`.
	// Platform types can't be declared in the source code.
	// The compiler assumes they are not null.
	// If it turns out they are null, an `IllegalStateException` is thrown.

	val maybeNullFromJava = NullSafetyInJava.MAYBE_NULL

	try {
		maybeNullFromJava.trim()
	} catch (e: IllegalStateException) {
		println("Platform types can bite!")
	}

	// The Kotlin compiler will leverage `@NotNull` and `@Nullable` annotations
	// in Java to infer non-nullable and nullable types respectively instead of
	// platform types.

	val notNullFromJava = NullSafetyInJava.NOT_NULL
	val nullableFromJava = NullSafetyInJava.NULL

	if (notNullFromJava is String) {
		println("A `@NotNull` String from Java is a `String`")
	}

	if (nullableFromJava is String?) {
		println("A `@Nullable` String from Java is a `String?`")
	}

	// If a `@NotNull` annotated field or method return value ends up being
	// null, an IllegalStateException is thrown when the value is referenced.

	try {
		val erroneouslyNotNullFromJava = NullSafetyInJava.SURPRISE
		println(erroneouslyNotNullFromJava) // Unreachable
	} catch (e: IllegalStateException) {
		println("Hopefully fields annotated `@NotNull` in Java truly are!")
	}

	try {
		val erroneouslyNotNullFromJava = NullSafetyInJava.surpriseMethod()
		println(erroneouslyNotNullFromJava) // Unreachable
	} catch (e: IllegalStateException) {
		println("Hopefully methods annotated `@NotNull` in Java truly are!")
	}
}
