package org.sdkotlin.intro.kotlin._12_properties

// Top level variables and class fields in Kotlin are really all properties.

val readOnly = 0
	// They have default getters (and for 'var', setters) that can be overridden.
	get() {

		// If you later decide you need to add some behavior to the getter,
		// you can.

		println("Audit readOnly get.")

		// The underlying field is available in getters and setters using the
		// keyword 'field'.

		return field
	}

var readWrite = 1
	get() {
		println("Audit readWrite get.")
		return field
	}
	set(value) {
		println("Audit readWrite set: $value.")
		field = value
	}

// You can use access modifiers to restrict the getter or setter, and omit the
// either to use the default.

var readPrivateWrite = 1
	private set(value) {
		println("Audit readWrite set: $value.")
		field = value
	}

// Getters and setters can be computed.

var width = 1
var height = 1

val area: Int
	get() = width * height

fun main() {
	println("readOnly is $readOnly")

	println("readWrite is $readWrite")
	readWrite++
	println("After readWrite++ it's $readWrite")

	println("Only this file can readPrivateWrite++: ${readPrivateWrite++}")

	println("Area: $area")
	width++
	height++
	println("New area: $area")
}
