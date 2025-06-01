package org.sdkotlin.integral

@JvmInline
value class Octal internal constructor(
	internal val data: Int,
) : Integral<Octal> {

	companion object {

		val MIN_VALUE = Octal(Int.MIN_VALUE)
		val MAX_VALUE = Octal(Int.MAX_VALUE)
		val NEGATIVE_ONE = Octal(-1)
		val ZERO = Octal(0)
		val ONE = Octal(1)

		internal fun octalRemainder(v1: Octal, v2: Octal): Octal =
			Octal((v1.toLong() % v2.toLong()).toInt())
	}

	internal constructor(octalValue: String) : this(octalValue.toInt(8))

	override val minValue: Octal get() = MIN_VALUE
	override val maxValue: Octal get() = MAX_VALUE
	override val zero: Octal get() = ZERO
	override val one: Octal get() = ONE

	override operator fun plus(other: Octal): Octal =
		Octal(data.plus(other.data))

	override operator fun minus(other: Octal): Octal =
		Octal(data.minus(other.data))

	override operator fun unaryMinus(): Octal =
		Octal(-data)

	override operator fun rem(other: Octal): Octal =
		octalRemainder(this, other)

	fun mod(other: Octal): Octal = rem(other)

	override fun compareTo(other: Octal): Int =
		data.compareTo(other.data)

	operator fun rangeTo(other: Octal): ClosedRange<Octal> =
		IntegralRange(this, other)

	operator fun rangeUntil(other: Octal): OpenEndRange<Octal> =
		IntegralRange(this, other - other.one)

	fun toUInt(): UInt = data.toUInt()

	fun toInt(): Int = data

	fun toLong(): Long = data.toLong()

	fun toUOctal(): UOctal = UOctal(data)

	override fun toString() = "%o".format(data)
}

fun Int.toOctal(): Octal = Octal(this)

fun String.toOctal(): Octal = Octal(this)

infix fun Octal.downTo(to: Octal): IntegralProgression<Octal> =
	IntegralProgression.fromClosedRange(this, to, Octal.NEGATIVE_ONE)
