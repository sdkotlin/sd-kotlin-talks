package org.sdkotlin.meetup.effectivejava.item50

/* Effective Java
Item 50: Make defensive copies when needed
 */

// Kotlin data classes come with copy()

data class Person(val name: String, var age: Int)

object OlympicTriplets {
	val leila = Person("Leila", 33)
	val liina = leila.copy(name = "Liina")
	val lily = liina.copy(name = "Lily")

	override fun toString(): String {
		return "{ $leila, $liina, $lily }"
	}
}

fun good(triplets: OlympicTriplets) {

	// Copy Leila so as not to change her age on the passed parameter
	val leilaClone = triplets.leila.copy()

	leilaClone.age++

	println("Leila's clone is ${leilaClone.age} years old.")
}

fun bad(triplets: OlympicTriplets) {

	println("Unless we don't make a defensive copy, in which case...")
	triplets.leila.age++
}

fun main(args: Array<String>) {

	good(OlympicTriplets)

	println("The original Leila is still ${OlympicTriplets.leila.age}.")

	bad(OlympicTriplets)

	println("The original Leila is now ${OlympicTriplets.leila.age}.")
}
