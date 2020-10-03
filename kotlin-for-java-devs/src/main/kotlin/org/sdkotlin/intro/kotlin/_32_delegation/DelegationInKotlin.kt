package org.sdkotlin.intro.kotlin._32_delegation

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

fun main() {
	`with delegates`()
}
