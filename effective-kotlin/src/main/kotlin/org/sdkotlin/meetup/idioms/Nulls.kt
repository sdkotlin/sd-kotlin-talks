package org.sdkotlin.meetup.idioms

data class Person(val name: String, val address: Address? = null)

data class Address(val street1: String, val street2: String? = null,
		val city: String,
		val state: String, val zipCode: String)

/**
 * Handle nulls in chained property accessor and method calls
 */
fun whichState(person: Person?): String {
	return person?.address?.state ?: "Unknown"
}

/**
 * Handle nulls with a little less conversation, a little more action, using
 * Kotlin's Elvis operator
 *
 * @throws IllegalArgumentException if an address is not available
 */
fun sendCard(person: Person) {

	val address = person.address ?: throw IllegalArgumentException(
			"Can't send a card to a person if they don't have an address")

	println("Sending a card to ${person.name} at $address")
}

fun main(args: Array<String>) {

	val sally = Person(name = "Sally")

	println("Sally lives in ${whichState(sally)}")

	try {
		sendCard(sally)
	} catch (e: IllegalArgumentException) {
		println(e.message)
	}
}
