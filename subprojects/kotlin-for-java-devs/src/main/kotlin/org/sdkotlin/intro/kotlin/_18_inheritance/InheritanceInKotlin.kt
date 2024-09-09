package org.sdkotlin.intro.kotlin._18_inheritance

class Class

// "Effective Java, 3rd Edition. Item 19: Design and
// Document for Inheritance or Else Prohibit It"

// Classes are final by default in Kotlin (see bytecode)
//class Subclass : Class()

// You can "open" them like so...
open class BaseClass(var name: String = "Cliff") {

	// Functions are final by default in Kotlin as well
	fun finalFunction() = println("Can't touch this!")

	// But they can be opened too
	open fun openFunction() = println("Base class implementation")
}

// Subclasses must call the primary constructor of their superclass.

class Subclass : BaseClass("Norm") {

	// You can't override a final function
	//override fun finalFunction() = println("nope!")

	// You can explicitly override open functions
	override fun openFunction() = println("Subclass implementation")
}

fun main() {

	println(Class())

	val baseClass = BaseClass()
	baseClass.finalFunction()
	baseClass.openFunction()

	val subclass = Subclass()
	subclass.finalFunction()
	subclass.openFunction()
}
