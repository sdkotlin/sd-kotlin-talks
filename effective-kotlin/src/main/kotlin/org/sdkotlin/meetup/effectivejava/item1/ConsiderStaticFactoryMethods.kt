package org.sdkotlin.meetup.effectivejava.item1

import java.nio.charset.Charset
import java.time.Month.FEBRUARY
import java.time.Month.JANUARY
import java.time.Month.MARCH
import java.util.SortedMap
import java.util.TreeMap

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
// top-level functions can be used as alternatives to Java's static factory
// methods.

fun `with Java static factory functions`() {

	val charset = Charset.forName("ASCII")

	println("Java charset is \"${charset.name()}\"")
}

// In Kotlin a companion object function simulates a Java static function.

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

fun `with Kotlin companion object factory functions`() {

	val alienCharset = AlienCharset.forName("Klingon")

	println("Alien charset is \"${alienCharset.name()}\"")
}

// We can add such a static factory as a companion object extension function.

fun String.Companion.valueOf(groot: Groot) = groot.toString()

class Groot {
	override fun toString() = "I am Groot"
}

fun `with Kotlin companion object extension factory functions`() {

	val grootSays = String.valueOf(Groot())

	println("Groot says, \"$grootSays\"")
}

// We can also create top-level factory functions.

fun <K, V> sortedMapOf(vararg pairs: Pair<K, V>): SortedMap<K, V> =
	TreeMap<K, V>().apply { putAll(pairs) }

fun `with Kotlin top-level factory functions`() {

	val months = sortedMapOf(2 to FEBRUARY, 1 to JANUARY, 3 to MARCH)

	println("The months in order are $months")
}

fun main() {

	`with Java static factory functions`()
	`with Kotlin companion object factory functions`()
	`with Kotlin companion object extension factory functions`()
	`with Kotlin top-level factory functions`()
}
