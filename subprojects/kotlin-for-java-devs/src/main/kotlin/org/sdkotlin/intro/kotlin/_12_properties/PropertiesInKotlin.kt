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

// Mutable state is often kept private and published as read-only, which has
// meant declaring it twice.

class ManualCart {
	private val _items = mutableListOf<Int>()

	val items: List<Int>
		get() = _items

	fun add(item: Int) {
		_items += item
	}
}

// In Java that's a private field plus a getter returning
// Collections.unmodifiableList, which enforces read-only at runtime. Kotlin's
// List is only a compile-time restriction: the getter returns the same list,
// so a cast back to MutableList succeeds.

// An explicit backing field, stable as of Kotlin 2.4, collapses the pair into
// one declaration. Inside the class 'items' is the field's MutableList; to
// callers it's the property's List.

class Cart {
	val items: List<Int> field = mutableListOf()

	fun add(item: Int) {
		items += item
	}
}

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

	val cart = Cart()
	cart.add(42)
	println("Cart items: ${cart.items}")
}
