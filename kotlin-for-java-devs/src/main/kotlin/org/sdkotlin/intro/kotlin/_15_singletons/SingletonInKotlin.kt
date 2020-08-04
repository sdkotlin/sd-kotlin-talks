package org.sdkotlin.intro.kotlin._15_singletons

// Kotlin has built-in support for the singleton pattern.

object KotlinSingleton {

	val notQuitePi = 3.14

	fun doIt() {
		println("The most wonderful thing about tiggers is I'm the only one!")
	}
}

fun main() {

	// Singleton instances are available by their type name.

	val singleton = KotlinSingleton

	singleton.doIt()

	// No need to assign them to a var.

	println(KotlinSingleton.notQuitePi)

	// Singletons do not have constructors.

	//KotlinSingleton().doIt()
}
