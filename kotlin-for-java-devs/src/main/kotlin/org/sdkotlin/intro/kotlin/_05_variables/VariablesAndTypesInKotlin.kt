package org.sdkotlin.intro.kotlin._05_variables

// var's are mutable, val's are read only

// Pascal notation is used: name first, then, optionally or as needed, the type
// after a colon (Same as Scala, Swift, Go, Rust, etc.)

// Variables aren't defaulted, and must be initialized

// Kotlin does its best to abstract away primitives vs. wrapper types

val boolean = false // Boolean
val byte: Byte = 0 // Byte, type required because no literal
val otherByte = 0.toByte() // Alternatively, as literals are objects
val character = 'c' // Char, can't be treated as a number
val short = 0.toShort() // Short, no literal
val integer = 0 // Int
val long = 0L // Long, lowercase 'l' not supported
val float = 0.0f // Float, 'F' also supported
val double = 0.0 // Double, 'd/D' not supported

var j = 0

fun withVariables() {
	// i++ // Does not compile
	j++
}

fun main() {
	println("Boolean: " + boolean)
	println("Byte: " + byte)
	println("Other Byte: " + otherByte)
	println("Char: " + character)
	println("Short: " + short)
	println("Int: " + integer)
	println("Long: " + long)
	println("Float: " + float)
	println("Double: " + double)
	println("variable Int j: " + j)
	withVariables()
	println("variable Int j: " + j)
}
