package org.sdkotlin.intro.kotlin._XX_uniontypes

import arrow.core.Either

sealed interface GarbageOrFoodOrStuffedAnimal

class Garbage : GarbageOrFoodOrStuffedAnimal {
	fun throwAway() = "Yuk"
}

class Banana : GarbageOrFoodOrStuffedAnimal {
	fun eat() = "Yum"
}

open class StuffedAnimal : GarbageOrFoodOrStuffedAnimal {
	fun play() = "Squeek"
}

class Elmo : StuffedAnimal() {
	fun beCute() = "Elmo!"
}

fun getThing(): GarbageOrFoodOrStuffedAnimal = Banana()

fun getThing2(): Either<Garbage, Banana> = Either.Left(Garbage())

fun main() {

	val thing: GarbageOrFoodOrStuffedAnimal = getThing()

	when (thing) {
		is Garbage -> println(thing.throwAway())
		is Banana -> println(thing.eat())
		is StuffedAnimal -> println(thing.play())
	}

	val thing2: Either<Garbage, Banana> = getThing2()

	when (thing2) {
		is Either.Left -> println(thing2.value.throwAway())
		is Either.Right -> println(thing2.value.eat())
	}
}
