package org.sdkotlin.intro.kotlin._23_generics.examples

interface Vehicle {
	val make: String
	val model: String
}

interface Automobile : Vehicle {
	val wheels: Int
}

data class Car(
	override val make: String,
	override val model: String,
	override val wheels: Int = 4
) : Automobile {
	fun drive() = "Beep-Beep!"
}

data class Motorcycle(
	override val make: String,
	override val model: String,
	override val wheels: Int = 2
) : Automobile {
	fun ride() = "Wheelie!"
}

interface Aircraft : Vehicle

data class Airplane(
	override val make: String,
	override val model: String,
	val engines: Int = 1
) : Aircraft {
	fun fly() = "Sonic boom!"
}

data class Helicopter(
	override val make: String,
	override val model: String,
	val rotors: Int = 1
) : Aircraft {
	fun beatAirIntoSubmission() = "Whomp Whomp!"
}
