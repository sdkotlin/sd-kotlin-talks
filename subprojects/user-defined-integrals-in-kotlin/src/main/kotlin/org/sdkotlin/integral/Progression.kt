package org.sdkotlin.integral

interface Progression<out I> : Iterable<I> {
	val first: I
	val last: I
	val step: I

	fun isEmpty(): Boolean
}
