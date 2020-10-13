package org.sdkotlin.meetup.effectivejava.item18and19

/* Effective Java
Item 18: Favor composition over inheritance
Item 19: Design and document for inheritance or else prohibit it
 */

// Kotlin classes are final by default.

class CantTouchThis

//class HammerTime : CantTouchThis() // Doesn't compile

// If you open them, the functions and properties that can be overridden must
// also be documented.

open class JeepWranglerSport {
	open val name = "Jeep Wrangler Sport"
	open fun howMuchFun() = "🙂"
}

// And then you document them again at the point where they are overridden.

class JeepWranglerRubicon : JeepWranglerSport() {
	override val name = "Jeep Wrangler Rubicon"
	override fun howMuchFun() = "😃"
}

fun `with prohibiting or documenting for inheritance`() {
	val jeepWranglerSport = JeepWranglerSport()
	val jeepWranglerRubicon = JeepWranglerRubicon()

	println("The ${jeepWranglerSport.name} is ${jeepWranglerSport.howMuchFun()}")
	println("The ${jeepWranglerRubicon.name} is ${jeepWranglerRubicon.howMuchFun()}")
}

// Kotlin has built-on support for the Decorator pattern with delegates, which
// in many cases can be used as a more loosely coupled alternative to
// inheritance. It even works for closed classes!

interface DoThings {
	fun gitRDone()
}

class DoCoolThings : DoThings {
	override fun gitRDone() {
		println("Doin' cool things.")
	}
}

class DoEvenCoolerThings(private val delegate: DoThings) :
	DoThings by delegate {

	override fun gitRDone() {
		println("Doin' chill things.")
		delegate.gitRDone()
	}
}

fun `with decorators`() {
	val doCoolThings: DoThings = DoCoolThings()
	val doEvenCoolerThings: DoThings = DoEvenCoolerThings(doCoolThings)

	doEvenCoolerThings.gitRDone()
}

fun main() {
	`with prohibiting or documenting for inheritance`()
	`with decorators`()
}
