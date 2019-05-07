package org.sdkotlin.meetup.effectivejava.item1

import java.nio.charset.*
import java.time.Month.*
import java.util.*

/* Effective Java
Item 1: Consider static factory methods instead of constructors
- One advantage of static factory methods is that, unlike constructors, they
  have names.
- A second advantage of static factory methods is that, unlike constructors,
  they are not required to create a new object each time they’re invoked.
- A third advantage of static factory methods is that, unlike constructors,
  they can return an object of any subtype of their return type.
- A fourth advantage of static factories is that the class of the returned
  object can vary from call to call as a function of the input parameters.
- A fifth advantage of static factories is that the class of the returned
  object need not exist when the class containing the method is written.
 */

// Kotlin companion object functions, companion extension functions, and
// top-level functions can be used as powerful alternatives to Java's static
// factory methods

fun main(args: Array<String>) {

	// Java static factory usage
	val charset = Charset.forName("ASCII")
	println("Java charset is \"${charset.name()}\"")

	// Kotlin companion object factory function usage
	val alienCharset =
			AlienCharset.forName("Klingon")
	println("Alien charset is \"${alienCharset.name()}\"")

	// Kotlin companion object extension factory function usage
	val grootSays = String.valueOf(Groot())
	println("Groot says, \"$grootSays\"")

	// Kotlin top-level factory function usage
	val months = sortedMapOf(2 to FEBRUARY, 1 to JANUARY,
			3 to MARCH)
	println("The months in order are $months")
}

// Companion object factory function
abstract class AlienCharset {

	companion object {

		fun forName(charsetName: String) = when (charsetName) {
			"Klingon" -> KlingonCharset()
			"Alienese" -> AlieneseCharset()
			else -> throw IllegalArgumentException("Unknown character set")
		}
	}

	abstract fun name(): String
}

class KlingonCharset : AlienCharset() {
	override fun name() = "Klingon"
}

class AlieneseCharset : AlienCharset() {
	override fun name() = "Alienese"
}

// Companion object extension factory function
fun String.Companion.valueOf(groot: Groot) = groot.toString()

class Groot {
	override fun toString() = "I am Groot"
}

// Top-level factory function
fun <K, V> sortedMapOf(vararg pairs: Pair<K, V>): SortedMap<K, V> =
		TreeMap<K, V>().apply { putAll(pairs) }
