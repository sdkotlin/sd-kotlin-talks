package org.sdkotlin.meetup.effectivejava.item10through13

/* Effective Java
Item 10: Obey the general contract when overriding equals
Item 11: Always override hashCode when you override equals
Item 12: Always override toString
Item 13: Override clone judiciously
 */

// Data classes do all these things correctly for you out of the box.

data class Person(val name: String, var age: Int)

fun `with data classes`() {

	val sally = Person("Sally", 22)
	val sandy = Person("Sandy", 32)

	// equals

	println("Sally == Sandy?: ${sally == sandy}")

	// toString

	println("sally: $sally")

	// "clone"

	val suzzy = sally.copy(name = "Suzzy")

	// hashCode

	val people =
		mapOf(sally.name to sally, sandy.name to sandy, suzzy.name to suzzy)

	people.forEach { name, person ->
		println("$name's hash code is: ${person.hashCode()}")
	}
}

fun main() {
	`with data classes`()
}
