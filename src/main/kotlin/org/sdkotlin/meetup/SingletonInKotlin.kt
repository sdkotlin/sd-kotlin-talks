package org.sdkotlin.meetup

object KotlinSingleton {

	fun doIt() {
		println("The most wonderful thing about tiggers is I'm the only one!")
	}
}

fun main(args: Array<String>) {

	// val singleton = KotlinSingleton() // Does not compile.
	val singleton = KotlinSingleton
	singleton.doIt()

	// Or, the more idiomatic way...
	KotlinSingleton.doIt()
}
