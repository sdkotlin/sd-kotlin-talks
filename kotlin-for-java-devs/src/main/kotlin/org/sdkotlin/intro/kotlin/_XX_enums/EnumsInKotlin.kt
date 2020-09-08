package org.sdkotlin.intro.kotlin._XX_enums

import org.sdkotlin.intro.kotlin._XX_enums.State.STARTED
import org.sdkotlin.intro.kotlin._XX_enums.TrafficLight.CAUTION
import org.sdkotlin.intro.kotlin._XX_enums.TrafficLight.GO
import org.sdkotlin.intro.kotlin._XX_enums.TrafficLight.STOP

// Kotlin supports enum classes that provide a fixed set of singleton object
// instances (constants).

enum class State {
	PENDING, STARTED, FINISHED
}

fun `with states`() {

	// A variable of the enum type can be initialized to only one of those
	// instances.

	var currentState = State.PENDING // Enum types are inferred.

	// As a matter of style, enum constants are sometimes imported.

	currentState = STARTED

	// Nullability and type safety are enforced as usual.

	//currentState = null // Does not compile.
	//currentState = "Not a State" // Does not compile.
}

// They can have a primary constructor with immutable and mutable properties.

enum class TrafficLight(val color: String, var lightTime: Int) {
	GO("Green", 30),
	CAUTION("Yellow", 5),
	STOP("Red", 20)
}

fun `while driving`(light: TrafficLight) {

	println("light.color: ${light.color}")

	// Enums can be exhaustive for `when` expressions.

	val action = when (light) {
		GO -> "Beep beep!"
		CAUTION -> "Whoa!"
		STOP -> "Screech!"
	}

	println("action: $action")

	// Do keep versioning in mind, particularly for enums used in API, as
	// adding a new instance to the enum will break such expressions everywhere.

	// Also be mindful of mutable enum properties. They are global mutable
	// state, with all the thread safety implications entailed.

	GO.lightTime = 40
}

// Enum classes can declare abstract methods and properties to be implemented
// by anonymous classes for each instance. Use a semicolon to separate the enum
// constants from the rest of the class.

enum class Trio {

	GUITAR {
		override val strings = 6
		override fun play() = "Power cord!"
	},
	BASS {
		override val strings = 4
		override fun play() = "Groove!"
	},
	DRUMS {
		override val strings = 0
		override fun play() = "Swing!"
	};

	abstract val strings: Int
	abstract fun play(): String
}

// Enums can't extend classes, but they can implement interfaces.

interface Dandy {
	val dandy: Boolean
}

interface Inseparable {
	val inseparable: Boolean
}

enum class ThreeMusketeer : Inseparable, Dandy  {
	ATHOS {
		override val dandy = false
	},
	PORTHOS {
		override val dandy = true
	},
	ARAMIS {
		override val dandy = false
	};

	// Interfaces can be implemented by each enum constant individually, or
	// for the entire set.

	override val inseparable = true
}


fun main() {
	`with states`()
	`while driving`(GO)
}
