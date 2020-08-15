package org.sdkotlin.intro.kotlin._17_constants

// Kotlin compiles vals to properties, meaning in Java they have a private
// field and getter.

val VAL = "val"

// Add const to compile to a public static final field.

const val CONST_VAL = "const val"

// Only primitive types and String can be const.

//const val CONST_NON_PRIMITIVE_VAL = BigInteger.ONE

// Including primitive Arrays.

//const val CONST_PRIMITIVE_ARRAY_VAL = intArrayOf(1)
