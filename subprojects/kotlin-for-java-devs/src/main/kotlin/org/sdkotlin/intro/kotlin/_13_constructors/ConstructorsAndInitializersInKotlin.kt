package org.sdkotlin.intro.kotlin._13_constructors

// Kotlin has unique syntax for constructors.

// Classes can have a primary constructor, that is declared on the same line.

class Rebel constructor(val name: String, var jedi: Boolean)

// If there are no access modifiers or annotations the constructor
// keyword can be omitted.

class Imperial(val name: String, var sith: Boolean)

// Parameters with 'var' and 'val' automatically become class properties,
// otherwise the parameters are just constructor arguments variables that
// can be used during the initialization of the class.

class Ewok(nameArg: String, var yubYub: Boolean = true) {

	// Note that properties can still be declared outside the primary
	// constructor if they're not to be parameterized at construction time.

	// You can think of the body of the class being the primary constructor
	// body, with property initializers and initializer blocks referencing the
	// arguments. Property initializers and initializer blocks are executed in
	// the order they appear.

	init {
		println(nameArg)
		println(yubYub)
		//println(name) // Not declared yet; does not compile.
	}

	val name = nameArg.uppercase()

	init {
		println(name)
	}
}

// As with functions, named and default arguments take care of most of the need
// for overloaded constructors. Secondary constructors allow for different
// annotations and modifiers.

class Hutt(val name: String) {

	var isJabba: Boolean = false

	constructor(name: String, isJabba: Boolean) : this(name) {
		this.isJabba = isJabba
	}

	constructor(name: String, i: Int) : this(name, i == 1)
}

fun main() {
	val luke = Rebel("Luke", true)
	val han = Rebel("Han", false)
	val vader = Imperial("Darth Vader", true)
	val wicket = Ewok("Wicket")
	val jabba = Hutt("Jabba", true)
}
