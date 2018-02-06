package org.sdkotlin.meetup

// var's are mutable, val's are read only

// Name first, then, optionally, the type after a colon
// Similar to Scala, Swift, Go, Rust, Pascal, ML, etc.

// Variables aren't defaulted, and must be initialized

// Kotlin does its best to abstract away primitives vs wrapper types

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
	//i++ // Does not compile
	j++
}
