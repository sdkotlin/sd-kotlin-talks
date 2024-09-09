package org.sdkotlin.intro.kotlin._17_constants

// Kotlin compiles vals to properties, meaning in Java they have a private
// field and a getter.

val REGULAR_VAL = "val"

// A private val gets a private getter.

private val PRIVATE_VAL = "private val"

// Add 'const' to make a val compile-time constant. It becomes a public static
// final field in Java. It is also inlined at compile time. In a sense,
// 'const' is to 'val' as 'inline' is to 'fun'. Note that if code is compiled
// against a const in a library, and the value changes in the library, the code
// won't reflect the change until it is recompiled.

const val CONST_VAL = "const val"

// Only primitive types and String can be const, as it is known they can be
// inlined into expressions at compile-time.

//const val CONST_NON_PRIMITIVE_VAL = BigInteger.ONE

// The unsigned types are now considered primitives.

const val U_CONST = 1u

// This included primitive Arrays.

//const val CONST_PRIMITIVE_ARRAY_VAL = intArrayOf(1)

class ConstantsInKotlinCompanion {

	// Const vals must be either top level, in a companion object, or in an
	// object.

	//const val NOPE = false

	companion object {

		// Unlike regular vals, const vals can be used in annotations.

		@Deprecated(CONST_VAL)
		val COMPANION_VAL = "val in companion object"

		//@Deprecated(REGULAR_VAL)
		const val COMPANION_CONST_VAL = "const val in companion object"
	}
}

object ConstantsInKotlinObject {

	val OBJECT_VAL = "val in object"

	const val OBJECT_CONST_VAL = "const val in object"
}

fun main() {
	println("Regular val: $REGULAR_VAL")
	println("Const val: $CONST_VAL")
}
