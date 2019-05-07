package org.sdkotlin.meetup.effectivejava.item18and19

/* Effective Java
Item 18: Favor composition over inheritance
Item 19: Design and document for inheritance or else prohibit it
 */

// Kotlin classes are final by default

class CantTouchThis
// class HammerTime : CantTouchThis() // Doesn't compile


// If you open them, the functions and properties that can be overridden must be
// documented
open class JeepWranglerSport {
	open val name = "Jeep Wrangler Sport"
	open fun howMuchFun() = "🙂"
}

// And then you document them again at the point where they are overridden
class JeepWranglerRubicon: JeepWranglerSport() {
	override val name = "Jeep Wrangler Rubicon"
	override fun howMuchFun() = "😃"
}

fun main(args: Array<String>) {
	val jeepWranglerSport = JeepWranglerSport()
	val jeepWranglerRubicon = JeepWranglerRubicon()

	println("The ${jeepWranglerSport.name} is ${jeepWranglerSport.howMuchFun()}")
	println("The ${jeepWranglerRubicon.name} is ${jeepWranglerRubicon.howMuchFun()}")
}
