package org.sdkotlin.integral

class IntegralRange<I : Integral<I>>(
	start: I,
	endInclusive: I,
) : ClosedRange<I>, OpenEndRange<I>,
	Progression<I> by IntegralProgression(
		start,
		endInclusive,
		step = start.one
	) {

	override val start: I = first

	override val endInclusive: I = last

	override val endExclusive: I by lazy {
		check(endInclusive == start.maxValue) {
			"Cannot return the exclusive upper bound of a range that includes MAX_VALUE."
		}
		endInclusive + endInclusive.one
	}

	override fun contains(value: I): Boolean =
		start <= value && value <= endInclusive

	override fun isEmpty(): Boolean = start > endInclusive

	override fun equals(other: Any?): Boolean =
		other is IntegralRange<*> && (isEmpty() && other.isEmpty() ||
			start == other.start && endInclusive == other.endInclusive)

	override fun hashCode(): Int =
		if (isEmpty()) -1 else (31 * start.hashCode() + endInclusive.hashCode())

	override fun toString(): String = "$start..$endInclusive"
}
