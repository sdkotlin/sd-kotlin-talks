package org.sdkotlin.intro.kotlin._XX_singletons

object KotlinSingleton {

	fun doIt() {
		println("The most wonderful thing about tiggers is I'm the only one!")
	}
}

fun main() {

	// val singleton = KotlinSingleton() // Does not compile.
	val singleton = KotlinSingleton
	KotlinSingleton.doIt()

	// Or, the more idiomatic way...
	KotlinSingleton.doIt()
}
