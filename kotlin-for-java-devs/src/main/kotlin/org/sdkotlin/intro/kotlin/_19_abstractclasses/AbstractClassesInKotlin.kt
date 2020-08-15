package org.sdkotlin.intro.kotlin._19_abstractclasses

// Kotlin supports abstract classes and members.

// Abstract classes are implicitly open.

abstract class DinnerTemplate {

	protected open fun cookFood() {
		println("It's pasta for dinner")
	}

	// Abstract members are implicitly open as well.

	protected abstract fun serve()

	protected abstract fun enjoy()

	fun haveDinner() {
		cookFood()
		serve()
		enjoy()
	}
}

class NiceMeal : DinnerTemplate() {

	override fun serve() {
		println("Paper plates and plasticware")
	}

	override fun enjoy() {
		println("Slrrrp")
	}
}

abstract class NotPastaAgainTemplate : DinnerTemplate() {

	// An open member function can be overridden as abstract.
	abstract override fun cookFood()
}

class FancyMeal : NotPastaAgainTemplate() {

	override fun cookFood() {
		println("Chef's special")
	}

	override fun serve() {
		println("Fine china and silverware")
	}

	override fun enjoy() {
		println("Bon appetit")
	}
}

fun main() {

	val niceMeal = NiceMeal()
	val fancyMeal = FancyMeal()

	niceMeal.haveDinner()
	fancyMeal.haveDinner()
}
