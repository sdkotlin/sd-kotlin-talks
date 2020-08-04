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
}

fun main() {

	StaticInJava.saySomething()
	StaticInKotlin.saySomething()

	// StaticInJava().saySomething() // Does not compile
	// StaticInKotlin().saySomething() // Does not compile
}
