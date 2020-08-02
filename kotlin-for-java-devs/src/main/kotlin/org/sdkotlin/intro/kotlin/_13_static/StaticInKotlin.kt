package org.sdkotlin.intro.kotlin._13_static

import org.sdkotlin.intro.java._XX_static.StaticInJava

class StaticInKotlin {

	companion object {

		@JvmField
		val CONSTANT = "Unchanging"

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
