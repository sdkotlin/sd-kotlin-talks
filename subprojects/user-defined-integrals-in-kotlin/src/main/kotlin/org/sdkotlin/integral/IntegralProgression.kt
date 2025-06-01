package org.sdkotlin.integral

class IntegralProgression<I : Integral<I>> internal constructor(
	start: I,
	endInclusive: I,
	override val step: I,
) : Progression<I> {

	init {
		require(step != step.zero) { "Step must be non-zero." }
		require(step != step.minValue) {
			"Step must be greater than the type's minimum value to avoid overflow on negation."
		}
	}

	override val first: I = start

	override val last: I = getProgressionLastElement(start, endInclusive, step)

	override fun iterator(): Iterator<I> =
		IntegralProgressionIterator(first, last, step)

	override fun isEmpty(): Boolean =
		if (step > step.zero) first > last else first < last

	override fun equals(other: Any?): Boolean =
		other is IntegralProgression<*> && (isEmpty() && other.isEmpty() ||
			first == other.first && last == other.last && step == other.step)

	override fun hashCode(): Int =
		if (isEmpty()) {
			-1
		} else {
			(31 * (31 * first.hashCode() + last.hashCode()) + step.hashCode())
		}

	override fun toString(): String =
		if (step > step.zero) {
			"$first..$last step $step"
		} else {
			"$first downTo $last step ${-step}"
		}

	companion object {

		fun <I : Integral<I>> fromClosedRange(
			rangeStart: I,
			rangeEnd: I,
			step: I,
		): IntegralProgression<I> =
			IntegralProgression(rangeStart, rangeEnd, step)

		private fun <I : Integral<I>> getProgressionLastElement(
			start: I,
			end: I,
			step: I,
		): I =
			when {
				step > step.zero -> if (start >= end) {
					end
				} else {
					end - differenceModulo(end, start, step)
				}
				step < step.zero -> if (start <= end) {
					end
				} else {
					end + differenceModulo(start, end, -step)
				}
				else -> throw IllegalArgumentException("Step is zero.")
			}

		private fun <I : Integral<I>> differenceModulo(a: I, b: I, c: I): I {
			val ac = a % c
			val bc = b % c
			return if (ac >= bc) ac - bc else ac - bc + c
		}
	}
}
