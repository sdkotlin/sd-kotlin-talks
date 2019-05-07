package org.sdkotlin.meetup

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

fun main(args: Array<String>) {

	StaticInJava.saySomething()
	StaticInKotlin.saySomething()

	// StaticInJava().saySomething() // Does not compile
	// StaticInKotlin().saySomething() // Does not compile
}
