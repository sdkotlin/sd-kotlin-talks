package org.sdkotlin.intro.kotlin._20_interfaces

// Interfaces in Kotlin work mostly as you'd expect coming from Java, with a
// few differences when it comes to properties and constants.

interface Shape {

	// They can declare abstract functions.

	fun draw()

	// They can declare default functions.

	fun describe() = println("I'm shapely.")

	// They can declare abstract properties.

	val name: String

	// Constants are not supported.

	//const val CONSTANT = false

	// Companion objects are, and you can put constants in those.

	companion object {

		const val COMPANION_CONSTANT = true
	}

	// Interfaces can't store state, so any non-abstract properties must be
	// computed.

	val abstractName
		get() = "Shape"

	// They can be var given a side-effect setter. Maybe this could be
	// exploited when creating internal DSLs.

	var hmm
		get() = "🤔"
		set(value) = println("Hmm... $value")
}

class Circle(override val name: String) : Shape, Serializable {

	override fun draw() = println("O")

	// Default methods can be overridden.

	override fun describe() = println("I'm no square.")
}

// Multiple interface inheritance is supported as in Java.

interface Serializable

class Triangle : Shape, Serializable {

	override fun draw() = println("▲")

	override val name: String
		get() = "Pointy"
}

fun main() {
	val circle = Circle("Roundy")

	print("circle.draw(): ")
	circle.draw()

	print("circle.describe(): ")
	circle.describe()

	println("circle.name: ${circle.name}")
	println("circle.abstractName: ${circle.abstractName}")
	println("circle.hmm: ${circle.hmm}")
	print("circle.hmm = ")
	circle.hmm = "For DSLs maybe?"
}
