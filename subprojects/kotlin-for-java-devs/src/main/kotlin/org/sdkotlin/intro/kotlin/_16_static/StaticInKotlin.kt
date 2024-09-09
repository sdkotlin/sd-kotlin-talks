package org.sdkotlin.intro.kotlin._16_static

import org.sdkotlin.intro.java._16_static.StaticInJava

class StaticInKotlin {

	companion object {

		const val CONSTANT = "Unchanging"

		@JvmStatic
		fun saySomething() {
			println(CONSTANT)
		}
	}

	val instanceProperty = "Hello"
	fun instanceFunction() = "World"
}

fun main() {

	StaticInKotlin.saySomething()
	StaticInKotlin.CONSTANT

	StaticInKotlin().instanceFunction()

	StaticInJava.saySomething()

	// StaticInJava().saySomething() // Does not compile
	// StaticInKotlin().saySomething() // Does not compile
}
