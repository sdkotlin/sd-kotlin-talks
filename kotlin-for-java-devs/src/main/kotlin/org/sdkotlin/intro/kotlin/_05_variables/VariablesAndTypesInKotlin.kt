package org.sdkotlin.intro.kotlin._05_variables

// var's are mutable, val's are read only

// Pascal notation is used: name first, then, optionally or as needed, the type after a colon
// (Same as Scala, Swift, Go, Rust, etc.)

// Variables aren't defaulted, and must be initialized

// Kotlin does its best to abstract away primitives vs. wrapper types

val bool = false // Boolean
val b: Byte = 0 // Type required due to no literal
val c = 'c' // Char
val s: Short = 0 // Type required due to no literal
val i = 0 // Int
val l = 0L // Long, lowercase 'l' not supported
val f = 0.0f // Float, 'F' also supported
val d = 0.0 // Double, 'd/D' not supported

var j = 0

fun withVariables() {
	// i++ // Does not compile
	j++
}

fun main() {
	println("Boolean: " + bool)
	println("Byte: " + b)
	println("Char: " + c)
	println("Short: " + s)
	println("Int: " + i)
	println("Long: " + l)
	println("Float: " + f)
	println("Double: " + d)
	println("variable Int j: " + j)
	withVariables()
	println("variable Int j: " + j)
}
