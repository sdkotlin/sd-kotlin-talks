package org.sdkotlin.meetup.effectivejava.item16

/* Effective Java
Item 16: In public classes, use accessor methods, not public fields
 */

// Kotlin doesn't have fields, only properties with their associated accessors
// and private backing fields

data class Programmer(val name: String) {
	var languages = "Kotlin"
		set(value) {
			when (value) {
				"Kotlin", "Java" -> {
					println("""DEBUG: Appending "$value" to "$field"""")
					field += ", and $value"
				}
				else -> println("""DEBUG: Ignoring "$value"""")
			}
		}
		get() = "$field!"

	val favoriteIde = "IntelliJ"
		get() = "$field. 🙂"
}

fun main(args: Array<String>) {

	val programmer = Programmer("Jim")

	// Look, Ma, "field access"
	programmer.languages = "Java"
	programmer.languages = "Kotlin"
	programmer.languages = "Perl"

	println("${programmer.name} knows ${programmer.languages}")
	println("Their favorite IDE is ${programmer.favoriteIde}")
}
