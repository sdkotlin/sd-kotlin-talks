package org.sdkotlin.meetup.effectivejava.item17

/* Effective Java
Item 17: Minimize mutability
 */

// In Kotlin, immutability is just a `val` away

const val FUGGETABOUTIT = "Not gonna do it"

data class ThreadSafe(val readOnly: String = "Can't touch this") {

	fun immutable(): String {
		val value =  "Don't even"
		// value += "Nope" // Doesn't compile
		return value
	}
}

fun main(args: Array<String>) {

	// FUGGETABOUTIT = "As if" // Doesn't compile
	println(FUGGETABOUTIT)

	val threadSafe = ThreadSafe()
	// threadSafe.readOnly = "Sorry" // Doesn't compile
	println(threadSafe.readOnly)
	println(threadSafe.immutable())
}
