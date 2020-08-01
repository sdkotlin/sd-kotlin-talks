package org.sdkotlin.intro.kotlin._01_classes

// Can have multiple public classes in a source file.
// Class name doesn't have to match file name, though doing so is idiomatic.
// Curly braces are optional.

class Class

// "Effective Java, 3rd Edition". Item 19: Design and Document for Inheritance or Else Prohibit It
// Classes are final by default in Kotlin (see bytecode)
// Does not compile
//class Subclass : Class()

// You can "open" them like so...
open class BaseClass {

	// Functions are final in Kotlin as well...
	fun finalFunction() = println("Can't touch this!")

	// But they can be opened too...
	open fun openFunction() = println("Base class implementation")
}

class Subclass : BaseClass() {

	// Does not compile...
	//override fun finalFunction() = println("nope!")

	// You can explicitly override open functions
	override fun openFunction() = println("Subclass implementation")
}

fun main() {

	println(Class())

	with(BaseClass()) {
		finalFunction()
		openFunction()
	}

	with(Subclass()) {
		finalFunction()
		openFunction()
	}
}
