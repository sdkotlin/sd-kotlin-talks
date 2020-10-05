package org.sdkotlin.intro.kotlin._32_delegation

import java.awt.Color
import java.time.LocalDateTime
import kotlin.DeprecationLevel.WARNING
import kotlin.properties.Delegates.notNull
import kotlin.properties.Delegates.observable
import kotlin.properties.Delegates.vetoable
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// Kotlin has built-in support for the Decorator pattern.

// Say we want a list that reports when items are retrieved. Rather than
// extending a concrete list implementation, which would could use to that
// specific implementation, we can decorate any instantiated list with the
// behavior we want.

// We declare a class that takes a delegate list as a constructor argument, and
// then declare that we implement the List API by way of that delegate.

class GossipingList<E>(private val delegate: List<E>) : List<E> by delegate {

	// The compiler will generate all the requisite list API methods for us.

	override fun get(index: Int): E {
		val element = delegate[index]
		println("Someone is accessing \"$element\" at $index!")
		return element
	}
}

fun `with delegates`() {

	// Could be any `List` implementation.

	val listOfInterestingThings = listOf(1, 2, 3)

	// Decorate it.

	val gossipingList = GossipingList(listOfInterestingThings)

	// We get our decorated behavior when using the overridden method.

	gossipingList[0]

	// We have the whole list API.

	println("List average: ${gossipingList.average()}")

	// Note that the overridden method isn't invoked by the base delegate
	// methods as it would be if the decorator class extended the delegate
	// class. This is standard with the Decorator pattern, and could be
	// considered a "feature" in that it keeps the decorator and delegate
	// loosely coupled.

	// An advantage to the loose coupling is decorators can be used to "extend"
	// otherwise final classes, and can be composed into aggregates.
}

// Kotlin also supports delegation for the access and assignment of
// properties. This is useful if you want to decorate properties with
// additional or dynamic behavior in a reusable way.

// Kotlin comes with a few useful property delegates (and factory methods
// for them) in the standard library.

val timestamped by lazy {
	println("I'm computed when first accessed!")
	LocalDateTime.now()
}

fun `with the Lazy property delegate`() {

	println("timestamped: $timestamped")
	println("timestamped: $timestamped")
}

// The Observable property delegate accepts a callback for property changes.

var observed by observable(1) { property, oldValue, newValue ->
	println("${property.name}: changing from $oldValue to $newValue")
}

fun `with the Observable property delegate`() {

	println("observed: $observed")
	observed = 2
}

// The Vetoable property delegate can block assignment with a callback that
// returns false.

var vetoed by vetoable(Color.BLUE) { property, oldValue, newValue ->
	println("${property.name}: ALWAYS $oldValue!")
	newValue == oldValue
}

fun `with the Vetoable property delegate`() {

	vetoed = Color.GREEN
	println("vetoed: $vetoed")
}

// The NotNull property delegate allows for late initialization that isn't null.

var notNull: String by notNull()

fun `with the NotNull property delegate`() {

	try {
		println(notNull)
	} catch (e: IllegalStateException) {
		println(e)
	}

	notNull = "Late initialized!"

	println("notNull: $notNull")
}

// We can delegate the storage of a classes properties in a backing map.

class Settings(val settingsMap: Map<String, Any>) {
	val host: String by settingsMap
	val port: Int by settingsMap
}

fun `with the map-backed property delegate`() {

	val settings = Settings(mapOf("host" to "localhost", "port" to 1025))

	println("settings.host: ${settings.host}")
	println("settings.port: ${settings.port}")

	println("settingsMap: ${settings.settingsMap}")
}

// This works for mutable properties as well.

class Stats(val statsMap: MutableMap<String, Any?>) {
	var reads: Int by statsMap
	var writes: Int by statsMap
}

fun `with the mutable map-backed property delegate`() {

	val stats = Stats(mutableMapOf("reads" to 0, "writes" to 0))

	stats.reads++
	stats.statsMap["writes"] = 2

	println("stats.reads: ${stats.reads}")
	println("stats.writes: ${stats.writes}")

	println("statsMap: ${stats.statsMap}")
}

// We can create our own `val` property delegate by implementing
// `ReadOnlyProperty`.

class SpyingVal<V>(private val value: V) : ReadOnlyProperty<Any?, V> {

	override operator fun getValue(thisRef: Any?, property: KProperty<*>): V {
		println("Getting '${property.name}' from $thisRef as '$value'.")
		return value
	}
}

// And the same for a `var` property with `ReadWriteProperty`.

// Implementing these interfaces is actually optional, but it helps for getting
// the signatures right.

class SpyingVar<V>(private var value: V) : ReadWriteProperty<Any?, V> {

	override operator fun getValue(thisRef: Any?, property: KProperty<*>): V {
		println("Getting '${property.name}' from $thisRef as '$value'.")
		return value
	}

	override operator fun setValue(
		thisRef: Any?,
		property: KProperty<*>,
		value: V
	) {
		this.value = value
		println("Changing '${property.name}' in $thisRef to '$value'.")
	}
}

class Paranoid {
	val someonesWatchingMe by SpyingVal("Paranoid")
	var youAndMeBoth by SpyingVar("Also Paranoid")
}

fun `creating property delegates`() {

	val paranoid = Paranoid()

	println("someonesWatchingMe: ${paranoid.someonesWatchingMe}")

	paranoid.youAndMeBoth = "Double Paranoid"

	println("youAndMeBoth: ${paranoid.youAndMeBoth}")
}

// Starting with Kotlin 1.4 we can delegate one property to another. This is
// handy for dealing with deprecations.

object Potterverse {

	var topWitch = "TBD"

	@Deprecated("Oops", ReplaceWith("topWitch"), WARNING)
	var topWhich by ::topWitch
}

fun `with deprecated property delegation`() {

	Potterverse.topWhich = "Hermione"

	println("Which witch is which?: ${Potterverse.topWitch}")
}

fun main() {
	`with delegates`()
	`with the Lazy property delegate`()
	`with the Observable property delegate`()
	`with the Vetoable property delegate`()
	`with the NotNull property delegate`()
	`with the map-backed property delegate`()
	`with the mutable map-backed property delegate`()
	`creating property delegates`()
	`with deprecated property delegation`()
}
