package org.sdkotlin.intro.kotlin._05_0_variables_and_types

// var's are mutable, val's are read only

// Pascal notation is used: name first, then, optionally or as needed, the type
// after a colon (Same as Pascal, Scala, Swift, Go, Rust, etc.)

// Variables aren't defaulted, and must be initialized

// Kotlin does its best to abstract away primitives vs. wrapper types

val aBoolean: Boolean = true // Boolean
val aByte: Byte = 0 // Byte, type required because no literal
val anotherByte = 0.toByte() // Alternatively, as literals are objects
val aLiteralHexByte: Byte = 0xF // Kotlin supports hex literals
val aLiteralBinaryByte: Byte = 0b0000_0000 // Kotlin supports binary literals
val aCharacter = 'c' // Char, can't be treated as a number
val aString = "Go, Kotlin!" // String
val aShort = 0.toShort() // 16-bit Short, no literal
val anInteger = 0 // 32-bit Int
val aLong = 1_000_000L // 64-bit Long, lowercase 'l' not supported
val aFloat = .0f // 32-bit Float, 'F' also supported
val aDouble = 0.0 // 64-bit Double, 'd/D' not supported

val i = 0
var j = 0

fun withVariables() {
	//i++ // Does not compile
	j++
}

fun main() {
	println("Boolean: " + aBoolean)
	println("Byte: " + aByte)
	println("Other Byte: " + anotherByte)
	println("Hex Byte: " + aLiteralHexByte)
	println("Binary Byte: " + aLiteralBinaryByte)
	println("Char: " + aCharacter)
	println("String: " + aString)
	println("Short: " + aShort)
	println("Int: " + anInteger)
	println("Long: " + aLong)
	println("Float: " + aFloat)
	println("Double: " + aDouble)
	println("variable Int j: " + j)
	withVariables()
	println("variable Int j: " + j)
}
