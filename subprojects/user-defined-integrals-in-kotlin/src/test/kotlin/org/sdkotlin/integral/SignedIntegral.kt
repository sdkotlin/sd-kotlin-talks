package org.sdkotlin.integral

@JvmInline
value class SignedIntegral(private val i: Int) : Integral<SignedIntegral> {

	companion object {

		val MIN_VALUE: SignedIntegral = SignedIntegral(Int.MIN_VALUE)

		val MAX_VALUE: SignedIntegral = SignedIntegral(Int.MAX_VALUE)

		val ZERO: SignedIntegral = SignedIntegral(0)

		val ONE: SignedIntegral = SignedIntegral(1)

		val NEGATIVE_ONE: SignedIntegral = SignedIntegral(-1)
	}

	override val minValue: SignedIntegral
		get() = MIN_VALUE

	override val maxValue: SignedIntegral
		get() = MAX_VALUE

	override val zero: SignedIntegral
		get() = ZERO

	override val one: SignedIntegral
		get() = ONE

	override fun plus(other: SignedIntegral): SignedIntegral =
		SignedIntegral(i + other.i)

	override fun minus(other: SignedIntegral): SignedIntegral =
		SignedIntegral(i - other.i)

	override fun unaryMinus(): SignedIntegral =
		SignedIntegral(-i)

	override fun rem(other: SignedIntegral): SignedIntegral =
		SignedIntegral(i % other.i)

	override fun compareTo(other: SignedIntegral): Int =
		i.compareTo(other.i)

	operator fun rangeTo(other: SignedIntegral): ClosedRange<SignedIntegral> =
		IntegralRange(this, other)

	operator fun rangeUntil(other: SignedIntegral): OpenEndRange<SignedIntegral> =
		IntegralRange(this, other - ONE)

	override fun toString(): String = i.toString()
}

infix fun SignedIntegral.downTo(to: SignedIntegral): IntegralProgression<SignedIntegral> =
	IntegralProgression.fromClosedRange(this, to, SignedIntegral.NEGATIVE_ONE)
