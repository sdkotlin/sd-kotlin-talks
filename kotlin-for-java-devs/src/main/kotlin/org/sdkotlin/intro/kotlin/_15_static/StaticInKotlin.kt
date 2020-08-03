package org.sdkotlin.intro.kotlin._15_static

import org.sdkotlin.intro.java._15_static.StaticInJava

class StaticInKotlin {

	companion object {

		const val CONSTANT = "Unchanging"

		@JvmStatic
		fun saySomething() {
			println(CONSTANT)
		}
	}
}

fun main() {

	StaticInJava.saySomething()
	StaticInKotlin.saySomething()

	// StaticInJava().saySomething() // Does not compile
	// StaticInKotlin().saySomething() // Does not compile
}
