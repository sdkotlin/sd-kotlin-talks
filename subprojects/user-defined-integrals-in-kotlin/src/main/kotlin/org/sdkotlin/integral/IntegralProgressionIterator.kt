package org.sdkotlin.integral

internal class IntegralProgressionIterator<I : Integral<I>>(
	first: I,
	private val last: I,
	private val step: I,
) : Iterator<I> {

	private var hasNext: Boolean =
		if (step > step.zero) {
			first <= last
		} else {
			first >= last
		}

	private var next = if (hasNext) first else last

	override fun hasNext(): Boolean = hasNext

	override fun next(): I {
		val value = next
		if (value == last) {
			if (!hasNext) throw NoSuchElementException()
			hasNext = false
		} else {
			next += step
		}
		return value
	}
}
